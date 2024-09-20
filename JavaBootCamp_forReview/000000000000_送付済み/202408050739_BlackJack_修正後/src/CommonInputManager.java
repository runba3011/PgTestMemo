//ユーザー入力のための汎用クラス
import java.util.Scanner;

public class CommonInputManager {
  private static Scanner scanner = new Scanner(System.in);

  private static String STRING_YES = "Y";
  private static String STRING_NO = "N";

  public static String inputString() {
    return scanner.nextLine();
  }

  public static void printYesOrNoMessage() {
    System.out.printf("(%s/%s)", STRING_YES, STRING_NO);
  }

  public static void printYesOrNoFailMessage(){
    System.out.printf("%s か %s で入力してください", STRING_YES, STRING_NO);
  }

  public static Boolean isInputtedYesOrNo(String str) {
    return str.equals(STRING_YES) || str.equals(STRING_NO);
  }

  public static Boolean isYes(String str) {
    return str.equals(STRING_YES);
  }
}