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
    assertFalse(res.isSuccess());
    assertEquals("unknown command: foo", res.getMessage());
  }

  @Test
  void cdFailures() {
    List<CommandResult> results = runner.executeCommands(List.of("cd", "cd a b"));
    assertAll(
        () -> {
          CommandResult r0 = results.get(0);
          assertFalse(r0.isSuccess());
          assertEquals("cd: missing operand", r0.getMessage());
        },
        () -> {
          CommandResult r1 = results.get(1);
          assertFalse(r1.isSuccess());
          assertEquals("cd: too many operands", r1.getMessage());
        });
  }

  @Test
  void rmFailures() {
    List<CommandResult> results = runner.executeCommands(List.of("rm", "rm --recursive", "rm a b"));
    assertAll(
        () -> {
          CommandResult r0 = results.get(0);
          assertFalse(r0.isSuccess());
          assertEquals("rm: missing operand", r0.getMessage());
        },
        () -> {
          CommandResult r1 = results.get(1);
          assertFalse(r1.isSuccess());
          assertEquals("rm: missing target for --recursive", r1.getMessage());
        },
        () -> {
          CommandResult r2 = results.get(2);
          assertFalse(r2.isSuccess());
          assertEquals("rm: too many operands", r2.getMessage());
        });
  }

  @Test
  void mkdirFailures() {
    List<CommandResult> results = runner.executeCommands(List.of("mkdir", "mkdir x y"));
    assertAll(
        () -> {
          CommandResult r0 = results.get(0);
          assertFalse(r0.isSuccess());
          assertEquals("mkdir: missing operand", r0.getMessage());
        },
        () -> {
          CommandResult r1 = results.get(1);
          assertFalse(r1.isSuccess());
          assertEquals("mkdir: too many operands", r1.getMessage());
        });
  }

  @Test
  void touchFailures() {
    List<CommandResult> results = runner.executeCommands(List.of("touch", "touch one two"));
    assertAll(
        () -> {
          CommandResult r0 = results.get(0);
          assertFalse(r0.isSuccess());
          assertEquals("touch: missing operand", r0.getMessage());
        },
        () -> {
          CommandResult r1 = results.get(1);
          assertFalse(r1.isSuccess());
          assertEquals("touch: too many operands", r1.getMessage());
        });
  }

  @Test
  void pwdFailures() {
    CommandResult res = runner.executeCommands(List.of("pwd now")).get(0);
    assertFalse(res.isSuccess());
    assertEquals("pwd: too many operands", res.getMessage());
  }
}
