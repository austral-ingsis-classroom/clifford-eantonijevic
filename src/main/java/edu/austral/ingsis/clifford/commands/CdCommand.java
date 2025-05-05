package edu.austral.ingsis.clifford.commands;

import edu.austral.ingsis.clifford.engine.CommandResult;
import edu.austral.ingsis.clifford.engine.FileSystem;

public final class CdCommand implements Command {
  @Override
  public String name() {
    return "cd";
  }

  @Override
  public CommandResult execute(ParsedCommand cmd, FileSystem fs) {
    var ops = cmd.operands();
    if (ops.isEmpty()) {
      return CommandResult.failure("cd: missing operand");
    }
    if (ops.size() > 1) {
      return CommandResult.failure("cd: too many operands");
    }
    String msg = fs.cd(ops.get(0));
    return CommandResult.success(msg);
  }
}
