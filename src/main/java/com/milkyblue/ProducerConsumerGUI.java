package com.milkyblue;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.github.tomaslanger.chalk.Chalk;

// ProduceerConsumerGUI class. Models the GUI.
public class ProducerConsumerGUI {

  private JFrame mainFrame;
  private JPanel mainPanel, topPanel, bottomPanel;
  private JCheckBox chkSync;
  private JButton btnExecute;

  // Class constructor.
  public ProducerConsumerGUI() {
    // Enables color on terminal outputs.
    Chalk.setColorEnabled(true);

    mainFrame = new JFrame("Producer Consumer");
    mainPanel = new JPanel(new BorderLayout());
    topPanel = new JPanel();
    bottomPanel = new JPanel();
    chkSync = new JCheckBox("Enable Synchronization");
    btnExecute = new JButton("Execute");

    // Main methods are called.
    addAttributes();
    addListeners();
    build();
    launch();
  }

  // Adds attributes to elements in the class.
  private void addAttributes() {
    mainFrame.setResizable(false);
    mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }

  // Sets listeners to elements in GUI.
  private void addListeners() {

    // Creates a Buffer depending on the chkSync state, then with an ExecutorService
    // instance, executes a Producer and a Consumer that will be accessing to the
    // Buffer.
    btnExecute.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {

        boolean isSync = chkSync.isSelected();

        ExecutorService executor = Executors.newCachedThreadPool();

        // Buffer created based on chkSync state.
        Buffer sharedBuffer = (isSync) ? new BlockingBuffer() : new UnSyncBuffer();

        System.out.print("\033[H\033[2J");
        System.out.flush();

        if (!isSync) {
          System.out.println(Chalk.on("Action").bgMagenta() + "\t\t\t" + Chalk.on("Value").bgMagenta() + "\t"
              + Chalk.on("Produced sum").bgMagenta() + "\t" + Chalk.on("Consumed sum").bgMagenta());
          System.out.println("------\t\t\t-----\t------------\t------------");
        }

        // Thread execution.
        executor.execute(new Producer(sharedBuffer, isSync));
        executor.execute(new Consumer(sharedBuffer, isSync));
        executor.shutdown();

      }
    });
  }

  // Builds the GUI.
  private void build() {
    topPanel.add(chkSync);
    bottomPanel.add(btnExecute);

    mainPanel.add(topPanel, BorderLayout.NORTH);
    mainPanel.add(bottomPanel, BorderLayout.SOUTH);

    mainFrame.add(mainPanel);
  }

  // Launches the GUI by setting to true the visible attribute of the frame.
  private void launch() {
    mainFrame.setVisible(true);
    mainFrame.pack();
    mainFrame.setLocationRelativeTo(null);
  }

}