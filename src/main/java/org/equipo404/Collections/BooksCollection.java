package org.equipo404.Collections;

import org.equipo404.Library.Book;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BooksCollection extends ResourceCollection<Book>{
    List<Book> booksList;

    public BooksCollection(CollectionIteratorStrategy<Book> iteratorStrategy, ArrayList<Book> books) {
        super(iteratorStrategy);
        booksList = books;
        iteratorStrategy.setRawCollectionIterator(booksList.iterator());
    }

    @Override
    public Iterator<Book> iterator() {
        return this.getIteratorStrategy().rawCollectionIterator;
    }
}
