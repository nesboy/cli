package dev.tcheng.cli.command

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.options.flag
import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.options.required
import com.github.ajalt.clikt.parameters.types.path
import org.apache.logging.log4j.kotlin.Logging
import java.io.File
import java.nio.file.Path
import java.util.zip.ZipEntry
import java.util.zip.ZipFile
import kotlin.io.path.Path
import kotlin.io.path.createDirectories
import kotlin.io.path.pathString

object ZipExtractor :
    CliktCommand(
        help = "Extracts all zip files in a given folder.",
        printHelpOnEmptyArgs = true
    ),
    Logging {
    private val inputPath by option(
        names = arrayOf("-i"),
        help = "Input path containing zip files to extract"
    ).path(mustExist = true, canBeDir = true, canBeFile = false, canBeSymlink = false)
        .required()
    private val isRecursive by option(
        names = arrayOf("-r"),
        help = "Recursively process files in input path (default: enabled)"
    ).flag(default = true)
    private val shouldDeleteZipFile by option(
        names = arrayOf("-d"),
        help = "Delete the zip file after it has been extracted (default: enabled)"
    ).flag(default = true)

    override fun run() {
        inputPath.toFile()
            .walk()
            .maxDepth(if (isRecursive) Integer.MAX_VALUE else 1)
            .asSequence()
            .filter { it.isFile && it.extension == "zip" }
            .forEach { processFile(it) }
    }

    private fun processFile(file: File) {
        extractZipFile(ZipFile(file))

        if (shouldDeleteZipFile) {
            file.delete()
        }

        logger.info("Processed file=$file")
    }

    private fun extractZipFile(zipFile: ZipFile) {
        val parentPath = Path(zipFile.name).parent

        zipFile.use { file ->
            file.entries().asSequence().forEach { zipEntry ->
                extractZipEntry(zipFile, zipEntry, parentPath)
            }
        }
    }

    private fun extractZipEntry(zipFile: ZipFile, zipEntry: ZipEntry, parentPath: Path) {
        if (zipEntry.isDirectory) {
            Path("""${parentPath.pathString}\${zipEntry.name}""").createDirectories()
        } else {
            zipFile.getInputStream(zipEntry).use { inputStream ->
                File("""$parentPath\${zipEntry.name}""").outputStream().use { outputStream ->
                    inputStream.copyTo(outputStream)
                }
            }
        }
    }
}
