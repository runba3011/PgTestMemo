import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TrumpManager {
  private static List<Trump> lastTrumps = new ArrayList<>();
  private static List<Trump> playerTrumps = new ArrayList<>();
  private static List<Trump> dealerTrumps = new ArrayList<>();

  private static int DEALER_DRAW_BORDER_POINT = 17; // この点を超えるとディーラーはドローしない。「以上」ではない点に注意
  private static int BURST_BORDER_POINT = 21; // この点を超えるとバーストする。「以上」ではない点に注意
  private final static int FIRST_TURN_DISTRIBUTE_COUNT = 2;

  private static Boolean isPlayerBursted = false;
  private static Boolean isDealerBursted = false;
  public static void main(String[] args) throws Exception {
    reset();
    shuffle();
    firstTrunDistribute();
    printNowResult(getPoint(dealerTrumps) , true);
    printNowResult(getPoint(playerTrumps) , false);
    playerAdditionalDray();
    if (isPlayerBursted) {
      System.out.println("バーストしました。あなたの負けです");
      return;
    }

    dealerAdditionalDraw();
    if (isDealerBursted) {
      System.out.println("ディーラーがバーストしました。あなたの勝ちです");
      return;
    }

    printResult();

  }

  /**
   * トランプをリセット（各種のマークのトランプが13枚格納された状態に）する
   */
  private static void reset() {
    lastTrumps = new ArrayList<>();
    for (Trump.mark mark : Trump.mark.values()) {
      for (int number = Trump.NUMBER_MIN; number <= Trump.NUMBER_MAX; number++) {
        lastTrumps.add(new Trump(mark, number));
      }
    }
    playerTrumps = new ArrayList<>();
    dealerTrumps = new ArrayList<>();
  }

  /**
   * カードのシャッフルを行う
   */
  private static void shuffle() {
    Collections.shuffle(lastTrumps);
  }

  /**
   * カードのドローを行う
   * @return
   */
  private static Trump draw() {
    return lastTrumps.remove(0);
  }

  /**
   * ゲーム開始時のカード2枚配布を行う
   */
  private static void firstTrunDistribute() {
    for (int i = 0; i < FIRST_TURN_DISTRIBUTE_COUNT; i++) {
      distributeTrump(true);
      distributeTrump(false);
    }
    System.err.println("");
  }

  /**
   * トランプをドローする
   * @param isPlayer プレイヤーがドローするならtrue,ディーラーならfalseを渡す
   */
  private static void distributeTrump(Boolean isPlayer) {
    Trump trump = draw();
    if (isPlayer) {
      playerTrumps.add(trump);
    } else {
      dealerTrumps.add(trump);
    }
    String message = isPlayer ? "あなたに" : "ディーラーに";
    message += "「" + trump.getInformation() + "」";
    message += "が配られました";
    System.out.println(message);
  }


  /**
   * トランプをドローする
   * @param isPlayer ディーラーの結果を表示するならtrue,そうでないならfalseを渡す
   */
  private static void printNowResult(int nowPoint , Boolean isDealer) {
    String message = String.format("%sの合計は %d です", isDealer ? "ディーラー" : "現在", nowPoint);
    System.out.println(message);
  }

  /**
   * プレイヤーの追加のドローを行う
   */
  private static void playerAdditionalDray() {
    while (isPlayerContinueDrawTrump()) {
      distributeTrump(true);
      int nowPoint = getPoint(playerTrumps);
      printNowResult(nowPoint , false);
      isPlayerBursted = isBursted(nowPoint);

      if (isPlayerBursted) {
        return;
      }
    }
  }

  /**
   * 現在のポイントより、バーストしたかを取得sるう
   * @param nowPoint 現在のポイント
   * @return バーストしたか
   */
  private static Boolean isBursted(int nowPoint){
    return BURST_BORDER_POINT < nowPoint;
  }

  /**
   * プレイヤーが追加のドローを行うかの入力をさせる
   * @return
   */
  private static Boolean isPlayerContinueDrawTrump() {
    System.out.print("もう1枚カードを引きますか？");
    //YかNが入力されるまでループするための変数
    //初回は必ずループされるよう、失敗したものとして初期化する
    Boolean isFail = true;
    Boolean isYes = false;
    while (isFail) {
      CommonInputManager.printYesOrNoMessage();
      String str = CommonInputManager.inputString();
      isFail = !CommonInputManager.isInputtedYesOrNo(str);
      if(!isFail){
        isYes = CommonInputManager.isYes(str);
      }
      else{
        CommonInputManager.printYesOrNoFailMessage();
      }
    }
    System.out.println("");
    return isYes;
  }

  /**
   * ディーラーが追加のドローを行う
   * ディーラーは特定ポイントを上回るまでドローし続ける
   */
  private static void dealerAdditionalDraw() {

    while (isDealerContinueDrawTrump()) {
      distributeTrump(false);
      int nowPoint = getPoint(dealerTrumps);
      printNowResult(nowPoint, true);
      isDealerBursted = isBursted(nowPoint);
      System.out.println("");
      if (isDealerBursted) {
        return;
      }
    }
  }

  /**
   * ディーラーがドローするかを取得する
   * @return ドローするか
   */
  private static Boolean isDealerContinueDrawTrump() {
    return getPoint(dealerTrumps) < DEALER_DRAW_BORDER_POINT;
  }

  /**
   * トランプのリストより、現在のポイントを取得する
   * @param trumpList トランプのリスト
   * @return 現在のポイント
   */
  private static int getPoint(List<Trump> trumpList) {
    int totalPoint = 0;
    int aceCount = 0;
    for (Trump trump : trumpList) {
      int point = trump.getPoint();
      if (point.equals(Trump.NUMBER_ACE)) {
        // エースが出た場合は1,もしくは11のどちらかのうち
        // ユーザーにとって都合のいいものを選択する必要がある。
        // そのため現段階では合計には加算しない。
        aceCount++;
        continue;
      }

      totalPoint += point;
    }

    while (0 < aceCount) {
      Boolean isBurst = BURST_BORDER_POINT < totalPoint + aceCount * Trump.ACE_POINT_HIGH;
      if (isBurst) {
        totalPoint += Trump.ACE_POINT_LOW;
      } else {
        totalPoint += Trump.ACE_POINT_HIGH;
      }
      aceCount--;
    }

    return totalPoint;
  }

  /**
   * ゲームの結果を表示する
   */
  private static void printResult() {
    int playerPoint = getPoint(playerTrumps);
    int dealerPoint = getPoint(dealerTrumps);
    Boolean isPlayerWin = dealerPoint < playerPoint;
    Boolean isDraw = dealerPoint == playerPoint;
    if (isDraw) {
      System.out.println("引き分けです");
      return;
    }

    System.out.printf("あなたの%sです", isPlayerWin ? "勝ち" : "負け");
  }
}
