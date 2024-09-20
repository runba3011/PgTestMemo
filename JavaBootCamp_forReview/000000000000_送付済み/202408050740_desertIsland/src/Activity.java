public class Activity {
  private int dayCount;
  private int todayStartHp;
  private EatItem eatItem;
  private int move;
  private Boolean isDead;

  public Activity(int dayCount, int todayHp, EatItem eatItem, int move, Boolean isDead) {
    this.dayCount = dayCount;
    this.todayStartHp = todayHp;
    this.eatItem = eatItem;
    this.move = move;
    this.isDead = isDead;
  }
  

  public Boolean getIsDead() {
    return isDead;
  }


  public void showInformation() {
    System.out.println(String.format("%d日目：", this.dayCount));
    System.out.println(String.format("開始時点のHP:%s", this.todayStartHp));
    System.out.println("食べ物：");
    this.eatItem.showInformation();;
    String moveString = "";
    switch (this.move) {
      case Player.MOVE_EAT:
        moveString = "食べた";
        break;
      case Player.MOVE_NOT_EAT:
        moveString = "食べなかった";
        break;
      case Player.MOVE_DO_NOTHING:
        moveString = "あたふたした";
        break;

      default:
        break;
    }
    System.out.println(String.format("行動：%s", moveString));
  }

}
