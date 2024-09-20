
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

public class GameManager {
  private final static Random random = new Random();

  private final static Integer MAX_HP = 100;
  private static Integer nowHp = 50;
  private static Boolean isDead = false;
  private final static Integer HINT_COUNT_MAX = 3;
  private static Integer hintCount = 0;

  private static EatItem nowEatItem = null;
  private static EatItem nextEatItem = null;

  private final static Integer NOT_EAT_HP_DUCE_AMOUNT = 10;
  private final static Integer DAY_COUNT_MAX = 30;

  // switch-caseで使用できるよう、Integer型ではなくint型を使用している
  public final static int MOVE_EAT = 1;
  public final static int MOVE_NOT_EAT = 2;
  private final static int MOVE_HINT = 3;
  public final static int MOVE_DO_NOTHING = 4;

  private final static List<EatItem> eatItemList = Arrays.asList(
      new EatItem("毒蛇", 15, 30, "毒蛇の毒に負けた…"),
      new EatItem("漂流物（缶詰）", 30, 50, "歯では開けられなかった。歯が全部折れて出血死…"),
      new EatItem("流木", 8, 20, "バイキンだらけだった…"),
      new EatItem("落ち葉", 5, 20, "口の中の水分を全部持っていかれた…"),
      new EatItem("毒々しいキノコ", 10, 30, "笑いが止まらず疲れて死んだ…"),
      new EatItem("カラフルフルーツ", 5, 30, "種が喉に詰まった…"));

  // finalにできる旨の警告が表示されているが、定数ではないため警告を無視している。
  private static List<Activity> allActivityList = new ArrayList<>();

  public static void main(String[] args) {
    for (Integer dayCount = 1; dayCount <= DAY_COUNT_MAX; dayCount++) {
      Integer todayStartHp = nowHp;
      showDay(dayCount);
      appairEatItem();

      // ヒントを選択した場合は、ヒントを確認した後再度行動を選択する必要があるためループする。
      // 初回については必ず行動を選択する必要があるため、ヒントを選択した場合の値で初期化することで必ず一度は行動を選択するようにする。
      int selectedMove = MOVE_HINT;
      while (selectedMove == MOVE_HINT) {
        selectedMove = selectMove();
        executeMove(selectedMove);
      }
      allActivityList.add(new Activity(dayCount, todayStartHp, nowEatItem, selectedMove, isDead));
      if (isDead)
        break;
      showNowHp();
      System.out.println("");
      System.out.println("");
    }

    printResult();
  }

  public static void showDay(int dayCount) {
    System.out.println(String.format("%d日目～", dayCount));
  }

  public static void appairEatItem() {
    if (nextEatItem == null) {
      // 初日は翌日の食べ物が未取得になるため、翌日の食べ物を今日の食べ物と設定して設定することができない。
      // 今日の食べ物についてもランダムで取得する。
      nowEatItem = eatItemList.get(random.nextInt(eatItemList.size()));
    } else {
      nowEatItem = nextEatItem;
    }
    System.out.println(String.format("食べれそうなものが目の前に現れた！"));
    nowEatItem.showInformation();

    // ヒントで使用するため、翌日の食べ物についても取得しておく。
    nextEatItem = eatItemList.get(random.nextInt(eatItemList.size()));
  }

  public static int selectMove() {
    System.out.print(String.format("食べますか？ 食べる：%d 食べない：%d ヒント：%d ", MOVE_EAT, MOVE_NOT_EAT, MOVE_HINT));
    AtomicBoolean isSuccess = new AtomicBoolean(false);
    int selectedMove = CommonInputManager.inputNumber(isSuccess);
    System.out.println("");

    if (!isSelectedSomeMove(selectedMove, isSuccess)) {
      // 食べる、食べない、ヒント のいずれも選択されなかった場合は「あたふたしている」、つまり何もしないとして認識する
      selectedMove = MOVE_DO_NOTHING;
    }

    return selectedMove;
  }

  public static Boolean isSelectedSomeMove(int selectedMove, AtomicBoolean isSelectMoveSuccess) {
    if (!isSelectMoveSuccess.get()) {
      return false;
    }

    List<Integer> moveList = Arrays.asList(MOVE_EAT, MOVE_NOT_EAT, MOVE_HINT);
    return moveList.contains(selectedMove);
  }

  public static void executeMove(int move) {
    switch (move) {
      case MOVE_EAT:
        executeEat();
        break;
      case MOVE_NOT_EAT:
        executeNotEat();
        break;
      case MOVE_HINT:
        executeHint();
        break;

      case MOVE_DO_NOTHING:
        executeDoNothing();
        break;
      default:
        break;
    }
  }

  public static void executeEat() {
    System.out.println(String.format("%s を食べようと試みました…", nowEatItem.getItemName()));
    if (nowEatItem.canEat()) {
      System.out.println("完食！HPが回復しました！");
      nowHp = Math.min(nowHp + nowEatItem.getExpectedHeelingHP(), MAX_HP);
    } else {
      System.out.println("あなたの命は終焉を迎えた！");
      System.out.println(String.format("死因：%s", nowEatItem.getCauseOfDeath()));
      isDead = true;
    }
  }

  public static void executeNotEat() {
    System.out.println(String.format("%s を食べませんでした。HPが10減りました。", nowEatItem.getItemName()));
    nowHp -= NOT_EAT_HP_DUCE_AMOUNT;
    if (isDead()) {
      deathByNotEating();
    }
  }

  public static void executeHint() {
    hintCount++;
    if (HINT_COUNT_MAX <= hintCount) {
      System.out.println("…何も起きなかった。");
      return;
    }

    System.out.println("突如、君の頭の中に明日の食べ物についての情報が流れてくる！");
    nextEatItem.showInformation();
    System.out.println("ただし、目の前にある食べ物は変わらないことを君は思い出した…");
    System.out.println("");
  }

  public static void executeDoNothing() {
    nowHp -= NOT_EAT_HP_DUCE_AMOUNT;
    System.out.println("あたふたしている間にいつの間にか食べ物はなくなってしまっていた！");
    if (isDead()) {
      deathByNotEating();
      return;
    }

    System.out.println("次からはきちんと行動を選択しようと、あなたは理解しました…");
  }

  public static Boolean isDead() {
    return nowHp <= 0;
  }

  public static void deathByNotEating() {
    System.out.println("食べていたら生存していたのではと後悔を残しながら、あなたはの命は終わりを迎えました…");
    isDead = true;
  }

  public static void showNowHp() {
    System.out.println(String.format("現在のHP:%d", nowHp));
  }

  public static void printResult() {
    System.out.println("");
    System.out.println("──────── 結果 ────────");
    for (int i = 0; i < allActivityList.size(); i++) {
      Activity todayActivity = allActivityList.get(i);
      todayActivity.showInformation();

      if (todayActivity.getIsDead()) {
        System.err.println("あなたの命はここで終わりを迎えた…");
        return;
      }

      //i は0始まりだが、日数は1始まりのため、最大日数生き延びたかの確認には i + 1と最大日数を比較する。
      if(i + 1 == DAY_COUNT_MAX){
        System.out.println(String.format("%d日間生き延びた！", DAY_COUNT_MAX));
        return;
      }

      System.out.print("次へ > (Enterを押してください)");
      CommonInputManager.inputString();
      System.out.println("");
    }
  }
}
