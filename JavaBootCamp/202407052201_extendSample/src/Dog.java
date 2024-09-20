public class Dog extends Animal {
  public void display(){
    System.out.println(String.format("[Dog] 名前は%sです" , this.getName()));
  }
}