package dev.tcheng.cli.command

import com.github.ajalt.clikt.core.NoOpCliktCommand
import com.github.ajalt.clikt.core.context

object Cli : NoOpCliktCommand() {
    init {
        context {
            // figure out HelpFormatter
        }
    }
}
