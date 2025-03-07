package org.equipo404.Library;

import org.equipo404.DesignPatterns.Context;
import org.equipo404.DesignPatterns.Subject;
import org.equipo404.User.User;

import java.util.Arrays;
import java.util.Queue;

public abstract class DocumentTemplate extends Context<DocumentTemplate> implements Subject<User> {
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

    public void checkWaitingUsers(){
        if(!waiting.isEmpty()){
            removeElement();
        }
    }

    @Override
    public void addElement(User observer) {
        waiting.offer(observer);
    }

    @Override
    public void removeElement() {
        User firstInQueue = waiting.poll();
        firstInQueue.borrow(this, firstInQueue.getBorrowType());
        this.sendNotifications("El recurso: " + this.resource + "fue prestado a " + firstInQueue + " que estaba esperando el libro");
    }

    @Override
    public void sendNotifications(String message){
        waiting.stream().forEach(user -> user.update(message));
    }



    protected String format() {
        return "📖 " + getResource() + "\n" +
                "🔖 Estado: " + documentState;
    }

    @Override
    public String toString() {
        String content = format();
        int maxLength = Arrays.stream(content.split("\n")).mapToInt(String::length).max().orElse(0) + 4;
        String border = "┌" + "─".repeat(maxLength) + "┐";
        String formattedContent = Arrays.stream(content.split("\n"))
                .map(line -> "│ " + line + " ".repeat(maxLength - line.length() - 2) + "│")
                .reduce("", (a, b) -> a + "\n" + b);
        return border + formattedContent + "\n" + "└" + "─".repeat(maxLength) + "┘";
    }
}
