//ユーザー入力のための汎用クラス

import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;

public class CommonInputManager {
  private static Scanner scanner = new Scanner(System.in);

  // 以下の関数では、最後の引数に渡された値に関数の成否を格納し、呼び出し元で確認できるようにするためAtomicBoolean型を使用する。

  public static String inputUppercaseAlphabet(int length, AtomicBoolean isSuccess) {
    String result = scanner.nextLine();
    if (length != result.length()) {
      isSuccess.set(false);
      return "";
    }

    if (!isAlphabet(result)) {
      isSuccess.set(false);
      return "";
    }

    isSuccess.set(true);
    return result;
  }

  private static boolean isAlphabet(String str) {
    for (int i = 0; i < str.length(); i++) {
      String confirmStr = String.valueOf(str.charAt(i));
      if (!confirmStr.matches("[A-Z]")) {
        return false;
      }
    }

    return true;
  }

  public static String inputPositiveNumberWithDigitsStartZero(int digits, AtomicBoolean isSuccess) {
    int result = inputPositiveNumberWithDigits(digits, isSuccess);
    if (!isSuccess.get())
      return "";

    String resultStr = formatNumberDigits(result, digits);
    return resultStr;
  }

  public static String formatNumberDigits(int number, int digits) {
    return String.format("%0" + digits + "d", number);
  }

  public static int inputPositiveNumberWithDigits(int digits, AtomicBoolean isSuccess) {
    int result = inputPositiveNumber(isSuccess);
    if (!isSuccess.get())
      return 0;

    if (getNumberDigits(result) == digits) {
      isSuccess.set(true);
      return result;
    } else {
      isSuccess.set(false);
      return 0;
    }
  }

  private static int getNumberDigits(int number) {
    String numberStr = String.valueOf(number);
    return 0 <= number ? numberStr.length() : numberStr.length() - 1;
  }

  public static int inputPositiveNumber(AtomicBoolean isSuccess) {
    int number = inputNumber(isSuccess);
    if (0 <= number) {
      isSuccess.set(true);
      return number;
    } else {
      isSuccess.set(false);
      return 0;
    }
  }

  public static int inputNumber(AtomicBoolean isSuccess) {
    if (scanner.hasNextInt()) {
      isSuccess.set(true);
      return scanner.nextInt();
    } else {
      isSuccess.set(false);
      scanner.nextLine(); // 余分な入力(改行など)をクリアする
      return 0;
    }
  }
}