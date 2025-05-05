package edu.austral.ingsis.clifford.commands;

import edu.austral.ingsis.clifford.engine.CommandResult;
import edu.austral.ingsis.clifford.engine.FileSystem;

public final class RmCommand implements Command {
  @Override
  public String name() {
    return "rm";
  }

  @Override
  public CommandResult execute(ParsedCommand cmd, FileSystem fs) {
    var ops = cmd.operands();
    if (ops.isEmpty()) {
      return CommandResult.failure("rm: missing operand");
    }

    boolean recursive = "--recursive".equals(ops.get(0));
    String target;
    if (recursive) {
      if (ops.size() < 2) {
        return CommandResult.failure("rm: missing target for --recursive");
      }
      if (ops.size() > 2) {
        return CommandResult.failure("rm: too many operands");
      }
      target = ops.get(1);
    } else {
      if (ops.size() > 1) {
        return CommandResult.failure("rm: too many operands");
      }
      target = ops.get(0);
    }

    String msg = fs.rm(target, recursive);
    return CommandResult.success(msg);
  }
}
