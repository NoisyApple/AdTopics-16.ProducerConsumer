package com.milkyblue;

// Buffer interface. Models a Buffer object with two actions, put and take.
public interface Buffer {
  public void put(int value) throws InterruptedException;

  public int take() throws InterruptedException;
}