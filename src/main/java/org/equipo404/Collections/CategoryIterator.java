package org.equipo404.Collections;

import org.equipo404.Library.Resource;
import org.equipo404.Library.ResourceCategory;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class CategoryIterator<T extends Resource> extends CollectionIteratorStrategy<T> {

    ResourceCategory category;
    private T nextElement;

    public CategoryIterator(Iterator<T> iterator, ResourceCategory resourceCategory) {
        super(iterator);
        this.category = resourceCategory;
    }

    private void advance(){
        nextElement = null;
        while(this.rawCollectionIterator.hasNext()){
            T element = next();
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
