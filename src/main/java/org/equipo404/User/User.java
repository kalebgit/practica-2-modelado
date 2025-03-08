package org.equipo404.User;


import org.equipo404.DesignPatterns.Context;
import org.equipo404.DesignPatterns.Observer;
import org.equipo404.Library.DocumentState;
import org.equipo404.Library.DocumentTemplate;
import org.equipo404.Library.Resource;
import org.equipo404.util.TerminalUI;



import java.util.Arrays;
/**
 * Esta clase representa un usuario en el sistema, que puede pedir prestados documentos y recibir notificaciones.
 * 
 * @autores Emiliano Kaleb Jimenez Rivera, Bedoya Tellez Ariadna Valeria, Sanchez Soto Saul
 * @version 1.0
 */

public class User extends Context<User> implements Observer {
    /**
     * Identificador del usuario.
     */
    private int id;
    /**
     * Email del usuario.
     */
    private String email;
    /**
     * Documento prestado actualmente por el usuario.
     */
    private DocumentTemplate documentBorrowed;
    /**
     * Estado actual del usuario.
     */
    private UserState state;
    /**
     * Tipo de préstamo actual del usuario.
     */
    private BorrowType borrowType;
    /**
     * Constructor que inicializa un usuario con su ID y email.
     * 
     * @param id    El identificador del usuario.
     * @param email El email del usuario.
     */

    public User(int id, String email) {
        super(new Regular());
        this.state = new Regular();
        this.state.setContext(this);
        this.id = id;
        this.email = email;
    }
    /**
     * Realiza el préstamo de un documento según el tipo de préstamo especificado.
     * 
     * @param doc        El documento a prestar.
     * @param borrowType El tipo de préstamo a realizar.
     */

    public void borrow(DocumentTemplate doc, BorrowType borrowType) {
        state.borrow(doc, borrowType);
    }
    /**
     * Obtiene los días restantes hasta la fecha límite del préstamo.
     * 
     * @return Los días restantes o -1 si no hay un préstamo activo.
     */

    public int getDaysLeftToDeadline() {
        return borrowType != null ? borrowType.daysLeft : -1;
    }
    /**
     * Cambia el estado del usuario.
     * 
     * @param state El nuevo estado del usuario.
     */

    public void changeState(UserState state) {
        this.state = state;
        this.state.setContext(this);
    }
    /**
     * Realiza la devolución del documento prestado.
     */

    public void checkRegularity(){
        int daysLeft = this.getDaysLeftToDeadline();
        if(daysLeft < 0){
            changeState(new Irregular());
            TerminalUI.error("😡Ahora el usuario + " + this.getEmail() + " moroso por tener "  + (-daysLeft) + " dias de retraso");
        }
    }

    public void returnBorrowedDoc(){
        state.returnBorrowedDoc();
    }


    //getters y setters
    /**
     * Actualiza el estado del usuario con un mensaje.
     *
     * @param message El mensaje recibido.
     */



    @Override
    public void update(String message) {
        TerminalUI.printNotification("📩 Mensaje recibido a [" + this + "]: " + message);
    }

    public BorrowType getBorrowType() {
        return borrowType;
    }

    public void setBorrowType(BorrowType borrowType) {
        this.borrowType = borrowType;
    }

    public DocumentTemplate getDocumentBorrowed() {
        return documentBorrowed;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDocumentBorrowed(DocumentTemplate documentBorrowed) {
        this.documentBorrowed = documentBorrowed;
    }
    /**
     * Representa el estado del usuario como una cadena de texto.
     *
     * @return La representación en texto del estado del usuario.
     */

    public UserState getState() {
        return state;
    }


    @Override
    public String toString() {
        String content = "👤 Usuario: " + email + "\n" +
                "🔖 Estado: " + state + "\n" +
                "📚 Préstamos: " + documentBorrowed == null? "Ninguno" : String.valueOf(documentBorrowed);
        int maxLength = Arrays.stream(content.split("\n")).mapToInt(String::length).max().orElse(0) + 4;
        String border = "┌" + "─".repeat(maxLength) + "┐";
        String formattedContent = Arrays.stream(content.split("\n"))
                .map(line -> "│ " + line + " ".repeat(maxLength - line.length() - 2) + "│")
                .reduce("", (a, b) -> a + "\n" + b);
        return border + formattedContent + "\n" + "└" + "─".repeat(maxLength) + "┘";
    }
}
