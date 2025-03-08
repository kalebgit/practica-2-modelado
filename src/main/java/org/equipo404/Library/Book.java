package org.equipo404.Library;

public class Book extends Resource{
    private String author;
    private BookCategory bookCategory;

    public Book(String title, ResourceCategory resourceCategory) {
        super(title, resourceCategory);
    }
}
