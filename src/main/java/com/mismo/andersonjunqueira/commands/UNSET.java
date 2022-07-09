package com.mismo.andersonjunqueira.commands;

import com.mismo.andersonjunqueira.DB;

public class UNSET extends Executor {

  @Override
  public boolean isValid(String[] args) {
    if (args == null || args.length < 1) {
      System.out.println("Invalid command. Use:");
      System.out.println("> UNSET <variable>");
      return false;
    }
    return true;
  }

  @Override
  public String run(DB db, String[] args) {
    db.getCurrentState().remove(args[0]);
    return null;
  }
}
