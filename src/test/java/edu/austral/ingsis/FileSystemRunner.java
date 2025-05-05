// src/main/java/edu/austral/ingsis/FileSystemRunner.java
package edu.austral.ingsis;

import edu.austral.ingsis.clifford.engine.CommandResult;
import java.util.List;

/** Execute a list of shell lines, returning structured results. */
public interface FileSystemRunner {
  List<CommandResult> executeCommands(List<String> commands);
}
