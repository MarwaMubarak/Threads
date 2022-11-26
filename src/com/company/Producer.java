package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Producer implements Runnable {

    private boolean isPrime(int x) {
        int cnt = 0;
        for (int i = 2; i < x; i++) {
            if (x % i == 0)
                cnt++;
        }
        return (cnt == 0);
    }

    int n;
    int cnt = 0;
    Buffer buffer;
    long startTime;
    int mxPrime = 1;

    public Producer(int n, Buffer buffer, long startTime) {
        this.n = n;
        this.buffer = buffer;
        this.startTime = startTime;
    }

    @Override
    public void run() {
        for (int i = 1; i <= n; i++) {
            if (isPrime(i)) {
                buffer.add(Integer.toString(i));
                cnt++;
                mxPrime = i;
            }

        }
        buffer.finish = true;
        if (buffer.numOfElements == 0) {

            long endTime = System.currentTimeMillis();
            JFrame frame_ = new JFrame("Output Screen ^_^");
            JLabel l1, o1, l2, o2, l3, o3;
            l1 = new JLabel("The Largest Prime Number:                        ");
            l1.setBounds(30, 20, 300, 30);

            o1 = new JLabel(Integer.toString(mxPrime));
            o1.setBounds(310, 20, 40, 30);

            l2 = new JLabel("# Of Elements (Prime Number) Generated:          ");
            l2.setBounds(30, 60, 300, 30);

            o2 = new JLabel(Integer.toString(cnt));
            o2.setBounds(310, 60, 40, 30);

            l3 = new JLabel("Time Elapsed Since The Start Of Processing:      ");
            l3.setBounds(30, 100, 300, 30);

            o3 = new JLabel((String.valueOf(endTime - startTime) + " ms"));
            o3.setBounds(310, 100, 200, 30);

            JButton b = new JButton("Finish");
            b.setBounds(350, 150, 100, 30);
            b.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    System.exit(0);
                }
            });

            frame_.setSize(500, 250);
            frame_.add(l1);
            frame_.add(o1);
            frame_.add(l2);
            frame_.add(o2);
            frame_.add(l3);
            frame_.add(o3);
            frame_.add(b);


            frame_.setLayout(null);
            frame_.setVisible(true);
            try {
                TimeUnit.SECONDS.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.exit(0);

        }


    }
}
