package org.equipo404.User;

public class LongBorrow extends BorrowType{
    private int renews = 0;

    public LongBorrow(){
        super(15);
    }

    public boolean renew(){
        if(renews <= 3){
            renews++;
            this.addDaysLeft(5);
            return true;
        }
        return false;
    }
}
