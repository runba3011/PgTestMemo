package freestyles.jp.Cargo.Entity;
public class Employee {

  private int id;
  private String name;
  private int age;
  private Division division;

  public Employee id(int id) {
    this.id = id;
    return this;
  }

  public void showDetails() {
    System.out.printf(
        "従業員ID:%d %s（%d 歳）さんは、%s 所属です. %n",
        this.id,
        this.name,
        this.age,
        this.division.toString());
  }

  public Employee name(String name) {
    this.name = name;
    return this;
  }

  public Employee age(int age) {
    this.age = age;
    return this;
  }

  public Employee division(Division division) {
    this.division = division;
    return this;
  }
}