package library;

public abstract class User {
   protected String name;
   protected String userId;

   public User(String var1, String var2) {
      this.name = var1;
      this.userId = var2;
   }
   public String getName() {
      return this.name;
   }
   public String getUserId() {
      return this.userId;
   }
   public abstract void displayMenu();
   public abstract void performAction(LibrarySystem librarySystem);
   public abstract void displayUserDetails();
}
