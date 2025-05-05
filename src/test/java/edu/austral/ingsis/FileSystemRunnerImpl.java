package edu.austral.ingsis;

import static java.util.Map.entry;

import edu.austral.ingsis.clifford.commands.*;
import edu.austral.ingsis.clifford.engine.CommandResult;
import edu.austral.ingsis.clifford.engine.FileSystem;
import edu.austral.ingsis.clifford.engine.FsException;
import java.util.*;

public final class FileSystemRunnerImpl implements FileSystemRunner {
  private final FileSystem fs = new FileSystem();
  private final Map<String, Command> commands;

  public FileSystemRunnerImpl() {
    this(defaultCommands());
  }

  public FileSystemRunnerImpl(Map<String, Command> commands) {
    this.commands = Map.copyOf(commands);
  }

  @Override
  public List<CommandResult> executeCommands(List<String> lines) {
    // wrap in unmodifiableList
    return Collections.unmodifiableList(
      lines.stream()
        .map(this::executeLine)
        .toList()
    );
  }

  private CommandResult executeLine(String line) {
    final var pc = ParsedCommand.parse(line);
    final var cmd = commands.get(pc.name());
    if (cmd == null) {
      return CommandResult.failure("unknown command: " + pc.name());
    }
    try {
      return cmd.execute(pc, fs);
    } catch (FsException e) {
      return CommandResult.failure(e.getMessage());
    }
  }

  private static Map<String, Command> defaultCommands() {
    return Map.of(
      "pwd",   new PwdCommand(),
      "ls",    new LsCommand(),
      "mkdir", new MkdirCommand(),
      "touch", new TouchCommand(),
      "cd",    new CdCommand(),
      "rm",    new RmCommand()
    );
  }
}
