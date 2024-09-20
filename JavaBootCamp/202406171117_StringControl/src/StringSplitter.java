public class StringSplitter {
  public static void main(String[] args) {
    String fullName = "山本 一郎";
    String[] names = fullName.split(" ");
    System.out.printf("姓: %s, 名: %s %n",names[0],names[1]);
  }
}
