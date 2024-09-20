public class Dog extends Animal {
  @Override
  public void eat() {
    System.out.println(
        "[Dog] 食べます ..........");
  }

  public void bark(){
    System.out.println(
        "[Dog] わん わん ..........");
  }
}