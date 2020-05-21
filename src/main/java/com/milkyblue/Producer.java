package com.milkyblue;

import java.util.Random;

import com.github.tomaslanger.chalk.Chalk;

// Producer class. Models a Producer object that will be storing values into a passed Buffer.
public class Producer implements Runnable {

  private final static Random generator = new Random();
  private final Buffer sharedBuffer;
  private final boolean isSync;

  // Class constructor. Takes a Buffer object where the producer will be working
  // on. Also get a boolean value to specify if the Buffer is working with
  // synchronized approach.
  public Producer(Buffer sharedBuffer, boolean isSync) {
    this.sharedBuffer = sharedBuffer;
    this.isSync = isSync;
  }

  // run method is called when a thread based in an instance of Producer is
  // executed by an ExecutorService.
  public void run() {

    int sum = 0;

    for (int count = 1; count <= 10; count++) {

      try {
        // Waits up to 3 seconds before each iteration.
        Thread.sleep(generator.nextInt(3000));
        // Adds the new value into the Buffer.
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