//ユーザー入力のための汎用クラス

import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;

public class CommonInputManager {
  private static Scanner scanner = new Scanner(System.in);
  //第2引数に渡された値に関数の成否を格納し、呼び出しもとで確認できるようにするためAtomicBoolean型を使用する
  public static String inputUppercaseAlphabet(int length, AtomicBoolean isSuccess) {
    String result = scanner.nextLine();
    if (length != result.length()) {
      isSuccess.set(false);
      return "";
    }

    for (int i = 0; i < result.length(); i++) {
      String confirmStr = String.valueOf(result.charAt(i));

      if (!confirmStr.matches("[A-Z]")) {
        isSuccess.set(false);
        return "";
      }
    }

    isSuccess.set(true);
    return result;
  }
}