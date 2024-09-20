public class ChildrenHelper {
  public static void main(String[] args) {

    Child[] children = {
        new Child("山本", "一郎", Gender.MAN),
        new Child("山田", "花子", Gender.WOMAN),
        new Child("山口", "次郎", Gender.MAN),
    };

    for (Child child : children) {
      System.out.printf(
          "ID=%d のフルネームは %s です. %n",
          child.id, child.getFullName());
    }
  }
}

enum Gender {
  MAN, WOMAN,
}

class Child {
  private static int maxId;
  public int id;
  private String firstName;
  private String lastName;
  private Gender gender;

  static {
    maxId = 0;
  }

  public Child(
      String firstName, String lastName,
      Gender gender) {

    this.id = ++maxId;
    this.firstName = firstName;
    this.lastName = lastName;
    this.gender = gender;
  }

  public String GetFullNameWithHonorificTitle(){
    return getFullName() + getHonorificTitle();
  }

  public String getFullName() {
    return this.firstName + this.lastName;
  }

  public String getHonorificTitle() {
    return isMan(this.gender) ? "くん" : "ちゃん";
  }

  public boolean isMan(Gender gender){
    return gender == Gender.MAN;
  }
}