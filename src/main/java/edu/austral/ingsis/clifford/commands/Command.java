package edu.austral.ingsis.clifford.commands;

import edu.austral.ingsis.clifford.engine.CommandResult;
import edu.austral.ingsis.clifford.engine.FileSystem;

public sealed interface Command
    permits CdCommand, LsCommand, MkdirCommand, PwdCommand, RmCommand, TouchCommand {

  String name();

  /** Now takes a ParsedCommand instead of raw String[]. */
  CommandResult execute(ParsedCommand cmd, FileSystem fs);
}
