import java.util.Scanner;

public class BadInputReceiver {
  public static void main(String[] args) throws Exception {
    Scanner stdin = new Scanner(System.in);
    System.out.println("数字を入力してください:");
    try {
      int number = stdin.nextInt();
      System.out.printf("入力された数字は%dです",number);
    } catch (Exception e) {
      System.out.println("数字以外の文字が入力されました");
    }
    stdin.close();
  }
}
