package edu.austral.ingsis.clifford.commands;

import edu.austral.ingsis.clifford.engine.CommandResult;
import edu.austral.ingsis.clifford.engine.FileSystem;

public final class TouchCommand implements Command {
  @Override
  public String name() {
    return "touch";
  }

  @Override
  public CommandResult execute(ParsedCommand cmd, FileSystem fs) {
    var ops = cmd.operands();
    if (ops.isEmpty()) {
      return CommandResult.failure("touch: missing operand");
    }
    if (ops.size() > 1) {
      return CommandResult.failure("touch: too many operands");
    }
    String msg = fs.touch(ops.get(0));
    return CommandResult.success(msg);
  }
}
