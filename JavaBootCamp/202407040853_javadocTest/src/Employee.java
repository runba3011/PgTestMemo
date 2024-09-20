/** 
  従業員クラス
  従業員の名前、年齢、所属部署を格納するクラス
  @author Minoru Takano
  @version 1.0
*/
public class Employee {

  /** 従業員ID */
  private int id;
  /** 従業員名 */
  private String name;
  /** 年齢 */
  private int age;
  /** 部署 */
  private Division division;

  /**
   * 従業員IDを設定し、自分自身を返す
   * @param id 従業員ID
   * @return 自分自身のインスタンス
   */
  public Employee id(int id) {
    this.id = id;
    return this;
  }

  /** 
   * 従業員の情報を出力する
   */
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