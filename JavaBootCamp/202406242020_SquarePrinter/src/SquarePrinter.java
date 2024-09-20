import java.util.Scanner;

public class SquarePrinter {

  private static Scanner stdin = new Scanner(System.in);
  private static final int MAX_WIDTH = 9;

  public static void main(String[] args) {

    String strCharInput = "";

    while (!isInputOneChar(strCharInput)) {
      strCharInput = inputLine();

      if (!isInputOneChar(strCharInput)) {
        showNotInputOneChar();
      }
      System.out.println();
    }

    char charToDraw = strCharInput.charAt(0);

    String strWidthInput = "";

    boolean isValid = false;
    int intWidth = 0;

    while (!isValid) {
      System.out.printf(
          "幅（1 ～ %d）を入力してください: ",
          MAX_WIDTH);

      strWidthInput = stdin.nextLine();

      try {
        intWidth = Integer.parseInt(strWidthInput);
      } catch (NumberFormatException e) {
        System.out.println(" => 値が不正です.");
        System.out.println();
        continue;
      }

      if (isWidthValid(intWidth)) {
        showNotWidthValid();
        continue;
      }
      isValid = true;
      System.out.println();
    }

    printSquare(intWidth,charToDraw);

    stdin.close();
  }

  static String inputLine() {
    System.out.print("一文字入力してください: ");
    return stdin.nextLine();
  }

  static boolean isInputOneChar(String strCharInput) {
    return strCharInput.length() == 1;
  }

  static void showNotInputOneChar() {
    System.out.println(
        " => 一文字ではありません.");
  }

  static boolean isWidthValid(int intWidth) {
    return intWidth <= 0 || intWidth > MAX_WIDTH;
  }

  static void showNotWidthValid() {
    System.out.println(" => 値が不正です.");
    System.out.println();
  }

  static void printSquare(int intWidth, char  charToDraw) {
    for (int i = 0; i < intWidth; i++) {

      for (int j = 0; j < intWidth; j++) {
        System.out.print(charToDraw);
      }
      System.out.println();
    }
  }
}