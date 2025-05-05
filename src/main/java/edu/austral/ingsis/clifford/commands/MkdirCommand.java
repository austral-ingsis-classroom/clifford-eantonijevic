package edu.austral.ingsis.clifford.commands;

import edu.austral.ingsis.clifford.engine.CommandResult;
import edu.austral.ingsis.clifford.engine.FileSystem;

public final class MkdirCommand implements Command {
  @Override
  public String name() {
    return "mkdir";
  }

  @Override
  public CommandResult execute(ParsedCommand cmd, FileSystem fs) {
    var ops = cmd.operands();
    if (ops.isEmpty()) {
      return CommandResult.failure("mkdir: missing operand");
    }
    if (ops.size() > 1) {
      return CommandResult.failure("mkdir: too many operands");
    }
    String msg = fs.mkdir(ops.get(0));
    return CommandResult.success(msg);
  }
}
