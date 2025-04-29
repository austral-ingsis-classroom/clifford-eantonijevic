package edu.austral.ingsis.clifford.commands;

import edu.austral.ingsis.clifford.clifford.CommandResult;
import edu.austral.ingsis.clifford.clifford.FileSystem;

public final class MkdirCommand implements Command {
  @Override
  public String name() {
    return "mkdir";
  }

  @Override
  public CommandResult execute(String[] args, FileSystem fs) {
    String msg = fs.mkdir(args[1]);
    return CommandResult.success(msg);
  }
}
