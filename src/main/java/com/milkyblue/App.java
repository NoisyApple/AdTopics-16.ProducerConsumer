package com.milkyblue;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.github.tomaslanger.chalk.Chalk;

public class App {
    public static void main(String[] args) {

        boolean isSync = false;

        Chalk.setColorEnabled(true);

        ExecutorService executor = Executors.newCachedThreadPool();

        Buffer sharedBuffer = (isSync) ? new BlockingBuffer() : new UnSyncBuffer();

        if (!isSync) {
            System.out.println(Chalk.on("Action").bgMagenta() + "\t\t\t" + Chalk.on("Value").bgMagenta() + "\t"
                    + Chalk.on("Produced sum").bgMagenta() + "\t" + Chalk.on("Consumed sum").bgMagenta());
            System.out.println("------\t\t\t-----\t------------\t------------");
        }

        executor.execute(new Producer(sharedBuffer, isSync));
        executor.execute(new Consumer(sharedBuffer, isSync));

        executor.shutdown();
    }
}
