package edu.austral.ingsis;

import static java.util.Map.entry;

import edu.austral.ingsis.clifford.commands.*;
import edu.austral.ingsis.clifford.engine.CommandResult;
import edu.austral.ingsis.clifford.engine.FileSystem;
import edu.austral.ingsis.clifford.engine.FsException;
import java.util.*;

public class FileSystemRunnerImpl implements FileSystemRunner {
  private final FileSystem fs = new FileSystem();
  private final Map<String, Command> commands;

  /** No-arg ctor for existing tests */
  public FileSystemRunnerImpl() {
    this(defaultCommands());
  }

  /** Main ctor, allows custom command registrations */
  public FileSystemRunnerImpl(Map<String, Command> commands) {
    this.commands = Map.copyOf(commands);
  }

  @Override
  public List<CommandResult> executeCommands(List<String> lines) {
    return lines.stream().map(this::executeLine).toList();
  }

  private CommandResult executeLine(String line) {
    ParsedCommand pc = ParsedCommand.parse(line);
    Command c = commands.get(pc.name());
    if (c == null) {
      return CommandResult.failure("unknown command: " + pc.name());
    }
    try {
      return c.execute(pc, fs);
    } catch (FsException e) {
      return CommandResult.failure(e.getMessage());
    }
  }

  private static Map<String, Command> defaultCommands() {
    return Map.ofEntries(
        entry("pwd", new PwdCommand()),
        entry("ls", new LsCommand()),
        entry("mkdir", new MkdirCommand()),
        entry("touch", new TouchCommand()),
        entry("cd", new CdCommand()),
        entry("rm", new RmCommand()));
  }
}
