package com.mismo.andersonjunqueira.commands;

import com.mismo.andersonjunqueira.DB;

public class SET extends Executor {

  @Override
  public boolean isValid(String[] args) {
    if (args == null || args.length < 2) {
      System.out.println("Invalid command. Use:");
      System.out.println("> SET <variable> <value>");
      return false;
    }
    return true;
  }

  @Override
  public String run(DB db, String[] args) {
    db.getCurrentState().put(args[0], args[1]);
    return null;
  }
}
