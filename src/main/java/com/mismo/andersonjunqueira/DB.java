package com.mismo.andersonjunqueira;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DB {

  private List<Map<String, String>> states = new ArrayList<>();

  public boolean isTransactionActive() {
    return states.size() > 1;
  }

  public Map<String, String> getCurrentState() {
    if (states.size() == 0) states.add(new HashMap<>());
    return states.get(states.size() - 1);
  }

  public void startTransaction() {
    Map<String, String> currentState = getCurrentState();
    Map<String, String> newState = new HashMap<>();
    currentState.entrySet().stream()
        .forEach(
            entry -> {
              newState.put(new String(entry.getKey()), new String(entry.getValue()));
            });
    states.add(newState);
  }

  public void commit() {
    Map<String, String> currentState = getCurrentState();
    states.clear();
    states.add(currentState);
  }

  public void rollback() {
    states.remove(states.size() - 1);
  }
}
