package com.shl.ssa.shop.product.inspection;

import java.util.LinkedList;

/**
 * @author 施海林
 * @create 2023-08-23 11:24
 * @Description
 */
public class ProducerConsumer {

    private LinkedList<Integer> buffer = new LinkedList<>();
    private final int MAX_SIZE = 10;

    public void start() {
        Thread producerThread = new Thread(new Producer());
        Thread consumerThread = new Thread(new Consumer());

        producerThread.start();
        consumerThread.start();
    }

    class Producer implements Runnable {
        public void run() {
            int value = 0;
            while (true) {
                synchronized (buffer) {
                    while (buffer.size() == MAX_SIZE) {
                        try {
                            buffer.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    buffer.add(value);
                    System.out.println("Producer produced: " + value);
                    value++;
                    buffer.notifyAll();
                }
            }
        }
    }

    class Consumer implements Runnable {
        public void run() {
            while (true) {
                synchronized (buffer) {
                    while (buffer.isEmpty()) {
                        try {
                            buffer.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    int value = buffer.removeFirst();
                    System.out.println("Consumer consumed: " + value);
                    buffer.notifyAll();
                }
            }
        }
    }

    public static void main(String[] args) {
        ProducerConsumer pc = new ProducerConsumer();
        pc.start();
    }
}




