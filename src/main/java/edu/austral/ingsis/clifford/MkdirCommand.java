package edu.austral.ingsis.clifford;

public class MkdirCommand implements Command {
  @Override
  public String name() {
    return "mkdir";
  }

  @Override
  public String execute(String[] args, FileSystem fs) {
    return fs.mkdir(args[1]);
  }
}
