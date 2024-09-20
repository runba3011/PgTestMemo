import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class Player {
  private final int MAX_HP = 100;
  private int nowHp = 50;
  private final int HINT_COUNT_MAX = 3;
  private int hintCount = 0;
  
  private final int NOT_EAT_HP_DUCE_AMOUNT = 10;

  public static final int MOVE_EAT = 1;
  public static final int MOVE_NOT_EAT = 2;
  public static final int MOVE_HINT = 3;
  public static final int MOVE_DO_NOTHING = 4;

  public int getNowHp() {
    return nowHp;
  }

  public int selectMove() {
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

  private Boolean isSelectedSomeMove(int selectedMove, AtomicBoolean isSelectMoveSuccess) {
    if (!isSelectMoveSuccess.get()) {
      return false;
    }

    List<Integer> moveList = Arrays.asList(MOVE_EAT, MOVE_NOT_EAT, MOVE_HINT);
    return moveList.contains(selectedMove);
  }

  public void executeMove(EatItem nowEatItem , int move) {
    switch (move) {
      case MOVE_EAT:
        executeEat(nowEatItem);
        break;
      case MOVE_NOT_EAT:
        executeNotEat(nowEatItem);
        break;
      case MOVE_HINT:
        executeHint(nowEatItem);
        break;

      case MOVE_DO_NOTHING:
        executeDoNothing();
        break;
      default:
        break;
    }
  }

  public void executeEat(EatItem nowEatItem) {
    System.out.println(String.format("%s を食べようと試みました…", nowEatItem.getItemName()));
    if (nowEatItem.canEat()) {
      System.out.println("完食！HPが回復しました！");
      nowHp = Math.min(nowHp + nowEatItem.getExpectedHeelingHP(), MAX_HP);
    } else {
      System.out.println("あなたの命は終焉を迎えた！");
      System.out.println(String.format("死因：%s", nowEatItem.getCauseOfDeath()));
      nowHp = 0;
    }
  }

  public void executeNotEat(EatItem nowEatItem) {
    System.out.println(String.format("%s を食べませんでした。HPが10減りました。", nowEatItem.getItemName()));
    nowHp -= NOT_EAT_HP_DUCE_AMOUNT;
    if (isDead()) {
      deathByNotEating();
    }
  }

  public void executeHint(EatItem nextEatItem) {
    hintCount++;
    if (HINT_COUNT_MAX < hintCount) {
      System.out.println("…何も起きなかった。");
      return;
    }

    System.out.println("突如、君の頭の中に明日の食べ物についての情報が流れてくる！");
    nextEatItem.showInformation();
    System.out.println("ただし、目の前にある食べ物は変わらないことを君は思い出した…");
    System.out.println("");
  }

  public void executeDoNothing() {
    nowHp -= NOT_EAT_HP_DUCE_AMOUNT;
    System.out.println("あたふたしている間にいつの間にか食べ物はなくなってしまっていた！");
    if (isDead()) {
      deathByNotEating();
      return;
    }

    System.out.println("次からはきちんと行動を選択しようと、あなたは理解しました…");
  }

  public Boolean isDead() {
    return nowHp <= 0;
  }

  public void deathByNotEating() {
    System.out.println("食べていたら生存していたのではと後悔を残しながら、あなたはの命は終わりを迎えました…");
  }

  public void showNowHp() {
    System.out.println(String.format("現在のHP:%d", nowHp));
  }
}
