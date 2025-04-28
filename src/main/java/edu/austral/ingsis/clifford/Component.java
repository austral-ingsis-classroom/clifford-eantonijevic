// src/main/java/edu/austral/ingsis/clifford/Component.java
package edu.austral.ingsis.clifford;

import java.util.List;
import java.util.Optional;

/** A file‐system component: either a Directory or a FileEntry. */
public interface Component {
  String name();

  Directory parent();

  boolean isDirectory();

  /** Full path from root ("/"). */
  String path();

  /** Find a direct child by name (directories only); files always return empty. */
  Optional<Component> find(String name);

  /** Add a child (only valid on directories). */
  void add(Component item);

  /** Remove a child by name (only valid on directories). */
  void remove(String name);

  /** List children (only valid on directories). */
  List<Component> children();
}
