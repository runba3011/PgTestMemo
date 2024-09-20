import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

public class App {
  private final static Integer NUMBER_DIGITS = 4;

  public static void main(String[] args) {
    // ユーザー入力を受け取るとき、0始まりの数字に対応するためString型で受け取る。
    // それに合わせて生成した乱数を記憶するリストもString型を持つようにする。
    ArrayList<String> randomNumberStringList = new ArrayList<>();
    generateRandomNumber(randomNumberStringList);

    Integer inputCount = 0;
    Integer hitCount = 0;
    // 警告は発生している。、hitについては下記のwhile文で使用しているため初期化する必要があるが、blowCountは初期化する必要がない。
    // ただし、似た目的で使用する変数のため同様に初期化しないと統一性が失われる。こういった個所はどうするのが最適か？
    Integer blowCount = 0;
    while (!hitCount.equals(NUMBER_DIGITS)) {
      AtomicBoolean isSuccess = new AtomicBoolean();
      String inputNumberString = inputNumberString(isSuccess);
      if (!isSuccess.get()) {
        continue;
      }
      inputCount++;

      ArrayList<String> inputNumberStringList;
      inputNumberStringList = convertNumberStringToList(inputNumberString);

      // ヒットの項目がブローで再度見つからないようにするよう、ヒットの項目のインデックスを記憶するためのリスト
      ArrayList<Integer> hitIndexList = new ArrayList<>();
      hitCount = getHitCount(randomNumberStringList, inputNumberStringList, hitIndexList);
      blowCount = getBlowCount(randomNumberStringList, inputNumberStringList, hitIndexList);

      if (hitCount.equals(NUMBER_DIGITS)) {
        System.out.printf("おめでとう!%d回目で成功♪", inputCount);
      } else {
        System.out.printf("ヒット：%d個、ブロー：%d個\n", hitCount, blowCount);
      }
    }
  }

  public static void generateRandomNumber(ArrayList<String> randomNumberStringList) {
    Random rand = new Random();
    while (randomNumberStringList.size() < NUMBER_DIGITS) {
      Integer randomNumber = rand.nextInt(10);
      String randomNumbeString = randomNumber.toString();
      if (!randomNumberStringList.contains(randomNumbeString)) {
        randomNumberStringList.add(randomNumbeString);
      }
    }
  }

  public static String inputNumberString(AtomicBoolean isSuccess) {
    System.out.printf("%d桁の数字を入力して下さい:", NUMBER_DIGITS);
    // 0始まりの数字に対応するため、String型で受け取る。
    String inputNumberString = CommonInputManager.inputPositiveNumberWithDigitsStartZero(NUMBER_DIGITS, isSuccess);
    if (!isSuccess.get()) {
      System.out.printf("正の%d桁の数字を入力してください\n", NUMBER_DIGITS);
      return "";
    }

    return inputNumberString;
  }

  public static ArrayList<String> convertNumberStringToList(String inputNumberString) {
    ArrayList<String> list = new ArrayList<>();
    while (0 < inputNumberString.length()) {
      list.add(inputNumberString.substring(0, 1));
      inputNumberString = inputNumberString.substring(1, inputNumberString.length());
    }

    return list;
  }

  public static Integer getHitCount(ArrayList<String> randomNumberStringList, ArrayList<String> inputNumberStringList,
      ArrayList<Integer> hitIndexList) {
    Integer result = 0;
    for (int i = 0; i < randomNumberStringList.size(); i++) {
      if (randomNumberStringList.get(i).equals(inputNumberStringList.get(i))) {
        result++;
        hitIndexList.add(i);
      }
    }

    return result;
  }

  public static Integer getBlowCount(ArrayList<String> randomNumberStringList, ArrayList<String> inputNumberStringList,
      ArrayList<Integer> hitIndexList) {
    Integer result = 0;
    for (int i = 0; i < randomNumberStringList.size(); i++) {
      if (randomNumberStringList.contains(inputNumberStringList.get(i)) && !hitIndexList.contains(i)) {
        result++;
      }
    }

    return result;
  }
}
