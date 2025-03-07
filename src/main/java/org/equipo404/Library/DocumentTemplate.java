package org.equipo404.Library;

import org.equipo404.DesignPatterns.Context;
import org.equipo404.DesignPatterns.Subject;
import org.equipo404.User.User;

import java.util.Queue;

public abstract class DocumentTemplate extends Context implements Subject<User> {
    private Resource resource;
    private DocumentState documentState;
    private Queue<User> waiting;

    public DocumentTemplate(Resource resource, DocumentState documentState) {
        super(documentState);
        this.resource = resource;
        this.documentState = documentState;
    }


    public boolean lend(){
        if(documentState.lend()){
            documentState.context().changeState(new Borrowed());
            return true;
        }
        return false;
    }

    @Override
    public void addElement(User observer) {
        waiting.offer(observer);
    }

    @Override
    public void removeElement(User observer) {
        User firstInQueue = waiting.poll();
        firstInQueue.borrow(this, firstInQueue.getBorrowType());
        this.sendNotifications("El recurso: " + this.resource + "fue prestado a " + firstInQueue);
    }

    @Override
    public void sendNotifications(String message){
        waiting.stream().forEach(user -> user.update(message));
    }

    public void reserve(User user){
        addElement(user);
    }

    public abstract void getFormat();

    public Resource getResource() {
        return resource;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }
}
