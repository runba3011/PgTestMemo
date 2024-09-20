public class PersonManager {

  public static void main(String[] args) {

      Person[] people = {
              new Person("山本一郎", 7),
              new Person("山田次郎", 151),
              new Person(),
          };

      for (Person person : people) {
          person.show();
      }
  }
}