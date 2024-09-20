public class BlackJack {
  TrumpManager trumpManager = new TrumpManager();
  private BlackJackPlayer player = new BlackJackPlayer("あなた");
  private BlackJackPlayer dealer = new BlackJackPlayer("ディーラー");

  private final int DEALER_DRAW_BORDER_POINT = 17; // この点を超えるとディーラーはドローしない。「以上」ではない点に注意
  private final int FIRST_TURN_DISTRIBUTE_COUNT = 2;

  public void start() {
    try {
      trumpManager.reset();
      trumpManager.shuffle();
      firstTrumpDistribute();
      dealer.printNowResult();
      player.printNowResult();
      playerAdditionalDraw();
      if (player.isBursted()) {
        System.out.println(String.format("バーストしました。%s の負けです", player.getName()));
        return;
      }

      dealerAdditionalDraw();
      if (dealer.isBursted()) {
        System.out.println(String.format("%sがバーストしました。%sの勝ちです", dealer.getName(), player.getName()));
        return;
      }
      printResult();
    } catch (OutOfCardsException e) {
      // カードが0枚の状態でドローしようとした場合が見つかった場合
      // ※ドローする前にカードが0枚でないことをチェックしているためこの処理が通る機会はない想定である。
      System.out.println(e.getMessage());
    }
  }

  /**
   * ゲーム開始時のカード2枚配布を行う
   */
  private void firstTrumpDistribute() throws OutOfCardsException {
    for (int i = 0; i < FIRST_TURN_DISTRIBUTE_COUNT; i++) {
      if (trumpManager.isAbleDraw()) {
        distributeTrump(player);
      }
      if (trumpManager.isAbleDraw()) {
        distributeTrump(dealer);
      }
    }
    System.err.println("");
  }

  /**
   * トランプをドローする
   * 
   * @param player ドローするプレイヤー
   */
  private void distributeTrump(Player player) throws OutOfCardsException {
    Trump trump = trumpManager.draw();
    player.addTrump(trump);
    System.out.println(String.format("%s に 「%s」 が配られました", player.getName(), trump.getInformation()));
  }

  /**
   * プレイヤーの追加のドローを行う
   */
  private void playerAdditionalDraw() throws OutOfCardsException {
    while (trumpManager.isAbleDraw() && isPlayerContinueDrawTrump()) {
      distributeTrump(player);
      if (player.isBursted()) {
        return;
      }
    }
  }

  /**
   * プレイヤーが追加のドローを行うかの入力をさせる
   * 
   * @return
   */
  private Boolean isPlayerContinueDrawTrump() {
    System.out.print("もう1枚カードを引きますか？");
    // YかNが入力されるまでループするための変数
    // 初回は必ずループされるよう、失敗したものとして初期化する
    Boolean isFail = true;
    Boolean isYes = false;
    while (isFail) {
      CommonInputManager.printYesOrNoMessage();
      String str = CommonInputManager.inputString();
      isFail = !CommonInputManager.isInputtedYesOrNo(str);
      if (!isFail) {
        isYes = CommonInputManager.isYes(str);
      } else {
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
  private void dealerAdditionalDraw() throws OutOfCardsException {
    while (trumpManager.isAbleDraw() && isDealerContinueDrawTrump()) {
      distributeTrump(dealer);
      System.out.println("");
    }
  }

  /**
   * ディーラーがドローするかを取得する
   * 
   * @return ドローするか
   */
  private Boolean isDealerContinueDrawTrump() {
    return dealer.getPoint() < DEALER_DRAW_BORDER_POINT;
  }

  /**
   * ゲームの結果を表示する
   */
  private void printResult() {
    System.out.println();
    System.out.println("------------------------ 結果 ------------------------");
    player.printNowResult();
    dealer.printNowResult();

    int playerPoint = player.getPoint();
    int dealerPoint = dealer.getPoint();
    Boolean isPlayerWin = dealerPoint < playerPoint;
    Boolean isDraw = dealerPoint == playerPoint;
    if (isDraw) {
      System.out.println("引き分けです");
      return;
    }

    System.out.printf("%sの%sです", player.getName(), isPlayerWin ? "勝ち" : "負け");
  }
}
