package com.example.demo.doit;



import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class test {

    private static int piao = 100;
    private static Object object = new Object();


    public static class m1 implements Runnable {
        @Override
        public void run() {
            sell();
        }
        private void sell(){
            while (true){
                synchronized (object){
                    if(piao==0){
                        break;
                    }
                    System.out.println(Thread.currentThread().getName()+":"+piao);
                    piao--;
                }

            }
        }

    }

    public static class m2 implements Runnable{
        @Override
        public void run() {
            sell();
        }
        private void sell(){
            while (true){
                synchronized (object){
                    if(piao==0){
                        break;
                    }
                    System.out.println(Thread.currentThread().getName()+":"+piao);
                    piao--;
                }

            }
        }
    }


    public static void main(String[] args) {

        new Thread(new m1()).start();
        new Thread(new m2()).start();


    }
}
