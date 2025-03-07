package org.equipo404.User;


import org.equipo404.DesignPatterns.Context;
import org.equipo404.DesignPatterns.Observer;
import org.equipo404.Library.DocumentState;
import org.equipo404.Library.DocumentTemplate;
import org.equipo404.Library.Resource;

public class User extends Context implements Observer {
    private int id;
    private String email;
    private Resource resourceBorrowed;
    private UserState state;
    private BorrowType borrowType;

    public User(int id, String email) {
        super(new Regular());
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
    }


    public void returnBorrowedDoc(){

    }

    @Override
    public void update(String message) {
        System.out.println("Mensaje recibido a [" + this + "]: " + message);
    }

    public BorrowType getBorrowType() {
        return borrowType;
    }

    public void setBorrowType(BorrowType borrowType) {
        this.borrowType = borrowType;
    }

    public Resource getResourceBorrowed() {
        return resourceBorrowed;
    }

    public void setResourceBorrowed(Resource resourceBorrowed) {
        this.resourceBorrowed = resourceBorrowed;
    }

}