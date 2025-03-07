package org.equipo404.User;

public abstract class BorrowType {
    protected int daysLeft;
    public BorrowType(int daysLeft){
       this.daysLeft = daysLeft;
    }

    public void addDaysLeft(int daysAdded){
        this.daysLeft += daysAdded;
    }
}
