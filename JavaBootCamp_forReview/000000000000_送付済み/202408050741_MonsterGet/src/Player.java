import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Player {
  public enum Action {
    USE_NORMAL_BALL,
    USE_SUPER_BALL,
    USE_MIRACLE_BALL,
    MISS_MONSTER
  }

  private boolean isCaptured = false;
  private boolean isMissed = false;
  private List<Monster> capturedMonsterList = new ArrayList<>();
  private List<CaptureBall> ballList = Arrays.asList(
      new CaptureBall("ノーマル捕獲玉", 0, 6),
      new CaptureBall("スーパー捕獲玉", 20, 3),
      new CaptureBall("ミラクル捕獲玉", 50, 1));

  /**
   * 下記のフラグをリセットする<p>
   * ・捕獲済みフラグ<p>
   * ・見逃しフラグ<p>
   */
  public void resetPlayerFlag(){
    this.isCaptured =false;
    this.isMissed = false;
  }

  public boolean isCaptured() {
    return isCaptured;
  }

  public boolean isMissed() {
    return isMissed;
  }

  public List<Monster> getCapturedMonsterList() {
    return capturedMonsterList;
  }

  public List<CaptureBall> getBallList() {
    return ballList;
  }


  /**
   * 行動を選択させる
   * @param nowMonster
   * @return 選択した行動の番号
   */
  public int selectMove(Monster nowMonster) {
    System.err.println("君はどうする？");
    for (int i = 0; i < this.ballList.size(); i++) {
      CaptureBall ball = this.ballList.get(i);
      System.out.println(String.format(" (%d)%sを使う(残り%d個。捕獲成功率：%d％)", i, ball.getName(), ball.getCount(),
          Math.min(Define.CAPTURE_RATE_MAX, ball.getCorrectValue() + nowMonster.getCaptureRate())));
    }
    System.out.println(String.format(" (%d)見逃す", Action.MISS_MONSTER.ordinal()));
    return App.scanner.nextInt();
  }

  /**
   * 行動の番号がいずれかの行動にあてはまるかを確認する
   * @param inputtedNumber 行動の番号
   * @return true:当てはまる false:当てはまらない
   */
  public boolean isInputtedMoveNumber(int inputtedNumber){
    Arrays.stream(Action.values()).anyMatch(action -> action.ordinal() == inputtedNumber);
    return false;
  }

  /**
   * 入力された番号を行動に変換する
   * @param inputtedNumber 入力された番号
   * @return 行動
   */
  public Action convertNumberToAction(int inputtedNumber){
    return Arrays.stream(Action.values()).filter(action -> action.ordinal() == inputtedNumber).findFirst().get();
  }

  /**
   * 行動を実行する
   * @param nowMonster 行動を実行する対象のモンスター
   * @param selectedMove 選択された行動
   */
  public void executeMove(Monster nowMonster , int selectedMove) {
    Action action = convertNumberToAction(selectedMove);
    switch (action) {
      case USE_NORMAL_BALL:
      case USE_SUPER_BALL:
      case USE_MIRACLE_BALL:
        // 捕獲玉を使う場合の行動の番号は、捕獲玉のリストのインデックス+1になっている。そのため、選択された行動から1減算した値を用いて捕獲玉を選択する。
        CaptureBall selectedBall = ballList.get(action.ordinal() - 1);
        if (selectedBall.getCount() <= 0) {
          System.out.println(String.format("%s はもう持っていない！", selectedBall.getName()));
          return;
        }

        selectedBall.use();
        isCaptured = nowMonster.getIsCaptured(selectedBall.getCorrectValue());
        if (isCaptured) {
          System.out.println(String.format("%s を捕獲した！", nowMonster.getName()));
          System.out.println(String.format("%dポイント取得した！", nowMonster.getPowerValue()));
          System.out.println("");
          capturedMonsterList.add(nowMonster);
          isCaptured = true;
        } else {
          System.out.println(String.format("捕獲に失敗した…", nowMonster.getName()));
          System.out.println("");
          isCaptured = false;
        }

        break;
      case MISS_MONSTER:
        System.out.println(String.format("%s に別れを告げた…", nowMonster.getName()));
        System.out.println("");
        isMissed = true;
        break;

      default:
        break;
    }

  }

  /**
   * ボールをすべて消費したかを確認する
   * @return true:消費した false:消費していない
   */
  public boolean isUsedAllBall() {
    int totalBallCount = 0;
    for (CaptureBall ball : ballList) {
      totalBallCount += ball.getCount();
    }
    return totalBallCount <= 0;
  }
}