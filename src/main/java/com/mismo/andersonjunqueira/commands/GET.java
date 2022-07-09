package com.mismo.andersonjunqueira.commands;

import com.mismo.andersonjunqueira.DB;

public class GET extends Executor {

  @Override
  public boolean isValid(String[] args) {
    if (args == null || args.length < 1) {
      System.out.println("Invalid command. Use:");
      System.out.println("> GET <variable>");
      return false;
    }
    return true;
  }

  @Override
  public String run(DB db, String[] args) {
    String output = db.getCurrentState().get(args[0]);
    return output == null ? "NULL" : output;
  }
}
