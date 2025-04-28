package edu.austral.ingsis.clifford;

import java.util.Optional;

public class LsCommand implements Command {
  @Override
  public String name() {
    return "ls";
  }

  @Override
  public String execute(String[] args, FileSystem fs) {
    Optional<String> ord = Optional.empty();
    if (args.length > 1 && args[1].startsWith("--ord=")) {
      ord = Optional.of(args[1].substring(6));
    }
    return fs.ls(ord);
  }
}
