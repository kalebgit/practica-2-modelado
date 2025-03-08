package org.equipo404.Collections;

import org.equipo404.Library.Resource;

import java.util.Iterator;

public abstract class CollectionIteratorStrategy<T extends Resource> implements Iterator<T> {
    protected Iterable<T> collection;
    public CollectionIteratorStrategy(Iterable<T> iterable){
        this.collection = iterable;
    }

    public Iterator<T> getRawCollectionIterator() {
        return collection.iterator();
    }

    public void setIterable(Iterable<T> iterable){
        this.collection = iterable;
    }
}
