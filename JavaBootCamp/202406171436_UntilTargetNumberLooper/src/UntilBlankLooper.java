import java.util.Scanner;

public class UntilBlankLooper {
  public static void main(String[] args) {
    Scanner stdin = new Scanner(System.in);
    final String BLANK = "";

    String imputtedString = null;
    int count = 0;
    System.out.printf("文字が入力されず Enter キーが押されたら終了します.\n");

    while(imputtedString != BLANK){
      System.out.printf("%d 回目:文字を入力してください:",count+1);
      imputtedString = stdin.nextLine();
      count ++;
    }

    System.out.printf("\n => 文字が入力されなかったので終了しました%n");
    stdin.close();
  }
}
