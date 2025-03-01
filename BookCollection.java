package Iterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BookCollection<T> {
    private List<T> books = new ArrayList<>();

    public void addBook(T book) {
        books.add(book);
    }

    public Iterator<T> iterator() {
        return books.iterator();
    }
}
