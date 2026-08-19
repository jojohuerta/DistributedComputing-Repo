package ar.edu.itba.pod.concurrency.exercises.e3;

import java.util.Random;
import java.util.concurrent.Callable;

//Callable<Integer>
public class RandomNumberCalculator implements Callable<Integer> {
    
    @Override
    public Integer call() throws Exception {
        System.out.println("Calculando número aleatorio...");
        Thread.sleep(500); 
        
        Random random = new Random();
        // Devuelve un random entre 0 y 99
        return random.nextInt(100); 
    }
}