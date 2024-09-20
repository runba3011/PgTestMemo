/**
 * 従業員クラス
 */
public class Employee {

  private int id;
  private String name;
  private int age;
  private Division division;

  public Employee id(int id) {
    this.id = id;
    return this;
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

  public String getInformation(){
    return String.format(
      "従業員ID:%d %s（%d 歳）さんは、%sです. %n",
      this.id,
      this.name,
      this.age,
      this.division.getInformation());
  }
}