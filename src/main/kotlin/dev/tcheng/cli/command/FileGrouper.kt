package dev.tcheng.cli.command

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.options.default
import com.github.ajalt.clikt.parameters.options.defaultLazy
import com.github.ajalt.clikt.parameters.options.flag
import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.options.required
import com.github.ajalt.clikt.parameters.types.enum
import com.github.ajalt.clikt.parameters.types.path
import dev.tcheng.cli.model.FileGrouping
import dev.tcheng.cli.strategy.ExtensionStrategy
import dev.tcheng.cli.strategy.FirstAlphaNumericCharacterStrategy
import org.apache.logging.log4j.kotlin.Logging
import java.io.File
import java.nio.file.Path
import java.nio.file.StandardCopyOption
import kotlin.io.path.Path
import kotlin.io.path.copyTo
import kotlin.io.path.createDirectories
import kotlin.io.path.notExists
import kotlin.io.path.pathString

object FileGrouper : CliktCommand(
    help = """Copies all files from a source folder to sub-folders based on some grouping strategy.
    """.trimMargin()
), Logging {
    private val inputPath by option(
        names = arrayOf("-i"), help = "Input path containing files to group"
    ).path(mustExist = true, canBeDir = true, canBeFile = false, canBeSymlink = false)
        .required()
    private val outputPath by option(
        names = arrayOf("-o"), help = "Output path containing group sub-folders with files (default: input path)"
    ).path(mustExist = true, canBeDir = true, canBeFile = false, canBeSymlink = false)
        .defaultLazy { inputPath }
    private val isRecursive by option(
        names = arrayOf("-r"), help = "Recursively process files in source path (default: disabled)"
    ).flag(default = false)
    private val fileGrouping by option(
        names = arrayOf("-g"), help = "File grouping strategy that determines which sub-folders to create"
    ).enum<FileGrouping>(ignoreCase = true)
        .default(FileGrouping.FIRST_ALPHA_NUMERIC_CHARACTER)

    override fun run() {
        inputPath.toFile()
            .walk()
            .maxDepth(if (isRecursive) Integer.MAX_VALUE else 1)
            .filter { it.isFile }
            .groupBy {
                when(fileGrouping) {
                    FileGrouping.EXTENSION ->
                        ExtensionStrategy.resolveDirectoryName(it)
                    FileGrouping.FIRST_ALPHA_NUMERIC_CHARACTER ->
                        FirstAlphaNumericCharacterStrategy.resolveDirectoryName(it)
                }
            }
            .forEach { (directoryName, files) ->
                processFiles(outputPath, directoryName, files)
            }
    }

    private fun processFiles(targetPath: Path, directoryName: String, files: List<File>) {
        val destinationDirectoryPath = Path("""${targetPath.pathString}\$directoryName""")
            .also { createDirectory(it) }

        files.forEach {
            it.toPath().copyTo(
                target = Path("""$destinationDirectoryPath\${it.name}"""),
                options = arrayOf(StandardCopyOption.REPLACE_EXISTING)
            )
            logger.info("Processed file=$it")
        }
    }

    private fun createDirectory(directory: Path) {
        if (directory.notExists()) {
            directory.createDirectories()
            logger.info("Created directory=$directory")
        }
    }
}
