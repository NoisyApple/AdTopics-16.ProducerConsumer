package com.milkyblue;

import com.github.tomaslanger.chalk.Chalk;

// UnSyncBuffer class. Models a non-syncrhonized Buffer based object.
public class UnSyncBuffer implements Buffer {

  private int buffer = -1;

  // put method is overwritten, adds a passed value to buffer.
  public void put(int value) throws InterruptedException {
    System.out.print("[" + Chalk.on("Producer").cyan() + "] writes\t" + Chalk.on(Integer.toString(value)).green());
    buffer = value;
  }

  // take method is overwritten, takes a value from buffer.
  public int take() throws InterruptedException {
    System.out.print("[" + Chalk.on("Consumer").yellow() + "] reads\t" + Chalk.on(Integer.toString(buffer)).green());
    return buffer;
  }
}