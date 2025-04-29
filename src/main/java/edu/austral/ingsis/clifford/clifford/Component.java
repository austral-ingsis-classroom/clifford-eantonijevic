// src/main/java/edu/austral/ingsis/clifford/Component.java
package edu.austral.ingsis.clifford.clifford;

import java.util.List;
import java.util.Optional;

/** A node in our FS tree: either a Directory or a FileEntry. */
public sealed interface Component permits Directory, FileEntry {

  String name();

  /** Parent directory, empty() for root. */
  Optional<Directory> parent();

  boolean isDirectory();

  /** Full path from the root ("/"). */
  String path();

  Optional<Component> find(String name);

  void add(Component item);

  void remove(String name);

  List<Component> children();
}
