import java.util.Scanner;

public class NumberInputReceiver {
  public static void main(String[] args) throws Exception {
    Scanner stdin = new Scanner(System.in);
    System.out.println("数字を入力してください:");
    int number = stdin.nextInt();
    System.out.printf("入力された数字は%dです",number);
    stdin.close();
  }
}
