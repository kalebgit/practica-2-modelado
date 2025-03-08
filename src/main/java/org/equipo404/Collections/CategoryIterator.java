package org.equipo404.Collections;

import org.equipo404.Library.Resource;
import org.equipo404.Library.ResourceCategory;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class CategoryIterator<T extends Resource> extends CollectionIteratorStrategy<T> {

    ResourceCategory category;
    private T nextElement;

    public CategoryIterator(Iterable<T> iterable, ResourceCategory resourceCategory) {
        super(iterable);
        this.category = resourceCategory;
    }

    private void advance(){
        nextElement = null;
        while(this.getRawCollectionIterator().hasNext()){
            T element = this.getRawCollectionIterator().next();
            if(element.getResourceCategory() == category){
                nextElement = element;
                break;
            }
        }
    }

    @Override
    public boolean hasNext() {
        return nextElement != null;
    }

    @Override
    public T next() {
        if(nextElement == null){
            throw new NoSuchElementException("No hay mas elementos de esta categoria");
        }
        T result = nextElement;
        advance();
        return result;
    }
}
