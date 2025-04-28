package edu.austral.ingsis.clifford;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public record FileEntry(String name, Directory parent) implements Component {

  @Override
  public boolean isDirectory() {
    return false;
  }

  @Override
  public String path() {
    String p = parent.path();
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
