public class Person {
  private String name;
  private int age;
  Person(String name , int age){
    this.name = name ;
    this.age = age;
  }

  Person(){
    this("名無しの太郎" , 20);
  }

  public void show(){
    System.out.printf("%s さんは %d 歳です.\n" , this.name , this.age);
  }
}
