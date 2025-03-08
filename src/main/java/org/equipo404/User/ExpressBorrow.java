package org.equipo404.User;
/**
 * Representa un préstamo exprés en la biblioteca.
 * Los préstamos exprés tienen un límite de 7 días.
 * 
 * @autores Emiliano Kaleb Jimenez Rivera, Bedoya Tellez Ariadna Valeria, Sanchez Soto Saul
 * @version 1.0
 */

public class ExpressBorrow extends BorrowType{
    /**
     * Constructor de ExpressBorrow.
     * Inicializa el préstamo con un período de 7 días.
     */

    public ExpressBorrow(){
        super(7);
    }
}
