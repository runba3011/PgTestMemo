import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class TrumpManager {
  private static List<Trump> lastTrumps = new ArrayList<>();
  private static List<Trump> playerTrumps = new ArrayList<>();
  private static List<Trump> dealerTrumps = new ArrayList<>();

  private static Integer DEALER_DRAW_BORDER_POINT = 17; // この点を超えるとディーラーはドローしない。「以上」ではない点に注意
  private static Integer BURST_BORDER_POINT = 21; // この点を超えるとバーストする。「以上」ではない点に注意
  private final static Integer FIRST_TURN_DISTRIBUTE_COUNT = 2;

  public static void main(String[] args) throws Exception {
    reset();
    shuffle();
    firstTrunDistribute();
    AtomicBoolean isPlayerBursted = new AtomicBoolean(false);
    AtomicBoolean isDealerBursted = new AtomicBoolean(false);
    printNowResult(dealerTrumps, isDealerBursted);
    printNowResult(playerTrumps, isPlayerBursted);
    playerAdditionalDray(isPlayerBursted);
    if (isPlayerBursted.get()) {
      System.out.println("バーストしました。あなたの負けです");
      return;
    }

    dealerAdditionalDraw(isDealerBursted);
    if (isDealerBursted.get()) {
      System.out.println("ディーラーがバーストしました。あなたの勝ちです");
      return;
    }

    printResult();

  }

  private static void reset() {
    lastTrumps = new ArrayList<>();
    for (Trump.mark mark : Trump.mark.values()) {
      for (Integer number = Trump.NUMBER_MIN; number <= Trump.NUMBER_MAX; number++) {
        lastTrumps.add(new Trump(mark, number));
      }
    }
    playerTrumps = new ArrayList<>();
    dealerTrumps = new ArrayList<>();
  }

  private static void firstTrunDistribute() {
    for (int i = 0; i < FIRST_TURN_DISTRIBUTE_COUNT; i++) {
      distributeTrump(true);
      distributeTrump(false);
    }
    System.err.println("");
  }

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

  private static void printNowResult(List<Trump> trumpList, AtomicBoolean isBursted) {

    Integer total;
    total = getPoint(trumpList);
    if (BURST_BORDER_POINT < total)
      isBursted.set(true);

    String message = String.format("%sの合計は %d です", trumpList.equals(playerTrumps) ? "現在" : "ディーラー", total);
    System.out.println(message);
  }

  private static void playerAdditionalDray(AtomicBoolean isPlayerBursted) {
    while (isPlayerContinueDrawTrump()) {
      distributeTrump(true);
      printNowResult(playerTrumps, isPlayerBursted);

      if (isPlayerBursted.get()) {
        return;
      }
    }
  }

  private static Boolean isPlayerContinueDrawTrump() {
    System.out.print("もう1枚カードを引きますか？");
    AtomicBoolean isSuccess = new AtomicBoolean(false);
    Boolean result = false;
    while (!isSuccess.get()) {
      result = CommonInputManager.inputYesOrNo(isSuccess);
    }
    System.out.println("");

    return result;
  }

  private static void dealerAdditionalDraw(AtomicBoolean isDealerBursted) {

    while (isDealerContinueDrawTrump()) {
      distributeTrump(false);
      printNowResult(dealerTrumps, isDealerBursted);
      System.out.println("");
      if (isDealerBursted.get()) {
        return;
      }
    }

  }

  private static Boolean isDealerContinueDrawTrump() {
    return getPoint(dealerTrumps) < DEALER_DRAW_BORDER_POINT;
  }

  private static void shuffle() {
    Collections.shuffle(lastTrumps);
  }

  private static Trump draw() {
    return lastTrumps.remove(0);
  }

  private static Integer getPoint(List<Trump> trumpList) {
    Integer totalPoint = 0;
    Integer aceCount = 0;
    for (Trump trump : trumpList) {
      Integer point = trump.getPoint();
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

  private static void printResult() {
    Integer playerPoint = getPoint(playerTrumps);
    Integer dealerPoint = getPoint(dealerTrumps);
    Boolean isPlayerWin = dealerPoint < playerPoint;
    Boolean isDraw = dealerPoint == playerPoint;
    if (isDraw) {
      System.out.println("引き分けです");
      return;
    }

    System.out.printf("あなたの%sです", isPlayerWin ? "勝ち" : "負け");
  }
}
