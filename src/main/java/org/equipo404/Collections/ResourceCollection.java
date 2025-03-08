package org.equipo404.Collections;

import org.equipo404.Library.Book;
import org.equipo404.Library.Resource;

import java.util.Iterator;
/**
 * Representa una colección de recursos dentro del sistema de la biblioteca digital.
 * Implementa la interfaz {@link Iterable} para permitir la iteración sobre sus elementos.
 *
 * @param <T> Tipo de recurso que extiende de {@link Resource}.
 *
 * @author Emiliano Kaleb Jimenez Rivera
 * @author Bedoya Tellez Ariadna Valeria
 * @author Sanchez Soto Saul
 * @version 1.0
 */

public abstract class ResourceCollection<T extends Resource> implements Iterable<T>{
    /**
     * Estrategia de iteración utilizada para recorrer la colección.
     */

    protected CollectionIteratorStrategy<T> iteratorStrategy;
    /**
     * Constructor de la colección de recursos.
     *
     * @param iteratorStrategy Estrategia de iteración a utilizar.
     */

    public ResourceCollection(CollectionIteratorStrategy<T> iteratorStrategy){
        this.iteratorStrategy = iteratorStrategy;
    }
    /**
     * Obtiene la estrategia de iteración actual.
     *
     * @return La estrategia de iteración utilizada.
     */

    public CollectionIteratorStrategy<T> getIteratorStrategy() {
        return iteratorStrategy;
    }
    /**
     * Establece una nueva estrategia de iteración.
     *
     * @param iteratorStrategy Nueva estrategia de iteración.
     */

    public void setIteratorStrategy(CollectionIteratorStrategy<T> iteratorStrategy) {
        this.iteratorStrategy = iteratorStrategy;
    }
    /**
     * Representación en cadena de la colección de recursos.
     *
     * @return Una cadena con el formato de la colección y sus elementos.
     */



    @Override
    public Iterator<T> iterator(){
        return this.iteratorStrategy;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("+------------------+\n");
        sb.append("|   Elementos     |\n");
        sb.append("+------------------+\n");

        boolean hasElements = false;
        for (T elemento : this) {
            hasElements = true;
            sb.append("| ").append(String.format("%-21s", elemento)).append("|\n"); // getTitle() en lugar de toString()
        }

        if (!hasElements) {
            sb.append("|    (Vacío)      |\n");
        }
        sb.append("+------------------+");
        return sb.toString();
    }


}
