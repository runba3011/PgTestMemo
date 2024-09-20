public class ChildHouse {
  public static void main(String[] args) {

      Child yamachan = new Child();

      yamachan.setName("山本一郎");
      yamachan.setAge(11);
      yamachan.showDetails();

      try {
          yamachan.setAge(-30);
      } catch (IllegalArgumentException e) {
          e.printStackTrace();
      }

      try {
          yamachan.setAge(30);
      } catch (IllegalArgumentException e) {
          e.printStackTrace();
      }

      System.out.printf(
              "%s => %d %n",
              yamachan.getName(),
              yamachan.getAge()
          );
  }
}