public class AntiMagicNumber {

  public enum Gender {
    man(1,"男"),
    woman(2,"女"),;

    private int id;
    private String ja;

    Gender(int id , String ja){
      this.id = id;
      this.ja = ja;
    }

    int getId(){
      return this.id;
    }

    String getJa(){
      return this.ja;
    }
  }

  public static void main(String[] args) {

    Gender gender = Gender.woman;

    if (gender == Gender.woman) {
      System.out.printf("私は %s です." , gender.getJa());
    }
  }
}