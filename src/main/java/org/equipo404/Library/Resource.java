package org.equipo404.Library;

import java.util.Objects;

public abstract class Resource {
    private int id;
    private String title;
    private ResourceCategory ResourceCategory;

    public Resource(int id,String title, org.equipo404.Library.ResourceCategory resourceCategory) {
        this.id = id;
        this.title = title;
        ResourceCategory = resourceCategory;
    }

    public ResourceCategory getResourceCategory() {
        return ResourceCategory;
    }

    public void setResourceCategory(ResourceCategory resourceCategory) {
        ResourceCategory = resourceCategory;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Resource resource = (Resource) o;
        return id == resource.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Resource{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", ResourceCategory=" + ResourceCategory +
                '}';
    }
}
