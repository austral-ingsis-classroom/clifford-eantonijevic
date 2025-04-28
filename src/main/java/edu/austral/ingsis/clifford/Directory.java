package edu.austral.ingsis.clifford;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Directory implements Component {
  private final String name;
  private final Directory parent;
  private final Map<String, Component> children = new LinkedHashMap<>();

  public Directory(String name, Directory parent) {
    this.name = name;
    this.parent = parent;
  }

  @Override
  public String name() {
    return name;
  }

  @Override
  public Directory parent() {
    return parent;
  }

  @Override
  public boolean isDirectory() {
    return true;
  }

  @Override
  public String path() {
    if (parent == null) {
      return "/";
    }
    String p = parent.path();
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
