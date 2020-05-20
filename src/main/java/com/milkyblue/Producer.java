package com.milkyblue;

import java.util.Random;

public class Producer implements Runnable {
  private final static Random generator = new Random();
  private final Buffer sharedBuffer;

  public Producer(Buffer sharedBuffer) {
    this.sharedBuffer = sharedBuffer;
  }

  public void run() {
    int sum = 0;

    for (int count = 1; count <= 10; count++) {

      try {
        Thread.sleep(generator.nextInt(3000));
        sharedBuffer.put(count);
        sum += count;
        // System.out.println("\t" + sum);
      } catch (InterruptedException exception) {
        exception.printStackTrace();
      }

    }

    System.out.println("\nProducer stopped producing\nTerminating produceer\n");

  }
}