package com.milkyblue;

import java.util.Random;

import com.github.tomaslanger.chalk.Chalk;

public class Consumer implements Runnable {

  private final static Random generator = new Random();
  private final Buffer sharedBuffer;
  private final boolean isSync;

  public Consumer(Buffer sharedBuffer, boolean isSync) {
    this.sharedBuffer = sharedBuffer;
    this.isSync = isSync;
  }

  public void run() {
    int sum = 0;

    for (int count = 1; count <= 10; count++) {

      try {
        Thread.sleep(generator.nextInt(3000));
        sum += sharedBuffer.take();

        if (!isSync)
          System.out.println("\t\t\t" + Chalk.on(Integer.toString(sum)).yellow());

      } catch (InterruptedException exception) {
        exception.printStackTrace();
      }

    }

    System.out.println("\n[" + Chalk.on("Consumer").yellow() + "] read values, total: "
        + Chalk.on(Integer.toString(sum)).bgGreen().black() + ". Terminating...\n");

  }
}