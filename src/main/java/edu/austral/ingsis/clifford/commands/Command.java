// src/main/java/edu/austral/ingsis/Command.java
package edu.austral.ingsis.clifford.commands;

import edu.austral.ingsis.clifford.clifford.CommandResult;
import edu.austral.ingsis.clifford.clifford.FileSystem;

/** A single shell‐command. */
public sealed interface Command
    permits PwdCommand, LsCommand, MkdirCommand, TouchCommand, CdCommand, RmCommand {

  /** e.g. "ls", "cd", ... */
  String name();

  /** run it, returning a rich result. */
  CommandResult execute(String[] args, FileSystem fs);
}
