import java.util.Scanner;

public class App {
  //解放漏れを防ぐため、このScanner以外使用しない
  public static Scanner scanner;
  public static void main(String[] args) {
    scanner = new Scanner(System.in);
    GameManager gm = new GameManager();
    gm.start();
    scanner.close();
  }
}
