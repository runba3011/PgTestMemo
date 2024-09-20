class Dog {

  private String name;
  private int age;
  private String dogType;

  public Dog(String name, int age, String dogType) {

    this.name = name;
    this.age = age;
    this.dogType = dogType;
  }

  public void showDetails() {
    System.out.format("%s の %s は %d 歳です. %n",
        this.dogType,
        this.name,
        this.age);
  }
}