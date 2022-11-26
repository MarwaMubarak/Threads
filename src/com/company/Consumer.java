package com.company;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;

public class Consumer implements Runnable {
    int n;
    Buffer buffer;
    String fileName;
    long startTime;
    String mxPrime;
    int count = 0;
    boolean close =false;

    public Consumer(int n, Buffer buffer, String fileName, long startTime,boolean close) {
        this.n = n;
        this.buffer = buffer;
        this.fileName = fileName;
        this.startTime = startTime;
        this.close=close;

    }


    @Override
    public void run() {
        String word = " ";
        try {
            FileWriter fileWriter = new FileWriter(fileName);
            while (true) {
                if (buffer.numOfElements == 0 && buffer.finish &&!close) {
                    long endTime = System.currentTimeMillis();
                    fileWriter.close();
                    JFrame frame_ = new JFrame("Output Screen ^_^");
                    JLabel l1, o1, l2, o2, l3, o3;
                    l1 = new JLabel("The Largest Prime Number:                        ");
                    l1.setBounds(30, 20, 300, 30);

                    o1 = new JLabel(mxPrime);
                    o1.setBounds(310, 20, 40, 30);

                    l2 = new JLabel("# Of Elements (Prime Number) Generated:          ");
                    l2.setBounds(30, 60, 300, 30);

                    o2 = new JLabel(Integer.toString(count));
                    o2.setBounds(310, 60, 40, 30);

                    l3 = new JLabel("Time Elapsed Since The Start Of Processing:      ");
                    l3.setBounds(30, 100, 300, 30);

                    o3 = new JLabel(String.valueOf(endTime - startTime) + " ms");
                    o3.setBounds(310, 100, 200, 30);

                    frame_.add(l1);
                    frame_.add(o1);
                    frame_.add(l2);
                    frame_.add(o2);
                    frame_.add(l3);
                    frame_.add(o3);
                    JButton b = new JButton("Finish");
                    b.setBounds(350, 150, 100, 30);
                    b.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            System.exit(0);
                        }
                    });

                    frame_.add(b);
                    frame_.setSize(500, 250);
                    frame_.setLayout(null);
                    frame_.setVisible(true);
                    //close=true;
                    TimeUnit.SECONDS.sleep(40);
                    System.exit(0);



                } else {
                    word = buffer.remove();
                    mxPrime = word;
                    count++;
                    fileWriter.write("\" " + word + " \" , ");
                }


            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
