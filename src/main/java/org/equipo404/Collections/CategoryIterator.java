package org.equipo404.Collections;

import org.equipo404.Library.Resource;
import org.equipo404.Library.ResourceCategory;

import java.util.Iterator;
import java.util.NoSuchElementException;
/**
 * Implementa un iterador que recorre los recursos de una colección filtrando por categoría.
 *
 * @param <T> Tipo de recurso que extiende de {@link Resource}.
 *
 * @author Emiliano Kaleb Jimenez Rivera
 * @author Bedoya Tellez Ariadna Valeria
 * @author Sanchez Soto Saul
 * @version 1.0
 */

public class CategoryIterator<T extends Resource> extends CollectionIteratorStrategy<T> {
    /**
     * Categoría de recursos a filtrar.
     */

    ResourceCategory category;
    /**
     * Próximo elemento en la iteración.
     */
    private T nextElement;
    /**
     * Constructor del iterador por categoría.
     *
     * @param iterator Iterador base de la colección de recursos.
     * @param resourceCategory Categoría de los recursos a iterar.
     */

    public CategoryIterator(Iterator<T> iterator, ResourceCategory resourceCategory) {
        super(iterator);
        this.category = resourceCategory;
    }
    /**
     * Avanza al siguiente elemento de la categoría deseada dentro de la colección.
     */

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
    /**
     * Verifica si hay más elementos en la categoría filtrada.
     *
     * @return {@code true} si hay más elementos, {@code false} en caso contrario.
     */

    @Override
    public boolean hasNext() {
        return nextElement != null;
    }
    /**
     * Devuelve el siguiente elemento de la categoría especificada.
     *
     * @return El siguiente recurso de la categoría filtrada.
     * @throws NoSuchElementException Si no hay más elementos disponibles.
     */

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
