package ar.edu.itba.pod.concurrency.iii.inmutable;

import java.util.Date;
import java.util.List;

// 1. final en minúscula y antes de class
public final class Subscriber {

    // 2. final en minúscula en todos los atributos
    private final Integer id;
    private final String fullName;
    private final Date dateOfBirth; // 3. Cambiamos Datetime por Date
    private final List<Subscription> subscriptions;

    // 4. El constructor no lleva final y se llama igual que la clase
    public Subscriber(Integer id, String fullName, Date dateOfBirth, List<Subscription> subscriptions) {
        this.id = id;
        this.fullName = fullName;

        // Copia defensiva: creamos un nuevo Date para que no modifiquen el original desde afuera
        this.dateOfBirth = new Date(dateOfBirth.getTime());

        // Copia defensiva: hacemos que la lista sea inmodificable
        this.subscriptions = List.copyOf(subscriptions);
    }

    // Nota: Si la clase ya es 'final', no hace falta ponerle 'final' a los métodos
    public Integer getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public Date getDateOfBirth() {
        // Retornamos un clon del Date para proteger nuestro atributo interno
        return new Date(dateOfBirth.getTime());
    }

    public List<Subscription> getSubscriptions() {
        return subscriptions;
    }
}