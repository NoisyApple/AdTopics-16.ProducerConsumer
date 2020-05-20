# [Advanced Programming Topics] #16 Producer/Consumer

This is the **16th** project part of my Advanced Programming Topics course at Leon Institute of Thechnology :mortar_board::lion:.

## :dart:GOAL

Show the functionality of a program that uses **thread synchronization** through the **Producer/Consumer** model.

## :eyeglasses:BRIEF EXPLANATION

The GUI consists simply of a `CheckBox` that allows us to select if the program runs with the `synchronized` way or not, and also a button to execute the program.

So, the **Producer/Consumer** model works in the following way:

- The **Producer** element produces data that is then stored in a **shared resource** 📥.
- The **Consumer** elements consumes the produced data stored in the **shared resource** 📤.

That **shared resource** works as a **buffer**, that stores data from the **producer** waiting for a previous use, this way the **consumer** can be processing the actual retrieved data from the **buffer** and when it finishes it will just take the next data until the **buffer** is empty.

The problem comes when the **Producer** and the **Consumer** access the **buffer** at the same time, the same problem as the previous project, where we were accesing to the a resource from two parallel threads, that then its going to produce unexpected behaviours on our program. By the way, we already know that the problem can be solved usin **thread synchronization**.

---

Now that we have a basic idea of how the **Producer/Consumer** model works, its time for the actual program functionality.

So, the program consists of a **producer** that will store in a **buffer** a sequence of 10 values, from 1 to 10 while a **consumer** will also take 10 from the same **buffer** and sum those values, both of them in a random interval of time up to 3 seconds between each iteration, expecting a result of 55.

### Non Synchronized Producer/Consumer: 📥📤🚫🔄

To run the program in a non-synchronized way we just need to press the **execute** button leaving the checkbox **unchecked**.

<p align="center">
<img src="./snapshots/Annotation 2020-05-20 080157.png"/>
</p>

Giving the next output:

<p align="center">
<img src="./snapshots/Annotation 2020-05-20 090553.png"/>
</p>

The output shows how the **producer** and the **consumer** reads and writes data into the **buffer**, but because of the waiting time between each iteration we get those unexpected behaviours mentioned before, producing the next cases:

- **Consumer** reading before any value is inserted into the **buffer**
- Values being ignored.
- Values being read multiple times.

Resulting in a value totally different of what we expected, leading us to the conclusion that it will be too hard to get the correct result.

### Synchronized Producer/Consumer: 📥📤🔄

To get an acceptable solution we will be using the `ArrayBlockingQueue` class, this class already incorporates a correct implementation of the **thread synchronization** concept, actually, when the **producer** stores a value in the **buffer** he can no longer store another one until the buffer is empty again, in the same way, the **consumer** will be able to read from the **buffer** only when it has something stored, if not then it will wait, fitting perfectly for the program's purpose.

Now, to run the program using the synchronized solution we have to check the **"Enable synchronization"** checkbox and press the **"Execute"** button.

<p align="center">
<img src="./snapshots/Annotation 2020-05-20 165803.png"/>
</p>

Giving the next output:

<p align="center">
<img src="./snapshots/Annotation 2020-05-20 170209.png"/>
</p>

As we can see, now each thread (producer and consumer) runs in a synchronized way, using the **buffer** only when they should and preventing a thread to execute its operation multiple times before the other thread, this way resulting on the expected output of 55.
