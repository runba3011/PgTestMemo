import java.util.Scanner;

public class App {
  //解放漏れを起こさないよう、このscanner以外使用しない
  public static Scanner scanner;
  public static void main(String[] args) {
    scanner = new Scanner(System.in);
    MonsterGetManager monsterGetManager = new MonsterGetManager();
    monsterGetManager.start();
    scanner.close();
  }
}
