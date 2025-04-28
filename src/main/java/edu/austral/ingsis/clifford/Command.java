// src/main/java/edu/austral/ingsis/Command.java
package edu.austral.ingsis.clifford;

/** A single shell‐command. */
public interface Command {
  /** The literal name, e.g. "ls", "cd", ... */
  String name();

  /** Execute with the raw tokens (including the command itself at args[0]). */
  String execute(String[] args, FileSystem fs);
}
