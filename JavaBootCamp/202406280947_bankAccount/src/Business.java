public class Business {
  public static void main(String[] args) {

      Division division = new Division();
      division.setId(7);
      division.setName("ゲーム事業部");

      Employee employee = new Employee();
      employee.setId(777);
      employee.setName("山本一郎");
      employee.setAge(171);
      employee.setDivision(division);

      System.out.printf(
              "従業員ID:%d %s（%d 歳）さんは、部署ID:%d の %s 所属です. %n",
              employee.getId(),
              employee.getName(),
              employee.getAge(),
              employee.getDivision().getId(),
              employee.getDivision().getName()
          );
  }
}