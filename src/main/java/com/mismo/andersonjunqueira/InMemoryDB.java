package com.mismo.andersonjunqueira;

import com.mismo.andersonjunqueira.commands.BEGIN;
import com.mismo.andersonjunqueira.commands.COMMIT;
import com.mismo.andersonjunqueira.commands.END;
import com.mismo.andersonjunqueira.commands.Executor;
import com.mismo.andersonjunqueira.commands.GET;
import com.mismo.andersonjunqueira.commands.NUMEQUALTO;
import com.mismo.andersonjunqueira.commands.ROLLBACK;
import com.mismo.andersonjunqueira.commands.SET;
import com.mismo.andersonjunqueira.commands.UNSET;

import java.util.Arrays;
import java.util.Scanner;

public class InMemoryDB {

  DB db = new DB();

  public void init() {
    db = new DB();

    System.out.println("WELCOME to the In-Memory DB");
    System.out.println("Please, type in your command after the '>' and press ENTER:");
  }

  public void read() {
    Scanner sc = new Scanner(System.in);
    while (true) {
      System.out.print("> ");
      String command = sc.nextLine();
      String output = runCommand(command);
      if (output != null) System.out.println(output);
    }
  }

  public String runCommand(String line) {
    if (line == null || line.isEmpty()) {
      return "Invalid command.";
    }

    String[] args = line.split(" ");
    String command = args[0].toUpperCase();
    args = Arrays.copyOfRange(args, 1, args.length);

    try {
      Command executor = Command.valueOf(command);
      if (executor.get().isValid(args)) return executor.get().run(db, args);
    } catch (IllegalArgumentException ex) {
      return "Invalid command.";
    }

    return "";
  }

  private enum Command {
    END(new END()),
    SET(new SET()),
    GET(new GET()),
    UNSET(new UNSET()),
    NUMEQUALTO(new NUMEQUALTO()),
    BEGIN(new BEGIN()),
    COMMIT(new COMMIT()),
    ROLLBACK(new ROLLBACK()),
    ;

    private Executor command;

    private Command(Executor command) {
      this.command = command;
    }

    public Executor get() {
      return command;
    }
  }
}
