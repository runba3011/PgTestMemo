import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;

public class GameManager {
  private static Scanner scanner = new Scanner(System.in);

  private final static Integer MONSTER_COUNT_MAX = 10;
  private static Random random = new Random();
  private static Monster nowMonster;
  private static List<Monster> capturedMonsterList = new ArrayList<>();
  private static List<Monster> monsterList = Arrays.asList(
      new Monster("ザコモン", 30, 20, 20, 30, 72),
      new Monster("フツモン", 50, 20, 30, 30, 50),
      new Monster("ツヨモン", 100, 50, 30, 25, 28),
      new Monster("ボスモン", 100, 50, 50, 10, 25),
      new Monster("レアモン", 150, 100, 100, 5, 14));

  private final static int USE_NORMAL_BALL = 1;
  private final static int USE_SUPER_BALL = 2;
  private final static int USE_MIRACLE_BALL = 3;
  private final static int MISS_MONSTER = 4;

  private static List<CaptureBall> ballList = Arrays.asList(
      new CaptureBall("ノーマル捕獲玉", 0, 6),
      new CaptureBall("スーパー捕獲玉", 20, 3),
      new CaptureBall("ミラクル捕獲玉", 50, 1));

  public static void main(String[] args) {

    for (int monsterCount = 0; monsterCount < MONSTER_COUNT_MAX; monsterCount++) {
      appairMonster(monsterCount);
      AtomicBoolean canCaptured = new AtomicBoolean(false);
      AtomicBoolean isMissed = new AtomicBoolean(false);
      while (!canCaptured.get() && !isMissed.get() && !isUsedAllBall()) {
        int selectedMove = selectMove();
        executeMove(selectedMove, canCaptured, isMissed);
      }

      if (isUsedAllBall()) {
        System.out.println("捕獲玉がなくなってしまった！");
        break;
      }
    }

    printResult();

  }

  private static void appairMonster(int monsterCount) {
    nowMonster = monsterList.get(getAppairMonsterIndex());
    System.out.println(String.format("%d体目のモンスターが現れた！", monsterCount + 1));
    nowMonster.showInformation();
  }

  private static int getAppairMonsterIndex() {
    // 下記により各モンスターの出現率を実現する。
    // それぞれのモンスターの出現率を合計値に加算していき、加算した時点の合計値を記憶し、リストに格納する。
    // →出現率が10,20,30のモンスターが格納されている場合、出現率のリストは10,30,60となる。
    // 乱数を生成する際、合計値の最大を最大とする。
    // 出現率のリストの先に追加されたものから順番に、生成した乱数より大きいかを確認する。
    // 上記の例で乱数が25の場合、2番目のモンスターが出現する。
    List<Integer> encountRateList = new ArrayList<>();
    Integer encountRateTotal = 0;
    for (Monster monster : monsterList) {
      encountRateTotal += monster.getEncountRate();
      encountRateList.add(encountRateTotal);
    }

    Integer randomValue = random.nextInt(encountRateTotal);
    for (int i = 0; i < encountRateList.size(); i++) {
      if (randomValue < encountRateList.get(i)) {
        return i;
      }
    }

    // 以下の処理が行われるのは生成された乱数がどのモンスターにも当てはまらなかった場合である。想定外のためその旨を表示する。
    System.out.println("想定外の処理が行われました");
    return 0;
  }

  private static int selectMove() {
    System.err.println("君はどうする？");
    for (int i = 0; i < ballList.size(); i++) {
      CaptureBall ball = ballList.get(i);
      System.out.println(String.format(" (%d)%sを使う(残り%d個。捕獲成功率：%d％)", i + 1, ball.getName(), ball.getCount(),
          Math.min(Monster.CAPTURE_RATE_MAX, ball.getCorrectValue() + nowMonster.getCaptureRate())));
    }
    System.out.println(String.format(" (%d)見逃す", MISS_MONSTER));
    return scanner.nextInt();
  }

  private static void executeMove(int selectedMove, AtomicBoolean canCaptured, AtomicBoolean isMissed) {
    switch (selectedMove) {
      case USE_NORMAL_BALL:
      case USE_SUPER_BALL:
      case USE_MIRACLE_BALL:
        // 捕獲玉を使う場合の行動の番号は、捕獲玉のリストのインデックス+1になっている。そのため、選択された行動から1減算した値を用いて捕獲玉を選択する。
        CaptureBall selectedBall = ballList.get(selectedMove - 1);
        if (selectedBall.getCount() <= 0) {
          System.out.println(String.format("%s はもう持っていない！", selectedBall.getName()));
          return;
        }

        selectedBall.use();
        canCaptured.set(nowMonster.canCapture(selectedBall.getCorrectValue()));
        if (canCaptured.get()) {
          System.out.println(String.format("%s を捕獲した！", nowMonster.getName()));
          System.out.println(String.format("%dポイント取得した！", nowMonster.getPowerValue()));
          System.out.println("");
          capturedMonsterList.add(nowMonster);
        } else {
          System.out.println(String.format("捕獲に失敗した…", nowMonster.getName()));
          System.out.println("");
        }

        break;
      case MISS_MONSTER:
        System.out.println(String.format("%s に別れを告げた…", nowMonster.getName()));
        System.out.println("");
        isMissed.set(true);
        break;

      default:
        break;
    }

  }

  private static Boolean isUsedAllBall() {
    int totalBallCount = 0;
    for (CaptureBall ball : ballList) {
      totalBallCount += ball.getCount();
    }

    return totalBallCount <= 0;

  }

  private static void printResult() {
    System.out.println("ゲーム終了！");
    int captureCount = capturedMonsterList.size();
    if (0 < captureCount) {
      System.out.println(String.format("%d体のモンスターを捕獲した！", captureCount));
      System.out.println("──────────── 捕獲したモンスター ────────────");
      for (Monster monster : capturedMonsterList) {
        monster.showInformation();
        System.out.print("次へ> (Enterを押してください)");
        scanner.nextLine();
        System.out.println("");
      }
    } else {
      System.out.println("1体もモンスターを捕まえられなかった…");
    }
  }
}
