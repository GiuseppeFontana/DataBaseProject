package Singletons;

import Entity.User;

public class SingletonUser {
   private static SingletonUser instance = null;
   private User user;

   protected SingletonUser() {
   }
   public static SingletonUser getInstance() {
      if(instance == null) {
         instance = new SingletonUser();
      }
      return instance;
   }

   public User getUser() {
      return user;
   }

   public void setUser(User u){
      user = u;
   }

}