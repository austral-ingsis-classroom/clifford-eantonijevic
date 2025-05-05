// src/main/java/edu/austral/ingsis/clifford/FileSystem.java
package edu.austral.ingsis.clifford.engine;

import java.util.*;
import java.util.stream.Collectors;

public class FileSystem {
  private final Directory root = new Directory("", Optional.empty());
  private Directory cwd = root;

  public String pwd() {
    return cwd.path();
  }

  public String mkdir(String name) {
    validate(name);
    if (cwd.find(name).isPresent()) {
      throw new FsException("'" + name + "' already exists");
    }
    cwd.add(new Directory(name, Optional.of(cwd)));
    return "'" + name + "' directory created";
  }

  public String touch(String name) {
    validate(name);
    if (cwd.find(name).isPresent()) {
      throw new FsException("'" + name + "' already exists");
    }
    cwd.add(new FileEntry(name, Optional.of(cwd)));
    return "'" + name + "' file created";
  }

  public String ls(Optional<String> ord) {
    List<Component> items = new ArrayList<>(cwd.children());
    ord.ifPresent(
        o -> {
          Comparator<Component> cmp = Comparator.comparing(Component::name);
          if (o.equals("desc")) cmp = cmp.reversed();
          items.sort(cmp);
        });
    return items.stream().map(Component::name).collect(Collectors.joining(" "));
  }

  public String cd(String raw) {
    Directory target = resolve(raw);
    cwd = target;
    String nm = cwd == root ? "/" : cwd.name();
    return "moved to directory '" + nm + "'";
  }

  public String rm(String name, boolean recursive) {
    Component it =
        cwd.find(name).orElseThrow(() -> new FsException("'" + name + "' does not exist"));
    if (it.isDirectory() && !recursive) {
      return "cannot remove '" + name + "', is a directory";
    }
    cwd.remove(name);
    return "'" + name + "' removed";
  }

  // ——————————————————————————————
  // Path resolution split into three small helpers
  // ——————————————————————————————

  private Directory resolve(String path) {
    if (path.equals("/")) return root;
    List<String> segments = split(path);
    Directory start = path.startsWith("/") ? root : cwd;
    return walk(segments, start);
  }

  private List<String> split(String path) {
    return Arrays.stream(path.split("/")).filter(s -> !s.isEmpty()).collect(Collectors.toList());
  }

  private Directory walk(List<String> segs, Directory start) {
    Directory cur = start;
    for (String token : segs) {
      cur = step(cur, token);
    }
    return cur;
  }

  private Directory step(Directory cur, String p) {
    if (p.equals(".") || p.isEmpty()) {
      return cur;
    }
    if (p.equals("..")) {
      return cur.parent().orElse(root);
    }
    Component child =
        cur.find(p).orElseThrow(() -> new FsException("'" + p + "' directory does not exist"));
    if (!child.isDirectory()) {
      throw new FsException("'" + p + "' is not a directory");
    }
    return (Directory) child;
  }

  private void validate(String name) {
    if (name.contains("/") || name.contains(" ")) {
      throw new FsException("invalid name");
    }
  }
}
