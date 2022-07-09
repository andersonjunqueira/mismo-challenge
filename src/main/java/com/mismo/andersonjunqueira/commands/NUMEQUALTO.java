package com.mismo.andersonjunqueira.commands;

import com.mismo.andersonjunqueira.DB;

public class NUMEQUALTO extends Executor {

  @Override
  public boolean isValid(String[] args) {
    if (args == null || args.length < 1) {
      System.out.println("Invalid command. Use:");
      System.out.println("> NUMEQUALTO <variable>");
      return false;
    }
    return true;
  }

  @Override
  public String run(DB db, String[] args) {
    long output =
        db.getCurrentState().entrySet().stream()
            .filter(entry -> entry.getValue().equals(args[0]))
            .count();
    return Long.toString(output);
  }
}
