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

public class ProducerConsumerGUI {

  private JFrame mainFrame;
  private JPanel mainPanel, topPanel, bottomPanel;
  private JCheckBox chkSync;
  private JButton btnExecute;

  public ProducerConsumerGUI() {
    Chalk.setColorEnabled(true);

    mainFrame = new JFrame("Producer Consumer");
    mainPanel = new JPanel(new BorderLayout());
    topPanel = new JPanel();
    bottomPanel = new JPanel();
    chkSync = new JCheckBox("Enable Synchronization");
    btnExecute = new JButton("Execute");

    addAttributes();
    addListeners();
    build();
    launch();
  }

  private void addAttributes() {
    mainFrame.setResizable(false);
    mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }

  private void addListeners() {
    btnExecute.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {

        boolean isSync = chkSync.isSelected();

        ExecutorService executor = Executors.newCachedThreadPool();

        Buffer sharedBuffer = (isSync) ? new BlockingBuffer() : new UnSyncBuffer();

        System.out.print("\033[H\033[2J");
        System.out.flush();

        if (!isSync) {
          System.out.println(Chalk.on("Action").bgMagenta() + "\t\t\t" + Chalk.on("Value").bgMagenta() + "\t"
              + Chalk.on("Produced sum").bgMagenta() + "\t" + Chalk.on("Consumed sum").bgMagenta());
          System.out.println("------\t\t\t-----\t------------\t------------");
        }

        executor.execute(new Producer(sharedBuffer, isSync));
        executor.execute(new Consumer(sharedBuffer, isSync));

        executor.shutdown();

      }
    });
  }

  private void build() {
    topPanel.add(chkSync);
    bottomPanel.add(btnExecute);

    mainPanel.add(topPanel, BorderLayout.NORTH);
    mainPanel.add(bottomPanel, BorderLayout.SOUTH);

    mainFrame.add(mainPanel);
  }

  private void launch() {
    mainFrame.setVisible(true);
    mainFrame.pack();
    mainFrame.setLocationRelativeTo(null);
  }

}