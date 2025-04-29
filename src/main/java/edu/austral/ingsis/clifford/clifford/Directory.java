// src/main/java/edu/austral/ingsis/clifford/Directory.java
package edu.austral.ingsis.clifford.clifford;

import java.util.*;

/** A directory that may contain children. */
public final class Directory implements Component {
  private final String name;
  private final Optional<Directory> parent;
  private final Map<String, Component> children = new LinkedHashMap<>();

  public Directory(String name, Optional<Directory> parent) {
    this.name = name;
    this.parent = parent;
  }

  @Override
  public String name() {
    return name;
  }

  @Override
  public Optional<Directory> parent() {
    return parent;
  }

  @Override
  public boolean isDirectory() {
    return true;
  }

  @Override
  public String path() {
    if (parent.isEmpty()) {
      return "/";
    }
    String p = parent.get().path();
    return p.equals("/") ? "/" + name : p + "/" + name;
  }

  @Override
  public Optional<Component> find(String name) {
    return Optional.ofNullable(children.get(name));
  }

  @Override
  public void add(Component item) {
    children.put(item.name(), item);
  }

  @Override
  public void remove(String name) {
    children.remove(name);
  }

  @Override
  public List<Component> children() {
    return List.copyOf(children.values());
  }
}
