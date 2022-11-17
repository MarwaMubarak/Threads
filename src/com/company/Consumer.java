package com.company;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Consumer implements Runnable{
    int n;
    Buffer buffer;
    String fileName;

    public Consumer(int n, Buffer buffer, String fileName) {
        this.n = n;
        this.buffer = buffer;
        this.fileName = fileName;
    }


    @Override
    public void run() {
        String word=" ";
        try {
            FileWriter fileWriter = new FileWriter(fileName);
            while (true){
                if(buffer.numOfElements == 0 && buffer.finish){
                    fileWriter.close();
                    System.out.println("close");
                    break;
                }
                else{
                    System.out.println("before done");
                    word = buffer.remove();
                    fileWriter.write("\" " + word + " \" , ");
                    System.out.println("done");
                }


            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
