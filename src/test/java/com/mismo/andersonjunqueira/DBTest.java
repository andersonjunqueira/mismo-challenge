package com.mismo.andersonjunqueira;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class DBTest {

  private InMemoryDB db;

  @Test
  public void block1() {
    db = new InMemoryDB();
    db.init();
    String[][] commands =
        new String[][] {
          {"SET x 10", null},
          {"GET x", "10"},
          {"UNSET x", null},
          {"GET x", "NULL"},
        };
    run(commands);
  }

  @Test
  public void block2() {
    db = new InMemoryDB();
    db.init();
    String[][] commands =
        new String[][] {
          {"SET a 10", null},
          {"SET b 10", null},
          {"NUMEQUALTO 10", "2"},
          {"NUMEQUALTO 20", "0"},
          {"SET b 30", null},
          {"NUMEQUALTO 10", "1"},
        };
    run(commands);
  }

  @Test
  public void block3() {
    db = new InMemoryDB();
    db.init();
    String[][] commands =
        new String[][] {
          {"BEGIN", null},
          {"SET a 10", null},
          {"GET a", "10"},
          {"BEGIN", null},
          {"SET a 20", null},
          {"GET a", "20"},
          {"ROLLBACK", null},
          {"GET a", "10"},
          {"ROLLBACK", null},
          {"GET a", "NULL"},
        };
    run(commands);
  }

  @Test
  public void block4() {
    db = new InMemoryDB();
    db.init();
    String[][] commands =
        new String[][] {
          {"BEGIN", null},
          {"SET a 30", null},
          {"BEGIN", null},
          {"SET a 40", null},
          {"COMMIT", null},
          {"GET a", "40"},
          {"ROLLBACK", "NO TRANSACTION"},
        };
    run(commands);
  }

  @Test
  public void block5() {
    db = new InMemoryDB();
    db.init();
    String[][] commands =
        new String[][] {
          {"SET a 50", null},
          {"BEGIN", null},
          {"GET a", "50"},
          {"SET a 60", null},
          {"BEGIN", null},
          {"UNSET a", null},
          {"GET a", "NULL"},
          {"ROLLBACK", null},
          {"GET a", "60"},
          {"COMMIT", null},
          {"GET a", "60"},
        };
    run(commands);
  }

  @Test
  public void block6() {
    db = new InMemoryDB();
    db.init();
    String[][] commands =
        new String[][] {
          {"SET a 10", null},
          {"BEGIN", null},
          {"NUMEQUALTO 10", "1"},
          {"BEGIN", null},
          {"UNSET a", null},
          {"NUMEQUALTO 10", "0"},
          {"ROLLBACK", null},
          {"NUMEQUALTO 10", "1"},
          {"COMMIT", null},
        };
    run(commands);
  }

  private void run(String[][] commands) {
    Arrays.stream(commands)
        .forEach(
            command -> {
              String output = db.runCommand(command[0]);
              if (command[1] == null) assertNull(output);
              else assertEquals(output, command[1]);

              System.out.println("> " + command[0]);
              if (output != null) System.out.println(output);
            });
  }
}
