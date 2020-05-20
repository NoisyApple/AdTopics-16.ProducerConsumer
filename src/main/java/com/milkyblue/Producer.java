package com.milkyblue;

import java.util.Random;

import com.github.tomaslanger.chalk.Chalk;

public class Producer implements Runnable {
  private final static Random generator = new Random();
  private final Buffer sharedBuffer;
  private final boolean isSync;

  public Producer(Buffer sharedBuffer, boolean isSync) {
    this.sharedBuffer = sharedBuffer;
    this.isSync = isSync;
  }

  public void run() {
    int sum = 0;

    for (int count = 1; count <= 10; count++) {

      try {
        Thread.sleep(generator.nextInt(3000));
        sharedBuffer.put(count);
        sum += count;

        if (!isSync)
          System.out.println("\t" + Chalk.on(Integer.toString(sum)).cyan());

      } catch (InterruptedException exception) {
        exception.printStackTrace();
      }

    }

    System.out.println("\n[" + Chalk.on("Producer").cyan() + "] stopped producing. Terminating...\n");

  }
}