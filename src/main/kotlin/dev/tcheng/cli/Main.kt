package dev.tcheng.cli

import com.github.ajalt.clikt.core.subcommands
import dev.tcheng.cli.command.Cli
import dev.tcheng.cli.command.FileGrouper
import dev.tcheng.cli.command.ZipExtractor

fun main(args: Array<String>) {
    Cli.subcommands(
        FileGrouper,
        ZipExtractor
    ).main(args)
}
