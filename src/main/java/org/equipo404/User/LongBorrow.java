package org.equipo404.User;
/**
 * Esta clase representa un tipo de préstamo a largo plazo con opciones de renovación.
 * 
 * @autores Emiliano Kaleb Jimenez Rivera, Bedoya Tellez Ariadna Valeria, Sanchez Soto Saul
 * @version 1.0
 */

public class LongBorrow extends BorrowType{
    /**
     * Número de renovaciones realizadas.
     */
    private int renews = 0;
    /**
     * Constructor que inicializa el préstamo a largo plazo con una duración predeterminada.
     */

    public LongBorrow(){
        super(15);
    }
    /**
     * Renueva el préstamo añadiendo días adicionales si no se ha alcanzado el límite de renovaciones.
     *
     * @return true si la renovación es exitosa, false si se ha alcanzado el límite de renovaciones.
     */

    public boolean renew(){
        if(renews <= 3){
            renews++;
            this.addDaysLeft(5);
            return true;
        }
        return false;
    }
}
