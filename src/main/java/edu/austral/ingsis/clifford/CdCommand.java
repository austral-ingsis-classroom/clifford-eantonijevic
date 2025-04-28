package edu.austral.ingsis.clifford;

public class CdCommand implements Command {
  @Override
  public String name() {
    return "cd";
  }

  @Override
  public String execute(String[] args, FileSystem fs) {
    return fs.cd(args[1]);
  }
}
