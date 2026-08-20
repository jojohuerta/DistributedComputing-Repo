package ar.edu.itba.pod.concurrency.iii.pubsub;

import java.util.concurrent.BlockingQueue;

public class NumbersConsumer implements Runnable {
    private BlockingQueue<Integer> queue;
    private final int poisonPill;

    public NumbersConsumer(BlockingQueue<Integer> queue, int poisonPill) {
        this.queue = queue;
        this.poisonPill = poisonPill;
    }
//Que un consumer sea thread safe es que lo sea en cualquier contexto
    public void run() {
        try {
            //int suma = 0;

            while (true) {
                Integer number = queue.take();
                if (number.equals(poisonPill)) {
                    return;
                }
                System.out.println(Thread.currentThread().getName() + " result: " + number);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            System.out.println("Suma de los numeritos be like: omg bruh ah hell naw man wtf man who invited this guy");
            //El enunciado me pide que imprima todos los valores menos el poison pill. A ese, imprimirlo al final.
            //En vez de imprimir, sumo. Y al final de todo si imprimo.
            //Ojo: cada thread tiene su variable suma.
            //Quinteroooo
            //Son diferentes threads --> podrian estar interfiriendo? NO, porque lo que esta dentro del metodo es del stack del metodo

            System.out.println("Suma de los numeros (fr this time): %d");
        }
    }
}