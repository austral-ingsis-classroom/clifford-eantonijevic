package edu.austral.ingsis;

import static org.junit.jupiter.api.Assertions.*;

import edu.austral.ingsis.clifford.engine.CommandResult;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FileSystemErrorTests {

  private FileSystemRunner runner;

  @BeforeEach
  void setUp() {
    runner = new FileSystemRunnerImpl();
  }

  @Test
  void unknownCommand() {
    CommandResult res = runner.executeCommands(List.of("foo")).get(0);
    assertFalse(res.success());
    assertEquals("unknown command: foo", res.message());
  }

  @Test
  void cdFailures() {
    List<CommandResult> results = runner.executeCommands(List.of("cd", "cd a b"));
    assertAll(
        () -> {
          CommandResult r0 = results.get(0);
          assertFalse(r0.success());
          assertEquals("cd: missing operand", r0.message());
        },
        () -> {
          CommandResult r1 = results.get(1);
          assertFalse(r1.success());
          assertEquals("cd: too many operands", r1.message());
        });
  }

  @Test
  void rmFailures() {
    List<CommandResult> results = runner.executeCommands(List.of("rm", "rm --recursive", "rm a b"));
    assertAll(
        () -> {
          CommandResult r0 = results.get(0);
          assertFalse(r0.success());
          assertEquals("rm: missing operand", r0.message());
        },
        () -> {
          CommandResult r1 = results.get(1);
          assertFalse(r1.success());
          assertEquals("rm: missing target for --recursive", r1.message());
        },
        () -> {
          CommandResult r2 = results.get(2);
          assertFalse(r2.success());
          assertEquals("rm: too many operands", r2.message());
        });
  }

  @Test
  void mkdirFailures() {
    List<CommandResult> results = runner.executeCommands(List.of("mkdir", "mkdir x y"));
    assertAll(
        () -> {
          CommandResult r0 = results.get(0);
          assertFalse(r0.success());
          assertEquals("mkdir: missing operand", r0.message());
        },
        () -> {
          CommandResult r1 = results.get(1);
          assertFalse(r1.success());
          assertEquals("mkdir: too many operands", r1.message());
        });
  }

  @Test
  void touchFailures() {
    List<CommandResult> results = runner.executeCommands(List.of("touch", "touch one two"));
    assertAll(
        () -> {
          CommandResult r0 = results.get(0);
          assertFalse(r0.success());
          assertEquals("touch: missing operand", r0.message());
        },
        () -> {
          CommandResult r1 = results.get(1);
          assertFalse(r1.success());
          assertEquals("touch: too many operands", r1.message());
        });
  }

  @Test
  void pwdFailures() {
    CommandResult res = runner.executeCommands(List.of("pwd now")).get(0);
    assertFalse(res.success());
    assertEquals("pwd: too many operands", res.message());
  }
}
