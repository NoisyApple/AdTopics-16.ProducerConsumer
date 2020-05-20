package com.milkyblue;

public interface Buffer {
  public void put(int value) throws InterruptedException;

  public int take() throws InterruptedException;
}