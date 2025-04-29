package edu.austral.ingsis.clifford.commands;

import edu.austral.ingsis.clifford.clifford.CommandResult;
import edu.austral.ingsis.clifford.clifford.FileSystem;

public final class CdCommand implements Command {
  @Override
  public String name() {
    return "cd";
  }

  @Override
  public CommandResult execute(String[] args, FileSystem fs) {
    String msg = fs.cd(args[1]);
    return CommandResult.success(msg);
  }
}
