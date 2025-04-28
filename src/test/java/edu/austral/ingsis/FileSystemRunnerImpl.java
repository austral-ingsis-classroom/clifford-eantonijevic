package edu.austral.ingsis;

import static java.util.Map.entry;

import edu.austral.ingsis.clifford.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FileSystemRunnerImpl implements FileSystemRunner {
  private final FileSystem fs = new FileSystem();
  private final Map<String, Command> commands;

  public FileSystemRunnerImpl() {
    commands =
        Map.ofEntries(
            entry("pwd", new PwdCommand()),
            entry("ls", new LsCommand()),
            entry("mkdir", new MkdirCommand()),
            entry("touch", new TouchCommand()),
            entry("cd", new CdCommand()),
            entry("rm", new RmCommand()));
  }

  @Override
  public List<String> executeCommands(List<String> cmds) {
    List<String> out = new ArrayList<>();
    for (String line : cmds) {
      try {
        out.add(handle(line));
      } catch (FsException e) {
        out.add(e.getMessage());
      }
    }
    return out;
  }

  private String handle(String line) {
    String[] tok = line.trim().split("\\s+");
    Command cmd = commands.get(tok[0]);
    if (cmd == null) {
      throw new FsException("unknown command");
    }
    return cmd.execute(tok, fs);
  }
}
