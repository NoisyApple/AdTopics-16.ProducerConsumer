package com.milkyblue;

import java.util.concurrent.ArrayBlockingQueue;

import com.github.tomaslanger.chalk.Chalk;

public class BlockingBuffer implements Buffer {

  private final ArrayBlockingQueue<Integer> buffer;

  public BlockingBuffer() {
    buffer = new ArrayBlockingQueue<Integer>(1);
  }

  public void put(int value) throws InterruptedException {
    buffer.put(value);
    System.out.print("[" + Chalk.on("Producer").cyan() + "] writes:\t" + Chalk.on(Integer.toString(value)).green()
        + "\tCells taken: " + Chalk.on(Integer.toString(buffer.size())).green() + "\n");
  }

  public int take() throws InterruptedException {
    int readValue = buffer.take();
    System.out.print("[" + Chalk.on("Consumer").yellow() + "] reads:\t" + Chalk.on(Integer.toString(readValue)).green()
        + "\tCells taken: " + Chalk.on(Integer.toString(buffer.size())).green() + "\n");
    return readValue;
  }

}