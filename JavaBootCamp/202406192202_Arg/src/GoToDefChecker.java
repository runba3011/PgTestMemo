public class GoToDefChecker {
  public static void main(String[] args) {
      String[] myDogs = {"Bulldog", "Akita", "Poodle",};

      DogList petShopDogs = new DogList();
      petShopDogs.showAllDogs();
      
      petShopDogs.generateDogs();
      petShopDogs.showAllDogs();

      for (String dog : myDogs) {
          petShopDogs.addDog(dog);
      }
      petShopDogs.showAllDogs();
  }
}