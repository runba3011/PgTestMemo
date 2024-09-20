package advanced.superkey.entity;

public class Animal {

  protected String type = "Animal";

  protected Animal() {
    System.out.println(
        "[Animal] デフォルトコンストラクタを実行");
  }

  public Animal(String message) {
    System.out.format("[Animal]  %s %n",
        message);
  }

  public void bark(String sound) {
    System.out.format(
        "[Animal] %s .......... %n",
        sound);
  }
}