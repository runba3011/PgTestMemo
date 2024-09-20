import java.util.ArrayList;
import java.util.List;

public class DogOwner {
  public static void main(String[] args) {

    List<Dog> dogs = new ArrayList<>();
    dogs.add(new Dog("秋田賢", 7, "秋田犬"));
    dogs.add(new Dog("山本一郎", 1, "プードル"));
    dogs.add(new Dog("山田太朗", 2, "チワワ"));

    for (Dog dog : dogs) {
      dog.showInformation();
    }
  }
}