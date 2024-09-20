import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class BattleManager {
  private static Scanner scanner;
  public static Random random;
  private final static int CHARACTOR_COUNT_MAX = 2;
  private final static int GENERATE_NUMBER_HP_INDEX = 1;
  private final static int GENERATE_NUMBER_STR_INDEX = 2;
  private final static int GENERATE_NUMBER_DEF_INDEX = 3;
  private final static int GENERATE_NUMBER_LUCK_INDEX = 4;
  private final static int GENEARTE_NUMBER_DEVICE_AMOUNT = 10;
  public final static int GENERATE_NUMBER_MAX = 255;

  public static void main(String[] args) {
    scanner = new Scanner(System.in);
    random = new Random();
    List<Character> characterList = generateCharacters();
    showCharactorInformation(characterList);
    waitEnter();
    startBattle();
    BATTLE_LOOP:
    while (true) {
      for (int i = 0; i < characterList.size(); i++) {
        Character attacker = characterList.get(i);
        Character attacked = characterList.get(Math.abs(i - 1));
        attacker.attack(attacked);
        if (attacked.isAlive()) {
          System.out.printf("%sは力尽きた…\n", attacked.name);
          System.out.printf("%sの勝利！\n", attacker.name);
          break BATTLE_LOOP;
        }
      }

      nextTurn();
      showNowCharactorInformation(characterList);
      waitEnter();
    }
    scanner.close();
  }

  /**
   * Enterが押されるまで待機する
   */
  public static void waitEnter() {
    // 入力を待機することで、Enterが入力されるまで待つ
    System.out.print("待機中…(Enterを押してください)");
    scanner.nextLine();
    System.out.println("");
  }

  /**
   * 名前を入力させ、その情報をもとにキャラクターを2体生成する
   * 
   * @return 生成したキャラクターのリスト
   */
  public static List<Character> generateCharacters() {
    List<Character> characterList = new ArrayList<Character>();
    for (int i = 1; i <= CHARACTOR_COUNT_MAX; i++) {
      System.out.printf("プレイヤー %d の名前を入力してください:", i);
      String name = inputCharactoName();
      int maxHp = getStatus(name, GENERATE_NUMBER_HP_INDEX);
      int STR = getStatus(name, GENERATE_NUMBER_STR_INDEX);
      int DEF = getStatus(name, GENERATE_NUMBER_DEF_INDEX);
      int LUCK = getStatus(name, GENERATE_NUMBER_LUCK_INDEX);
      characterList.add(new Character(name, maxHp, STR, DEF, LUCK));
    }
    return characterList;
  }

  /**
   * 名前をもとにステータスを取得する
   * 
   * @param name  キャラクターの名前
   * @param index 取得するステータスのインデックス(定数を使用すること)
   * @return ステータス
   */
  public static int getStatus(String name, int index) {
    return Math.max(1, generateNumber(name, index) / GENEARTE_NUMBER_DEVICE_AMOUNT);
  }

  /**
   * ハッシュダイジェストから数値を取り出す
   * 
   * @param name  名前
   * @param index 何番目の数値を取り出すか
   * @return 数値(0 ～ 255)
   */
  public static int generateNumber(String name, int index) {
    try {
      String digest = getHashDigest(name);
      String hex = digest.substring(
          index * 2, index * 2 + 2);

      return Integer.parseInt(hex, 16);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return 0;
  }

  /**
   * 名前をもとにハッシュ値を取得する
   * 
   * @param name 名前
   * @return ハッシュ値
   */
  public static String getHashDigest(String name) {
    try {
      // ハッシュ値を取得する
      byte[] result = MessageDigest.getInstance("SHA-1")
          .digest(name.getBytes());
      return String.format(
          "%040x",
          new BigInteger(1, result));
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * ユーザーにキャラクター名を入力させる
   * 
   * @return ユーザーが入力したキャラクター名
   */
  public static String inputCharactoName() {
    return scanner.nextLine();
  }

  /**
   * 全キャラクターの情報を表示する
   * 
   * @param characterList 全キャラクターのリスト
   */
  public static void showCharactorInformation(List<Character> characterList) {
    System.out.println("-------キャラクター情報-------");
    for (int i = 0; i < characterList.size(); i++) {
      Character character = characterList.get(i);
      System.out.printf("%s : HP：%d 攻撃力：%d 防御力%d 運%d\n", character.name, character.maxHp, character.STR, character.DEF,
          character.LUCK);
    }
  }

  /**
   * バトル開始時のテキストを表示する
   */
  public static void startBattle() {
    System.out.println();
    System.out.println("=== バトル開始 ===");
  }


  public static void nextTurn() {
    System.out.println("");
    System.out.println("- 次のターン -");
  }

  public static void showNowCharactorInformation(List<Character> characterList){
    for (int i = 0; i < characterList.size(); i++) {
      Character character = characterList.get(i);
      System.out.printf("プレイヤー %d: %s(HP %d / %d)\n", i + 1, character.name, character.nowHp, character.maxHp);
    }
    System.out.println("");
    System.out.println("---");
  }
}
