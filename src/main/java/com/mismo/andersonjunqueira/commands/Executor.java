package com.mismo.andersonjunqueira.commands;

import com.mismo.andersonjunqueira.DB;

public abstract class Executor {

  public boolean isValid(String[] args) {
    return true;
  }

  public abstract String run(DB db, String[] args);
}
