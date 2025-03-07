package org.equipo404.Library;

public enum ResourceCategory {
    CIENCIA("Ciencia"), BUSINESS("Business"), ARTE("Arte"), SALUD("Salud");
    private String name;

     ResourceCategory(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }
}
