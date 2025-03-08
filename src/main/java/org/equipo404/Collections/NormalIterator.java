package org.equipo404.Collections;

import org.equipo404.Library.Resource;

import java.util.Iterator;
/**
 * Implementa un iterador normal para recorrer colecciones de recursos.
 * Extiende {@link CollectionIteratorStrategy} para proporcionar una iteración estándar.
 *
 * @param <T> Tipo de recurso que extiende de {@link Resource}.
 *
 * @author Emiliano Kaleb Jimenez Rivera
 * @author Bedoya Tellez Ariadna Valeria
 * @author Sanchez Soto Saul
 * @version 1.0
 */

public class NormalIterator<T extends Resource> extends CollectionIteratorStrategy<T> {
    /**
     * Constructor del iterador normal.
     *
     * @param iterator Iterador base de la colección de recursos.
     */

    public NormalIterator(Iterable<T> iterable) {
        super(iterable);
    }
    /**
     * Verifica si hay más elementos en la colección.
     *
     * @return {@code true} si hay más elementos, {@code false} en caso contrario.
     */

    @Override
    public boolean hasNext() {
        return this.getRawCollectionIterator().hasNext();
    }
    /**
     * Retorna el siguiente elemento de la colección.
     *
     * @return El siguiente recurso en la colección.
     */

    @Override
    public T next() {
        return this.getRawCollectionIterator().next();
    }
}
