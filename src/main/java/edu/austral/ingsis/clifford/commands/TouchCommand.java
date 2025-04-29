package edu.austral.ingsis.clifford.commands;

import edu.austral.ingsis.clifford.clifford.CommandResult;
import edu.austral.ingsis.clifford.clifford.FileSystem;

public final class TouchCommand implements Command {
  @Override
  public String name() {
    return "touch";
  }

  @Override
  public CommandResult execute(String[] args, FileSystem fs) {
    String msg = fs.touch(args[1]);
    return CommandResult.success(msg);
  }
}
