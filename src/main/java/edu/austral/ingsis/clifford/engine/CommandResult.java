package edu.austral.ingsis.clifford.engine;

/** Immutable result of running a command. */
public record CommandResult(boolean success, String message) {

  public static CommandResult success(String msg) {
    return new CommandResult(true, msg);
  }

  public static CommandResult failure(String msg) {
    return new CommandResult(false, msg);
  }
}
