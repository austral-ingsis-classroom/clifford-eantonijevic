// src/main/java/edu/austral/ingsis/clifford/FileEntry.java
package edu.austral.ingsis.clifford.engine;

import java.util.*;

/** A leaf node (file) in the FS tree. */
public record FileEntry(String name, Optional<Directory> parent) implements Component {

  @Override
  public boolean isDirectory() {
    return false;
  }

  @Override
  public String path() {
    String p = parent.get().path();
    return p.equals("/") ? "/" + name : p + "/" + name;
  }

  @Override
  public Optional<Component> find(String name) {
    return Optional.empty();
  }

  @Override
  public void add(Component item) {
    throw new UnsupportedOperationException("Cannot add to file");
  }

  @Override
  public void remove(String name) {
    throw new UnsupportedOperationException("Cannot remove from file");
  }

  @Override
  public List<Component> children() {
    return Collections.emptyList();
  }
}
