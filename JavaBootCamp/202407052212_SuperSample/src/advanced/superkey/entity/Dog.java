package advanced.superkey.entity;

public class Dog extends Animal {
  public Dog(){
    super("[Dog] String 引数のコンストラクタを呼び出し");
    System.out.println(String.format("[Dog] %s Dog クラスを生成しました.", super.type));
  }

  public void bark(String sound){
    super.bark("わんわん"+sound);
  }


}