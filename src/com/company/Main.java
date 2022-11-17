package com.company;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        Scanner input =new Scanner(System.in);
	    int n =input.nextInt();
        int bufferSize=input.nextInt();
        Buffer buffer =new Buffer(bufferSize);
//
//        FileWriter fileWriter =new FileWriter("marwa.txt");
//        fileWriter.write("Ahhhhhhhhhhhhhhhhhhhhhh");
//        fileWriter.write("  ahhhhhhhhhhh tany");
//        fileWriter.close();
        Thread producer =new Thread(new Producer(n,buffer));
        Thread consumer =new Thread(new Consumer(n,buffer,"test.txt"));
        producer.start();
        consumer.start();



    }
}
