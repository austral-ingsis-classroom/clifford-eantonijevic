// src/main/java/edu/austral/ingsis/FileSystemRunnerImpl.java
package edu.austral.ingsis;

import static java.util.Map.entry;

import edu.austral.ingsis.clifford.clifford.CommandResult;
import edu.austral.ingsis.clifford.clifford.FileSystem;
import edu.austral.ingsis.clifford.clifford.FsException;
import edu.austral.ingsis.clifford.commands.*;
import java.util.*;

public class FileSystemRunnerImpl implements FileSystemRunner {
  private final FileSystem fs = new FileSystem();
  private final Map<String, Command> cmds;

  public FileSystemRunnerImpl() {
    cmds =
        Map.ofEntries(
            entry("pwd", new PwdCommand()),
            entry("ls", new LsCommand()),
            entry("mkdir", new MkdirCommand()),
            entry("touch", new TouchCommand()),
            entry("cd", new CdCommand()),
            entry("rm", new RmCommand()));
  }

  @Override
  public List<CommandResult> executeCommands(List<String> lines) {
    List<CommandResult> out = new ArrayList<>();
    for (String line : lines) {
      out.add(executeOne(line));
    }
    return out;
  }

  private CommandResult executeOne(String line) {
    String[] tok = line.trim().split("\\s+");
    Command c = cmds.get(tok[0]);
    if (c == null) {
      return CommandResult.failure("unknown command");
    }
    try {
      return c.execute(tok, fs);
    } catch (FsException e) {
      return CommandResult.failure(e.getMessage());
    }
  }
}
