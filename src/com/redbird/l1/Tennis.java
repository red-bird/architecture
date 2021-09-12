package com.redbird.l1;

public class Tennis {

    boolean flag = false;

    public static void main(String[] args) {
        Tennis tennis = new Tennis();
        Pinger pinger = new Pinger(tennis);
        Ponger ponger = new Ponger(tennis);
        new Thread(pinger).start();
        new Thread(ponger).start();
    }

    public synchronized void ping() {
        if (flag) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        flag = true;
        System.out.println("PING");
        notify();
    }

    public synchronized void pong() {
        if (!flag) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        flag = false;
        System.out.println("PONG");
        notify();
    }
}

class Pinger implements  Runnable{

    Tennis tennis;

    public Pinger(Tennis tennis) {
        this.tennis = tennis;
    }

    @Override
    public void run() {
        while (true) {
            tennis.ping();
        }
    }
}


class Ponger implements Runnable {

    Tennis tennis;

    public Ponger(Tennis tennis) {
        this.tennis = tennis;
    }

    @Override
    public void run() {
        while (true) {
            tennis.pong();
        }
    }
}
