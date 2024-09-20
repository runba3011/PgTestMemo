import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DogList {
    private List<String> dogs;

    public DogList() {
        this.dogs = new ArrayList<>();
    }

    public void generateDogs() {
        this.dogs.addAll(
            Arrays.asList("Chiwawa", "Pomeranian")
        );
    }

    public void addDog(String dog) {
        this.dogs.add(dog);
    }

    public void showAllDogs() {
        System.out.println("-----");

        if (this.dogs.isEmpty()) {
            System.out.println("まだ犬が登録されていません.");
            return;
        }

        for (String dog : this.dogs) {
            System.out.printf(
                "%d => %s %n", 
                this.dogs.indexOf(dog), dog
            );
        }
    }
}