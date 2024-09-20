import java.util.Scanner;

public class App {
  //解放漏れを防ぐためこのScanner以外使用しない
  public static Scanner scanner;
  public static void main(String[] args) {
    scanner = new Scanner(System.in);
    GameManager gm = new GameManager();
    gm.start();
    scanner.close();
  }
}
