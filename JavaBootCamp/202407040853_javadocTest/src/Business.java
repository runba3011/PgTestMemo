/** 
  業務クラス
  処理の起点となるクラス
  @author Minoru Takano
  @version 1.0
*/
public class Business {
  
  /**
   * mainメソッド
   * 従業員の情報を作成し、表示する
   * @param args 使用しない
   */
  public static void main(String[] args) {

    Division division = new Division()
        .id(7)
        .name("ゲーム事業部");

    Employee employee = new Employee()
        .id(777)
        .name("山本一郎")
        .age(171)
        .division(division);

    employee.showDetails();
  }
}