package org.equipo404.Collections;

import org.equipo404.Library.Book;
import org.equipo404.Library.Resource;

import java.util.Iterator;

public abstract class ResourceCollection<T extends Resource> implements Iterable<T>{

    protected CollectionIteratorStrategy<T> iteratorStrategy;

    public ResourceCollection(CollectionIteratorStrategy<T> iteratorStrategy){
        this.iteratorStrategy = iteratorStrategy;
    }

    public CollectionIteratorStrategy<T> getIteratorStrategy() {
        return iteratorStrategy;
    }

    public void setIteratorStrategy(CollectionIteratorStrategy<T> iteratorStrategy) {
        this.iteratorStrategy = iteratorStrategy;
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
            sb.append("| ").append(String.format("%-16s", elemento.getTitle())).append("|\n"); // getTitle() en lugar de toString()
        }

        if (!hasElements) {
            sb.append("|    (Vacío)      |\n");
        }
        sb.append("+------------------+");
        return sb.toString();
    }}
