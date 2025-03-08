package org.equipo404.Collections;

import org.equipo404.Library.Magazine;

import java.util.Iterator;

public class MagazinesCollection extends ResourceCollection<Magazine> {

    // clase propia para que se visualice mejor el uso de arreglos
    IterableArray<Magazine> magazinesArray;

    public MagazinesCollection(CollectionIteratorStrategy<Magazine> iteratorStrategy, Magazine[] magazines) {
        super(iteratorStrategy);
        magazinesArray = new IterableArray<>(magazines);
        iteratorStrategy.setIterable(magazinesArray);
    }

    @Override
    public Iterator<Magazine> iterator() {
        return this.getIteratorStrategy().getRawCollectionIterator();
    }
}
