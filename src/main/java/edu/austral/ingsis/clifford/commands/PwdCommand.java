package edu.austral.ingsis.clifford.commands;

import edu.austral.ingsis.clifford.clifford.CommandResult;
import edu.austral.ingsis.clifford.clifford.FileSystem;

public final class PwdCommand implements Command {
  @Override
  public String name() {
    return "pwd";
  }

  @Override
  public CommandResult execute(String[] args, FileSystem fs) {
    String msg = fs.pwd();
    return CommandResult.success(msg);
  }
}
