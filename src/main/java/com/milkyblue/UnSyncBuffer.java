package com.milkyblue;

public class UnSyncBuffer implements Buffer {

  private int buffer = -1;

  public void put(int value) throws InterruptedException {
    System.out.print("Producer writes\t\t" + value);
    buffer = value;
  }

  public int take() throws InterruptedException {
    System.out.print("Consumer reads\t\t" + buffer);
    return buffer;
  }
}