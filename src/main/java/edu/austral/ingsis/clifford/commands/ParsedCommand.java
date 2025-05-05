package edu.austral.ingsis.clifford.commands;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Represents a trimmed & split line: - name: the command word - operands: positional args -
 * options: flags like "--ord=value"
 */
public record ParsedCommand(String name, List<String> operands, Map<String, String> options) {
  /**
   * Factory: split on whitespace, first token is name, then flags (starting with "--key=value") vs.
   * plain operands.
   */
  public static ParsedCommand parse(String line) {
    String[] tokens = line.strip().split("\\s+");
    if (tokens.length == 0 || tokens[0].isBlank()) {
      return new ParsedCommand("", List.of(), Map.of());
    }

    String name = tokens[0];
    List<String> ops = new ArrayList<>();
    Map<String, String> opts = new HashMap<>();

    for (int i = 1; i < tokens.length; i++) {
      String t = tokens[i];
      if (t.startsWith("--") && t.contains("=")) {
        String[] kv = t.substring(2).split("=", 2);
        opts.put(kv[0], kv[1]);
      } else {
        ops.add(t);
      }
    }

    return new ParsedCommand(name, List.copyOf(ops), Map.copyOf(opts));
  }
}
