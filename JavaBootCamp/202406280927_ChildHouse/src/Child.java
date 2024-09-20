public class Child {
  private String name;
  private int age;
  private final int AGE_RANGE = 20;

  public String getName(){
    return this.name;
  }

  public void setName(String name){
    this.name = name;
  }

  public int getAge(){
    return this.age;
  }

  public void setAge(int age){
    isValidAge(age);
    this.age = age;
  }

  private void isValidAge(int age){
    isPositiveAge(age);
    isAgeOutOfRange(age);
  }

  private void isPositiveAge(int age){
    if(age < 0){
      throw new IllegalArgumentException("年齢は正数で指定してください.");
    }
  }

  private void isAgeOutOfRange(int age){
    if(AGE_RANGE <= age){
      throw new IllegalArgumentException(String.format("年齢は %d 未満で指定してください." , AGE_RANGE));
    }
  }

  public void showDetails(){
    System.out.printf("%s は %d 歳です.\n" , this.name , this.age);
  }
}
