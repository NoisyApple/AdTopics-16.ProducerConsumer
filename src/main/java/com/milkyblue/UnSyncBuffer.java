package com.milkyblue;

import com.github.tomaslanger.chalk.Chalk;

public class UnSyncBuffer implements Buffer {

  private int buffer = -1;

  public void put(int value) throws InterruptedException {
    System.out.print("[" + Chalk.on("Producer").cyan() + "] writes\t" + Chalk.on(Integer.toString(value)).green());
    buffer = value;
  }

  public int take() throws InterruptedException {
    System.out.print("[" + Chalk.on("Consumer").yellow() + "] reads\t" + Chalk.on(Integer.toString(buffer)).green());
    return buffer;
  }
}