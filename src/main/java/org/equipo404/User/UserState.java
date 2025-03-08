package org.equipo404.User;


import org.equipo404.DesignPatterns.Context;
import org.equipo404.DesignPatterns.State;
import org.equipo404.Library.DocumentTemplate;

public abstract class UserState implements State<User> {

   protected User context;
   @Override
   public User  context() {
      return  this.context;
   }

   @Override
   public void setContext(User context) {
      this.context = context;
   }
    abstract void borrow(DocumentTemplate doc, BorrowType borrowType);
   abstract void returnBorrowedDoc();
}
