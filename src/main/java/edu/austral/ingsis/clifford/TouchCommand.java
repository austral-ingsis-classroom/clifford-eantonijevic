package edu.austral.ingsis.clifford;

public class TouchCommand implements Command {
  @Override
  public String name() {
    return "touch";
  }

  @Override
  public String execute(String[] args, FileSystem fs) {
    return fs.touch(args[1]);
  }
}
