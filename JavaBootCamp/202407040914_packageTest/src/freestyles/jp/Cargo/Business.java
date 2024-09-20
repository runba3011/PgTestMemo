package freestyles.jp.Cargo;
import freestyles.jp.Cargo.Entity.Division;
import freestyles.jp.Cargo.Entity.Employee;

public class Business {
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