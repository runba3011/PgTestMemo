import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class MonsterGetManager {
  private Player player = new Player();
  private Random random = new Random();
  private Monster nowMonster;
  private List<Monster> monsterList = Arrays.asList(
      new Monster("ザコモン", 30, 20, 20, 30, 72),
      new Monster("フツモン", 50, 20, 30, 30, 50),
      new Monster("ツヨモン", 100, 50, 30, 25, 28),
      new Monster("ボスモン", 100, 50, 50, 10, 25),
      new Monster("レアモン", 150, 100, 100, 5, 14));

  /**
   * モンスター捕獲ゲームを開始する
   */
  public void start() {
    for (int monsterCount = 1; monsterCount <= Define.MONSTER_COUNT_MAX; monsterCount++) {
      player.resetPlayerFlag();

      appairMonster(monsterCount);
      while (!player.isCaptured() && !player.isMissed() && !player.isUsedAllBall()) {
        int selectedMove = player.selectMove(nowMonster);
        player.executeMove(nowMonster, selectedMove);
      }

      if (player.isUsedAllBall()) {
        System.out.println("捕獲玉がなくなってしまった！");
        break;
      }
    }

    printResult();
  }

  /**
   * 新たなモンスターを出現させる
   * @param monsterCount モンスターの数
   */
  private void appairMonster(int monsterCount) {
    nowMonster = monsterList.get(getAppairMonsterIndex());
    System.out.println(String.format("%d体目のモンスターが現れた！", monsterCount));
    nowMonster.showInformation();
  }

  /**
   * 出現したモンスターの、モンスターのリスト内のインデックスを取得する
   * @return 出現したモンスターの、モンスターのリスト内のインデックス
   */
  private int getAppairMonsterIndex() {
    // 下記により各モンスターの出現率を実現する。
    // それぞれのモンスターの出現率を合計値に加算していき、加算した時点の合計値を記憶し、リストに格納する。
    // →出現率が10,20,30のモンスターが格納されている場合、出現率のリストは10,30,60となる。
    // 乱数を生成する際、合計値の最大を最大とする。
    // 出現率のリストの先に追加されたものから順番に、生成した乱数より大きいかを確認する。
    // 上記の例で乱数が25の場合、2番目のモンスターが出現する。
    List<Integer> encountRateList = new ArrayList<>();
    int encountRateTotal = 0;
    for (Monster monster : monsterList) {
      encountRateTotal += monster.getEncountRate();
      encountRateList.add(encountRateTotal);
    }

    int randomValue = random.nextInt(encountRateTotal);
    for (int i = 0; i < encountRateList.size(); i++) {
      if (randomValue < encountRateList.get(i)) {
        return i;
      }
    }

    // 以下の処理が行われるのは生成された乱数がどのモンスターにも当てはまらなかった場合である。想定外のためその旨を表示する。
    System.out.println("想定外の処理が行われました");
    return 0;
  }

  /**
   * ゲーム終了時のメッセージを表示する
   */
  private void printResult() {
    System.out.println("ゲーム終了！");
    int captureCount = player.getCapturedMonsterList().size();
    if (0 < captureCount) {
      System.out.println(String.format("%d体のモンスターを捕獲した！", captureCount));
      System.out.println("──────────── 捕獲したモンスター ────────────");
      for (Monster monster : player.getCapturedMonsterList()) {
        monster.showInformation();
        System.out.print("次へ> (Enterを押してください)");
        App.scanner.nextLine();
        System.out.println("");
      }
    } else {
      System.out.println("1体もモンスターを捕まえられなかった…");
    }
  }
}
