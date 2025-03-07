package org.equipo404.DesignPatterns;


import java.util.List;

public interface Subject<T extends Observer> {

    void addElement(T observer);
    void removeElement(T observer);
    void sendNotifications(String message);

}
