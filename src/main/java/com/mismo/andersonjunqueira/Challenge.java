package com.mismo.andersonjunqueira;

public class Challenge {

  public static void main(String[] args) {
    InMemoryDB db = new InMemoryDB();
    db.init();
    db.read();
  }
}
