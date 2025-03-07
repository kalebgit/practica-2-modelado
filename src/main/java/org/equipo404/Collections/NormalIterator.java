package org.equipo404.Collections;

import org.equipo404.Library.Resource;

import java.util.Iterator;

public class NormalIterator<T extends Resource> extends CollectionIteratorStrategy<T> {

    public NormalIterator(Iterator<T> iterator) {
        super(iterator);
    }

    @Override
    public boolean hasNext() {
        return this.getRawCollectionIterator().hasNext();
    }

    @Override
    public T next() {
        return this.getRawCollectionIterator().next();
    }
}
