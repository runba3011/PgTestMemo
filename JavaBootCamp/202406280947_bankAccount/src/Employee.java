public class Employee {
  private int id;
  private String name;
  private int age;
  private Division division;
  public int getId() {
    return id;
  }
  public String getName() {
    return name;
  }
  public int getAge() {
    return age;
  }
  public Division getDivision() {
    return division;
  }
  public void setId(int id) {
    this.id = id;
  }
  public void setName(String name) {
    this.name = name;
  }
  public void setAge(int age) {
    this.age = age;
  }
  public void setDivision(Division division) {
    this.division = division;
  }
}
