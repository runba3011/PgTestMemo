public class PetShop {

  private static final boolean MALE = true;
  private static final boolean FEMALE = false;

  public static void main(String[] args) {

    Animal[] pets = {
        new Rabbit(
            "スキウサギ",
            FEMALE),

        new Cat(
            "どらねこ",
            MALE),
    };

    for (Animal pet : pets) {
      System.out.println(pet.toString());
      pet.eat();
      pet.bark();
      System.out.println();
    }

    pets[0].changeName("ツキウサギ");
    System.out.println(pets[0].toString());
    pets[0].eat();

    Barkable rabbit = pets[0];
    rabbit.bark();
  }
}