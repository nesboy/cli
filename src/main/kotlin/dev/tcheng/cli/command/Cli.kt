package dev.tcheng.cli.command

import com.github.ajalt.clikt.core.NoOpCliktCommand
import com.github.ajalt.clikt.core.context
import com.github.ajalt.clikt.output.CliktHelpFormatter

object Cli : NoOpCliktCommand() {
    init {
        context {
            helpFormatter = CliktHelpFormatter(
                showRequiredTag = true,
                showDefaultValues = true,
                maxWidth = 120
            )
        }
    }
}
