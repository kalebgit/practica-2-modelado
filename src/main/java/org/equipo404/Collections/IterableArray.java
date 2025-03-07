package org.equipo404.Collections;

import java.util.Iterator;

public class IterableArray<T> implements Iterable<T> {
    private final T[] arreglo;

    public IterableArray(T[] arreglo) {
        this.arreglo = arreglo;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int indice = 0;

            @Override
            public boolean hasNext() {
                return indice < arreglo.length;
            }

            @Override
            public T next() {
                return arreglo[indice++];
            }
        };
    }

}