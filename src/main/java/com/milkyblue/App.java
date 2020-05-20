package com.milkyblue;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class App {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newCachedThreadPool();

        // Buffer sharedBuffer = new UnSyncBuffer();
        Buffer sharedBuffer = new BlockingBuffer();

        // System.out.println("Action\t\t\tValue\tProduced sum\tConsumed sum");
        // System.out.println("------\t\t\t-----\t------------\t------------");

        executor.execute(new Producer(sharedBuffer));
        executor.execute(new Consumer(sharedBuffer));

        executor.shutdown();
    }
}
