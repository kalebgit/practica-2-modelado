package org.equipo404.Collections;

import org.equipo404.Library.Resource;

import java.util.Iterator;
/**
 * Define una estrategia de iteración para colecciones de recursos.
 * Implementa la interfaz {@link Iterator} para proporcionar una estructura base de iteradores personalizados.
 *
 * @param <T> Tipo de recurso que extiende de {@link Resource}.
 *
 * @author Emiliano Kaleb Jimenez Rivera
 * @author Bedoya Tellez Ariadna Valeria
 * @author Sanchez Soto Saul
 * @version 1.0
 */

public abstract class CollectionIteratorStrategy<T extends Resource> implements Iterator<T> {

    /**
     * Iterador base de la colección de recursos.
     */
    protected Iterator<T> rawCollectionIterator;
     /**
     * Constructor de la estrategia de iteración.
     *
     * @param iterator Iterador base de la colección de recursos.
     */
    protected Iterable<T> collection;
    public CollectionIteratorStrategy(Iterable<T> iterable){
        this.collection = iterable;
    }
    /**
     * Obtiene el iterador base de la colección de recursos.
     *
     * @return El iterador base.
     */
    

    public Iterator<T> getRawCollectionIterator() {
        return collection.iterator();
    }
    /**
     * Establece un nuevo iterador base para la colección de recursos.
     *
     * @param rawCollectionIterator Nuevo iterador base.
     */

    public void setIterable(Iterable<T> iterable){
        this.collection = iterable;
    }
}
