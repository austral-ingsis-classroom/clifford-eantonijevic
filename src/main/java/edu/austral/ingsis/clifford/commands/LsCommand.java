package edu.austral.ingsis.clifford.commands;

import edu.austral.ingsis.clifford.clifford.CommandResult;
import edu.austral.ingsis.clifford.clifford.FileSystem;
import java.util.Optional;

public final class LsCommand implements Command {
  @Override
  public String name() {
    return "ls";
  }

  @Override
  public CommandResult execute(String[] args, FileSystem fs) {
    Optional<String> ord = Optional.empty();
    if (args.length > 1 && args[1].startsWith("--ord=")) {
      ord = Optional.of(args[1].substring(6));
    }
    String msg = fs.ls(ord);
    return CommandResult.success(msg);
  }
}
