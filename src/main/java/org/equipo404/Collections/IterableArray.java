package org.equipo404.Collections;

import java.util.Iterator;
/**
 * Implementa un array iterable, permitiendo recorrer sus elementos utilizando un iterador.
 *
 * @param <T> Tipo de elementos almacenados en el array.
 *
 * @author Emiliano Kaleb Jimenez Rivera
 * @author Bedoya Tellez Ariadna Valeria
 * @author Sanchez Soto Saul
 * @version 1.0
 */

public class IterableArray<T> implements Iterable<T> {
    /**
     * Arreglo de elementos a iterar.
     */
    private final T[] arreglo;
    /**
     * Constructor de la clase IterableArray.
     *
     * @param arreglo Array de elementos que se podrán iterar.
     */

    public IterableArray(T[] arreglo) {
        this.arreglo = arreglo;
    }
    /**
     * Retorna un iterador para recorrer los elementos del array.
     *
     * @return Un iterador sobre los elementos del array.
     */

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int indice = 0;
            /**
             * Verifica si hay más elementos en el array.
             *
             * @return {@code true} si hay más elementos, {@code false} en caso contrario.
             */

            @Override
            public boolean hasNext() {
                return indice < arreglo.length;
            }
            /**
             * Retorna el siguiente elemento del array.
             *
             * @return El siguiente elemento en el array.
             */

            @Override
            public T next() {
                return arreglo[indice++];
            }
        };
    }

}
