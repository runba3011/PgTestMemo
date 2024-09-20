import java.util.Scanner;

public class StringInputReceiver {
  public static void main(String[] args) throws Exception {
    Scanner stdin = new Scanner(System.in);
    System.out.println("文字列を入力してください:");
    String str = stdin.nextLine();
    System.out.printf("入力された文字列は%sです",str);
    stdin.close();
  }
}
