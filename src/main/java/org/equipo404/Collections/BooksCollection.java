package org.equipo404.Collections;

import org.equipo404.Library.Book;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
/**
 * Representa una colección de libros dentro del sistema de la biblioteca digital.
 * Extiende {@link ResourceCollection} para manejar recursos de tipo {@link Book}.
 *
 * @author Emiliano Kaleb Jimenez Rivera
 * @author Bedoya Tellez Ariadna Valeria
 * @author Sanchez Soto Saul
 * @version 1.0
 */

public class BooksCollection extends ResourceCollection<Book>{
    /**
     * Lista que almacena los libros de la colección.
     */
    List<Book> booksList;
    /**
     * Constructor de la colección de libros.
     *
     * @param iteratorStrategy Estrategia de iteración a utilizar.
     * @param books Lista de libros que conforman la colección.
     */

    public BooksCollection(CollectionIteratorStrategy<Book> iteratorStrategy, ArrayList<Book> books) {
        super(iteratorStrategy);
        booksList = books;
        iteratorStrategy.setRawCollectionIterator(booksList.iterator());
    }
    /**
     * Retorna un iterador para recorrer los libros de la colección.
     *
     * @return Un iterador sobre los libros.
     */

    @Override
    public Iterator<Book> iterator() {
        return this.getIteratorStrategy().rawCollectionIterator;
    }
}
