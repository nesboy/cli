package dev.tcheng.cli.command

import com.github.ajalt.clikt.core.NoOpCliktCommand
import com.github.ajalt.clikt.core.context
import com.github.ajalt.clikt.output.MordantHelpFormatter

object Cli : NoOpCliktCommand() {
    init {
        context {
            helpFormatter = {
                MordantHelpFormatter(
                    context = it,
                    showDefaultValues = true,
                    showRequiredTag = true
                )
            }
        }
    }
}
