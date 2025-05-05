package edu.austral.ingsis.clifford.commands;

import java.util.List;

public record ParsedCommand(String name, List<String> operands) {
  public static ParsedCommand parse(String line) {
    String[] parts = line.split("\\s+");
    String cmdName = parts[0];
    List<String> ops =
        parts.length > 1 ? List.copyOf(List.of(parts).subList(1, parts.length)) : List.of();
    return new ParsedCommand(cmdName, ops);
  }
}
