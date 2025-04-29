package edu.austral.ingsis.clifford.commands;

import edu.austral.ingsis.clifford.clifford.CommandResult;
import edu.austral.ingsis.clifford.clifford.FileSystem;

public final class RmCommand implements Command {
  @Override
  public String name() {
    return "rm";
  }

  @Override
  public CommandResult execute(String[] args, FileSystem fs) {
    boolean recursive = args.length > 1 && args[1].equals("--recursive");
    String target = recursive ? args[2] : args[1];
    String msg = fs.rm(target, recursive);
    return CommandResult.success(msg);
  }
}
