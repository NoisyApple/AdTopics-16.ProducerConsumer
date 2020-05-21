package com.milkyblue;

import java.util.Random;

import com.github.tomaslanger.chalk.Chalk;

// Consumer class. Models a Consumer object that reads data from a passed Buffer.
public class Consumer implements Runnable {

  private final static Random generator = new Random();
  private final Buffer sharedBuffer;
  private final boolean isSync;

  // Class constructor. Takes a Buffer object where the Consumer will be working
  // on. Also a boolean value is passed to keep track of the CheckBox state.
  public Consumer(Buffer sharedBuffer, boolean isSync) {
    this.sharedBuffer = sharedBuffer;
    this.isSync = isSync;
  }

  // run method is called when a Thread based on an instance of Consumer is
  // executed by an ExecutorService.
  public void run() {
    int sum = 0;

    for (int count = 1; count <= 10; count++) {

      try {
        // Waits up to 3 seconds before reading the Buffer.
        Thread.sleep(generator.nextInt(3000));
        // Takes a value from the Buffer.
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