package edu.austral.ingsis.clifford;

import java.util.*;

public class FileSystem {
  private final Directory root = new Directory("", null);
  private Directory cwd = root;

  public String pwd() {
    return cwd.path();
  }

  public String mkdir(String name) {
    validateName(name);
    if (cwd.find(name).isPresent()) {
      throw new FsException("'" + name + "' already exists");
    }
    cwd.add(new Directory(name, cwd));
    return "'" + name + "' directory created";
  }

  public String touch(String name) {
    validateName(name);
    if (cwd.find(name).isPresent()) {
      throw new FsException("'" + name + "' already exists");
    }
    cwd.add(new FileEntry(name, cwd));
    return "'" + name + "' file created";
  }

  public String ls(Optional<String> ord) {
    List<Component> items = new ArrayList<>(cwd.children());
    if (ord.isPresent()) {
      Comparator<Component> cmp = Comparator.comparing(Component::name);
      if (ord.get().equals("desc")) {
        cmp = cmp.reversed();
      }
      items.sort(cmp);
    }
    StringJoiner sj = new StringJoiner(" ");
    items.forEach(i -> sj.add(i.name()));
    return sj.toString();
  }

  public String cd(String raw) {
    Directory target = resolve(raw);
    cwd = target;
    String nameForMsg = (cwd == root) ? "/" : cwd.name();
    return "moved to directory '" + nameForMsg + "'";
  }

  public String rm(String name, boolean recursive) {
    Component item =
        cwd.find(name).orElseThrow(() -> new FsException("'" + name + "' does not exist"));
    if (item.isDirectory() && !recursive) {
      return "cannot remove '" + name + "', is a directory";
    }
    cwd.remove(name);
    return "'" + name + "' removed";
  }

  private Directory resolve(String path) {
    if (path.equals("/")) {
      return root;
    }
    String[] parts = path.split("/");
    Directory cur = path.startsWith("/") ? root : cwd;
    for (String p : parts) {
      if (p.isEmpty() || p.equals(".")) continue;
      if (p.equals("..")) {
        cur = Optional.ofNullable(cur.parent()).orElse(root);
      } else {
        Component child =
            cur.find(p).orElseThrow(() -> new FsException("'" + p + "' directory does not exist"));
        if (!child.isDirectory()) {
          throw new FsException("'" + p + "' is not a directory");
        }
        cur = (Directory) child;
      }
    }
    return cur;
  }

  private void validateName(String name) {
    if (name.contains("/") || name.contains(" ")) {
      throw new FsException("invalid name");
    }
  }
}
