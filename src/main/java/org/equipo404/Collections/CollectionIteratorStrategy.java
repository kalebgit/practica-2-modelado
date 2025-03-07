package org.equipo404.Collections;

import org.equipo404.Library.Resource;

import java.util.Iterator;

public abstract class CollectionIteratorStrategy<T extends Resource> implements Iterator<T> {
    protected Iterator<T> rawCollectionIterator;
    public CollectionIteratorStrategy(Iterator<T> iterator){
        this.rawCollectionIterator = iterator;
    }

    public Iterator<T> getRawCollectionIterator() {
        return rawCollectionIterator;
    }

    public void setRawCollectionIterator(Iterator<T> rawCollectionIterator) {
        this.rawCollectionIterator = rawCollectionIterator;
    }
}
