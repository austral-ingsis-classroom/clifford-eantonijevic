package edu.austral.ingsis.clifford.commands;

import edu.austral.ingsis.clifford.engine.CommandResult;
import edu.austral.ingsis.clifford.engine.FileSystem;

public final class PwdCommand implements Command {
  @Override
  public String name() {
    return "pwd";
  }

  @Override
  public CommandResult execute(ParsedCommand cmd, FileSystem fs) {
    if (!cmd.operands().isEmpty()) {
      return CommandResult.failure("pwd: too many operands");
    }
    String msg = fs.pwd();
    return CommandResult.success(msg);
  }
}
