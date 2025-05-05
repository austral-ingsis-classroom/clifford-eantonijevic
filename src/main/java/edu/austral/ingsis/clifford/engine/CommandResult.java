package edu.austral.ingsis.clifford.engine;

/** The result of running a command: - success? - human-readable message. */
public final class CommandResult {
  private final boolean success;
  private final String message;

  private CommandResult(boolean success, String message) {
    this.success = success;
    this.message = message;
  }

  public static CommandResult success(String msg) {
    return new CommandResult(true, msg);
  }

  public static CommandResult failure(String msg) {
    return new CommandResult(false, msg);
  }

  public boolean isSuccess() {
    return success;
  }

  public String getMessage() {
    return message;
  }

  @Override
  public String toString() {
    return message;
  }
}
