package dev.tcheng.cli.strategy

import java.io.File

sealed interface FileGrouperStrategy {
    fun doResolveDirectoryName(file: File): String

    fun resolveDirectoryName(file: File): String {
        if (!file.isFile) {
            throw IllegalStateException("$file is not a file")
        }
        return doResolveDirectoryName(file)
    }
}

object ExtensionStrategy : FileGrouperStrategy {
    override fun doResolveDirectoryName(file: File) =
        if (file.extension.isBlank()) "@" else file.extension.uppercase()
}

object FirstAlphaNumericCharacterStrategy : FileGrouperStrategy {
    override fun doResolveDirectoryName(file: File): String {
        val candidateDirectoryName = file.name.firstOrNull { it.isLetterOrDigit() }

        return if (candidateDirectoryName == null) {
            "@"
        } else if (candidateDirectoryName.isDigit()) {
            "#"
        } else {
            candidateDirectoryName.uppercase()
        }
    }
}
