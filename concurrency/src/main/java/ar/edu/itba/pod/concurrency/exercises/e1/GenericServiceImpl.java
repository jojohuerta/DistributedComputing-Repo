package ar.edu.itba.pod.concurrency.exercises.e1;

import java.util.LinkedList;
import java.util.Objects;
import java.util.Optional;
import java.util.Queue;

/**
 * Basic implementation of {@link GenericService}.
 */
public  class GenericServiceImpl implements GenericService {

    private int visitCount = 0;
    private final Queue<String> serviceQueue = new LinkedList<>();

    @Override
    public String echo(String message) {
        return message;
    }

    @Override
    public String toUpper(String message) {
        // if(message == null){
        //     return null;
        // }
        // return message.toUpperCase();
        return Optional.ofNullable(message).map(String::toUpperCase).orElse(null);
    }

    @Override
    public void addVisit() {
        this.visitCount++;
    }

    @Override
    public int getVisitCount() {
        return this.visitCount;
    }

    @Override
    public boolean isServiceQueueEmpty() {
        return serviceQueue.isEmpty();
    }

    @Override
    public void addToServiceQueue(String name) {
        Objects.requireNonNull(name, "El nombre no puede ser null");
        serviceQueue.add(name);
    }

    @Override
    public String getFirstInServiceQueue() {
        if(this.isServiceQueueEmpty()){
            throw new IllegalStateException("No one in queue");
        }
        // .poll() devuelve y elimina el primer elemento de la cola
        return serviceQueue.poll();
    }
}
