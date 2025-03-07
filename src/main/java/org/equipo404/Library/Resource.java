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
}
