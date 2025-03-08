package org.equipo404.User;
/**
 * Clase abstracta que representa un tipo de préstamo en el sistema de biblioteca.
 * Define la cantidad de días restantes para el préstamo y permite modificarla.
 * 
 * @autores Emiliano Kaleb Jimenez Rivera, Bedoya Tellez Ariadna Valeria, Sanchez Soto Saul
 * @version 1.0
 */

public abstract class BorrowType {
    /**
     * Días restantes en el préstamo.
     */
    protected int daysLeft;
    /**
     * Constructor de BorrowType.
     * 
     * @param daysLeft Número inicial de días para el préstamo.
     */
    public BorrowType(int daysLeft){
       this.daysLeft = daysLeft;
    }
    /**
     * Agrega días al préstamo actual.
     * 
     * @param daysAdded Número de días a agregar.
     */
    

    public void addDaysLeft(int daysAdded){
        this.daysLeft += daysAdded;
    }
    /**
     * Resta días al préstamo actual.
     * 
     * @param daysSubstracted Número de días a restar.
     */
    public void substractDaysLet(int daysSubstracted){this.daysLeft-= daysSubstracted;}

}
