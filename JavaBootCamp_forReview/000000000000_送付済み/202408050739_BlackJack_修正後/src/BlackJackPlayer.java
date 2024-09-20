public class BlackJackPlayer extends Player{
  private int BURST_BORDER_POINT = 21; // この点を超えるとバーストする。「以上」ではない点に注意

  public BlackJackPlayer(String name) {
    super(name);
  }

  /**
   * 現在のポイントより、バーストしたかを取得する
   * 
   * @return バーストしたらtrueを返す
   */
  public Boolean isBursted() {
    return BURST_BORDER_POINT < getPoint();
  }

  /**
   * 現在のポイントを取得する
   * 
   * @return 現在のポイント
   */
  public int getPoint() {
    int totalPoint = 0;
    int aceCount = 0;
    for (Trump trump : this.trumpList) {
      int point = trump.getPoint();
      if (point == Trump.NUMBER_ACE) {
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

  public void printNowResult() {
    System.out.println(String.format("%sの合計は %d です", this.name, this.getPoint()));
  }
}
