package com.milkyblue;

public class UnSyncBuffer implements Buffer {

  private int buffer = -1;

  public void put(int value) throws InterruptedException {
    System.out.println("Producer writes:\t" + value);
    buffer = value;
  }

  public int take() throws InterruptedException {
    System.out.println("Consumer reads:\t" + buffer);
    return buffer;
  }
}