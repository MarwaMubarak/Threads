package com.company;

public class Producer implements Runnable{

    private boolean isPrime(int x){
        int cnt=0;

        for(int i=1;i<=x;i++){
            if(x%i==0)
                cnt++;
        }
        return (cnt==2);
    }

    int n;
    int cnt=0;
    Buffer buffer;

    public Producer(int n, Buffer buffer) {
        this.n = n;
        this.buffer =buffer;
    }

    @Override
    public void run() {
        for (int i=2;i<=n;i++){
            if(isPrime(i)){
                buffer.add(Integer.toString(i));
                cnt++;
            }

        }
        buffer.finish=true;

    }
}
