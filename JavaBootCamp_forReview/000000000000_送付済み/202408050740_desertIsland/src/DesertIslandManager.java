import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class DesertIslandManager {
  private final Random random = new Random();

  private EatItem nowEatItem = null;
  private EatItem nextEatItem = null;

  private final int DAY_COUNT_MAX = 30;

  private final List<EatItem> eatItemList = Arrays.asList(
      new EatItem("毒蛇", 15, 30, "毒蛇の毒に負けた…"),
      new EatItem("漂流物（缶詰）", 30, 50, "歯では開けられなかった。歯が全部折れて出血死…"),
      new EatItem("流木", 8, 20, "バイキンだらけだった…"),
      new EatItem("落ち葉", 5, 20, "口の中の水分を全部持っていかれた…"),
      new EatItem("毒々しいキノコ", 10, 30, "笑いが止まらず疲れて死んだ…"),
      new EatItem("カラフルフルーツ", 5, 30, "種が喉に詰まった…"));

  // finalにできる旨の警告が表示されているが、定数ではないため警告を無視している。
  private List<Activity> allActivityList = new ArrayList<>();

  public void start() {
    Player player = new Player();
    for (int dayCount = 1; dayCount <= DAY_COUNT_MAX; dayCount++) {
      int todayStartHp = player.getNowHp();
      showDay(dayCount);
      appairEatItem();

      // ヒントを選択した場合は、ヒントを確認した後再度行動を選択する必要があるためループする。
      // 初回については必ず行動を選択する必要があるため、ヒントを選択した場合の値で初期化することで必ず一度は行動を選択するようにする。
      int selectedMove = Player.MOVE_HINT;
      while (selectedMove == Player.MOVE_HINT) {
        selectedMove = player.selectMove();
        player.executeMove(nowEatItem, selectedMove);
      }

      allActivityList.add(new Activity(dayCount, todayStartHp, nowEatItem, selectedMove, player.isDead()));
      if (player.isDead()) {
        break;
      }
      player.showNowHp();
      System.out.println("");
      System.out.println("");
    }

    printResult();
  }

  public void showDay(int dayCount) {
    System.out.println(String.format("%d日目～", dayCount));
  }

  public void appairEatItem() {
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

  public void printResult() {
    System.out.println("");
    System.out.println("──────── 結果 ────────");
    for (int i = 0; i < allActivityList.size(); i++) {
      Activity todayActivity = allActivityList.get(i);
      todayActivity.showInformation();

      if (todayActivity.getIsDead()) {
        System.err.println("あなたの命はここで終わりを迎えた…");
        return;
      }

      // i は0始まりだが、日数は1始まりのため、最大日数生き延びたかの確認には i + 1と最大日数を比較する。
      if (i + 1 == DAY_COUNT_MAX) {
        System.out.println(String.format("%d日間生き延びた！", DAY_COUNT_MAX));
        return;
      }

      System.out.print("次へ > (Enterを押してください)");
      CommonInputManager.inputString();
      System.out.println("");
    }
  }
}
