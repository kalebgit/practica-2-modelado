package org.equipo404.Library;

public class Magazine extends Resource{
    private String issue;

    public Magazine(int id, String title, org.equipo404.Library.ResourceCategory resourceCategory) {
        super(id, title, resourceCategory);
    }
}
