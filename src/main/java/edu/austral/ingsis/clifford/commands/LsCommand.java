package edu.austral.ingsis.clifford.commands;

import edu.austral.ingsis.clifford.engine.CommandResult;
import edu.austral.ingsis.clifford.engine.FileSystem;
import java.util.List;
import java.util.Optional;

public final class LsCommand implements Command {
  private static final String ORD_FLAG_PREFIX = "--ord=";

  @Override
  public String name() {
    return "ls";
  }

  @Override
  public CommandResult execute(ParsedCommand cmd, FileSystem fs) {
    List<String> ops = cmd.operands();

    // only zero or one argument permitted
    if (ops.size() > 1) {
      return CommandResult.failure("ls: unexpected operand");
    }

    // parse the single flag if present
    Optional<String> ord = Optional.empty();
    if (ops.size() == 1) {
      String candidate = ops.get(0);
      if (candidate.startsWith(ORD_FLAG_PREFIX)) {
        ord = Optional.of(candidate.substring(ORD_FLAG_PREFIX.length()));
      } else {
        return CommandResult.failure("ls: unexpected operand");
      }
    }

    String out = fs.ls(ord);
    return CommandResult.success(out);
  }
}
