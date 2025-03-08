package org.equipo404.User;


import org.equipo404.DesignPatterns.Context;
import org.equipo404.DesignPatterns.Observer;
import org.equipo404.Library.DocumentState;
import org.equipo404.Library.DocumentTemplate;
import org.equipo404.Library.Resource;
import org.equipo404.util.TerminalUI;

import java.util.Arrays;

public class User extends Context<User> implements Observer {
    private int id;
    private String email;
    private DocumentTemplate documentBorrowed;
    private UserState state;
    private BorrowType borrowType;

    public User(int id, String email) {
        super(new Regular());
        this.state = new Regular();
        this.state.setContext(this);
        this.id = id;
        this.email = email;
    }

    public void borrow(DocumentTemplate doc, BorrowType borrowType) {
        state.borrow(doc, borrowType);
    }

    public int getDaysLeftToDeadline() {
        return borrowType != null ? borrowType.daysLeft : -1;
    }

    public void changeState(UserState state) {
        this.state = state;
        this.state.setContext(this);
    }

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