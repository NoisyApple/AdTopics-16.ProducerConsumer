package com.milkyblue;

import java.util.concurrent.ArrayBlockingQueue;

import com.github.tomaslanger.chalk.Chalk;

// BlockingBuffer class. Models a syncrhonized Buffer based object that works over an 
// ArrayBlockingQueue instance.
public class BlockingBuffer implements Buffer {

  private final ArrayBlockingQueue<Integer> buffer;

  // Class constructor.
  public BlockingBuffer() {
    buffer = new ArrayBlockingQueue<Integer>(1);
  }

  // put method is overwritten, adds a passed value to buffer.
  public void put(int value) throws InterruptedException {
    buffer.put(value);
    System.out.print("[" + Chalk.on("Producer").cyan() + "] writes:\t" + Chalk.on(Integer.toString(value)).green()
        + "\tCells taken: " + Chalk.on(Integer.toString(buffer.size())).green() + "\n");
  }

  // take method is overwritten, takes a value from buffer.
  public int take() throws InterruptedException {
    int readValue = buffer.take();
    System.out.print("[" + Chalk.on("Consumer").yellow() + "] reads:\t" + Chalk.on(Integer.toString(readValue)).green()
        + "\tCells taken: " + Chalk.on(Integer.toString(buffer.size())).green() + "\n");
    return readValue;
  }

}