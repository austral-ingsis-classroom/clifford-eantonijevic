package edu.austral.ingsis.clifford;

public class PwdCommand implements Command {
  @Override
  public String name() {
    return "pwd";
  }

  @Override
  public String execute(String[] args, FileSystem fs) {
    return fs.pwd();
  }
}
