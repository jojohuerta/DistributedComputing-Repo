package ar.edu.itba.pod.concurrency.exercises.e3;

import ar.edu.itba.pod.concurrency.exercises.e1.GenericService;
import ar.edu.itba.pod.concurrency.exercises.e1.GenericServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ExecutorTest {
    private GenericService service;

    @BeforeEach
    public final void before() {
        service = new GenericServiceImpl();
    }

    @Test
    public final void test() throws Exception {
        ExecutorService executor = Executors.newCachedThreadPool();

        Callable<Integer> registerViews = () -> {
            for (int i = 0; i < 5; i++){
                service.addVisit();
            }
            return service.getVisitCount();
        };

        //Enviar la tarea al executor y obtener el Future
        Future<Integer> futureResult = executor.submit(registerViews);

        //Obtener el resultado final del hilo
        int totalViews = futureResult.get();

        //Hacer la aserción para verificar que sean 5
        assertEquals(5, totalViews);
        
        // Es buena práctica apagar el executor al terminar
        executor.shutdown();
    }
}
