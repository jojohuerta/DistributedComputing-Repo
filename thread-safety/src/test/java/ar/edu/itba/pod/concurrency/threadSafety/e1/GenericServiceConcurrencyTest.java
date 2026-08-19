package ar.edu.itba.pod.concurrency.threadSafety.e1;


import ar.edu.itba.pod.concurrency.service.GenericService;
import ar.edu.itba.pod.concurrency.service.GenericServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit test for {@link GenericService} using {@link Thread}s
 */
public class GenericServiceConcurrencyTest {
    // Si aumentamos a VISITS_BY_HREAD = 1000000 y a THREAD_COUNT = 1000 
    //Lo que va a pasar es una race conidciion y se van a perder visitas
    private static final int VISITS_BY_THREAD = 100000;
    private static final int THREAD_COUNT = 1000;
    private static final int EXPECTED_VISITS = 100000 * 1000;

    private GenericService service;

    @BeforeEach
    public final void before() {
        service = new GenericServiceImpl();
    }

    /**
     * Makes VISITS_BY_THREAD visits to the service
     */
    private final Runnable visitor = () -> {
        for (int i = 0; i < VISITS_BY_THREAD; i++) {
            service.addVisit();
        }
    };

    private ExecutorService pool;

    /**
     * generates THREAD_COUNT threads with {@link #visitor} and runs them.
     */
@Test
public final void visit_count_with_thread_start() throws InterruptedException {
    Thread[] threads = new Thread[THREAD_COUNT];
    
    // Thread initialization
    for (int i = 0; i < THREAD_COUNT; i++) {
        threads[i] = new Thread(visitor);
        threads[i].start();
    }
    
    // join() to all
    for (int i = 0; i < THREAD_COUNT; i++) {
        threads[i].join();
    }
    
    //assert
    assertEquals(EXPECTED_VISITS, service.getVisitCount());
}

    /**
     * generates THREAD_COUNT threads with {@link #visitor} and runs them submiting it via
     * the {@link ExecutorService}
     */
    @Test
    public final void visit_count_with_executor_submit() throws InterruptedException {
        ExecutorService pool = Executors.newCachedThreadPool();
        for (int i = 0; i < THREAD_COUNT; i++) {
            pool.submit(visitor);
        }

        pool.shutdown();
        
        pool.awaitTermination(20, TimeUnit.SECONDS); //Al aumentarle los threads, hay q aumentar esto tambien para que pueda procesar todo

        //The assert should be the last thing we do
        assertEquals(EXPECTED_VISITS, service.getVisitCount());
    }
}