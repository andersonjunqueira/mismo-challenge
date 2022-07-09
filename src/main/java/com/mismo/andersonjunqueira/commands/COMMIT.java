package com.mismo.andersonjunqueira.commands;

import com.mismo.andersonjunqueira.DB;

public class COMMIT extends Executor {

  @Override
  public boolean isValid(String[] args) {
    if (args == null || args.length > 0) {
      System.out.println("Invalid command. Use:");
      System.out.println("> COMMIT");
      return false;
    }
    return true;
  }

  @Override
  public String run(DB db, String[] args) {
    if (!db.isTransactionActive()) return "NO TRANSACTION";
    else db.commit();
    return null;
  }
}
