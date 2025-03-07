package org.equipo404.Library;

public abstract class Resource {
    private String title;
    private ResourceCategory ResourceCategory;

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
}
