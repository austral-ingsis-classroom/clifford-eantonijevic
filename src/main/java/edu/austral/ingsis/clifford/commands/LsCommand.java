package edu.austral.ingsis.clifford.commands;

import edu.austral.ingsis.clifford.engine.CommandResult;
import edu.austral.ingsis.clifford.engine.FileSystem;
import java.util.Optional;

public final class LsCommand implements Command {
  @Override
  public String name() {
    return "ls";
  }

  @Override
  public CommandResult execute(ParsedCommand cmd, FileSystem fs) {
    if (!cmd.operands().isEmpty()) {
      return CommandResult.failure("ls: unexpected operand");
    }
    Optional<String> ord = Optional.ofNullable(cmd.options().get("ord"));
    String out = fs.ls(ord);
    return CommandResult.success(out);
  }
}
