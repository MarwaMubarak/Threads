package com.company;

import java.util.LinkedList;
import java.util.Queue;

public class Buffer {


    private Queue<String> buffer = new LinkedList<String>();
    private int size;
    int numOfElements = 0;
    boolean finish = false;
    String word = "";

    public Buffer(int size) {
        this.size = size;
    }

    public void add(String num) {
        synchronized (this) {
            while (buffer.size() == size) {
                try {

                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            this.buffer.add(num);
            numOfElements++;
            notify();
        }

    }

    public synchronized String remove() {

        while (buffer.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        word = buffer.remove();
        numOfElements--;
        notify();
        return word;
    }


/**

 private int size;
 public Buffer(int size) {
 this.size = size;
 buffer= new String[size];
 }

 private String buffer [];
 private int inCnt = 0;
 private int outCnt = 0;
 Semaphore spaces = new Semaphore(size);
 Semaphore elements = new Semaphore(0);
 public void produce(String value) {
 spaces.add();
 buffer[inCnt] = value;
 inCnt = (inCnt + 1) % size;
 elements.remove();
 }
 public String consume() {
 String value;
 elements.add();
 value = buffer[outCnt];
 outCnt = (outCnt + 1) % size;
 spaces.remove();
 return value;
 }
 **/
}
