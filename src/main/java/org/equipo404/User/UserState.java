package org.equipo404.User;


import org.equipo404.DesignPatterns.Context;
import org.equipo404.DesignPatterns.State;
import org.equipo404.Library.DocumentTemplate;

public abstract class UserState implements State {

   private User context;
   @Override
   public User  context() {
      return  this.context;
   }
    abstract void borrow(DocumentTemplate doc, BorrowType borrowType);
   abstract void returnBorrowedDoc();
}
