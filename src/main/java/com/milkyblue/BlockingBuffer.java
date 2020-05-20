package com.milkyblue;

import java.util.concurrent.ArrayBlockingQueue;

public class BlockingBuffer implements Buffer {

  private final ArrayBlockingQueue<Integer> buffer;

  public BlockingBuffer() {
    buffer = new ArrayBlockingQueue<Integer>(1);
  }

  public void put(int value) throws InterruptedException {
    buffer.put(value);
    System.out.print("Producer writes:\t" + value + "\tCells taken: " + buffer.size() + "\n");
  }

  public int take() throws InterruptedException {
    int readValue = buffer.take();
    System.out.print("Consumer reads:\t\t" + readValue + "\tCells taken: " + buffer.size() + "\n");
    return readValue;
  }

}