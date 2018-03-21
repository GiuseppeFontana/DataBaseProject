package Utils;

import Entity.User;

public class ClassicSingleton {
   private static ClassicSingleton instance = null;
   private User user;

   protected ClassicSingleton() {
      // Exists only to defeat instantiation.
   }
   public static ClassicSingleton getInstance() {
      if(instance == null) {
         instance = new ClassicSingleton();
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