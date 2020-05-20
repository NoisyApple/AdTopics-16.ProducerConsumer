package com.milkyblue;

import java.util.Random;

public class Consumer implements Runnable {

  private final static Random generator = new Random();
  private final Buffer sharedBuffer;

  public Consumer(Buffer sharedBuffer) {
    this.sharedBuffer = sharedBuffer;
  }

  public void run() {
    int sum = 0;

    for (int count = 1; count <= 10; count++) {

      try {
        Thread.sleep(generator.nextInt(3000));
        sum += sharedBuffer.take();
        // System.out.println("\t\t\t" + sum);
      } catch (InterruptedException exception) {
        exception.printStackTrace();
      }

    }

    System.out.println("\nConsumer read values, total: " + sum + "\nTerminating consumer\n");

  }
}