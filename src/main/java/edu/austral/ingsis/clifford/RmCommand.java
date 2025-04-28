package edu.austral.ingsis.clifford;

public class RmCommand implements Command {
  @Override
  public String name() {
    return "rm";
  }

  @Override
  public String execute(String[] args, FileSystem fs) {
    boolean recursive = args.length > 1 && args[1].equals("--recursive");
    String target = recursive ? args[2] : args[1];
    return fs.rm(target, recursive);
  }
}
