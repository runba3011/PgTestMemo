import java.util.Arrays;
import java.util.List;

public class Trump {
  public enum mark {
    Diamonds("ダイヤ"),
    Hearts("ハート"),
    Clubs("クラブ"),
    Spades("スペード");

    String markName;

    private mark(String markName){
      this.markName = markName;
    }

    @Override
    public String toString(){
      return this.markName;
    }
  }

  public final static int NUMBER_MIN = 1;
  public final static int NUMBER_MAX = 5;
  public final static List<Integer> POINT_10_NUMBER_LIST = Arrays.asList(11, 12 , 13);
  public final static int POINT_10 = 10;
  public final static int NUMBER_ACE = 1;
  public final static int ACE_POINT_HIGH = 11;
  public final static int ACE_POINT_LOW = 1;

  private mark mark;
  private int number;

  Trump(mark mark, int number) {
    this.mark = mark;
    this.number = number;
  }

  /**
   * マークと番号の情報を文字列で取得する
   * @return マークと番号の情報を文字列で取得した値
   */
  public String getInformation() {
    String value = "";
    value += this.mark.toString();
    value += " ";
    value += this.numberToString();
    return value;
  }

  /**
   * 数字を文字列に変換する
   * 1の場合はAとする などに対処するための関数
   * @return 数字をもとに取得した文字列
   */
  public String numberToString() {
    switch (this.number) {
      case 1:
        return "A";
      case 11:
        return "J";
      case 12:
        return "Q";
      case 13:
        return "K";
      default:
        return String.valueOf(this.number);
    }
  }

  /**
   * 番号よりポイントを取得する
   * @return 取得したポイント
   */
  public int getPoint() {
    if (POINT_10_NUMBER_LIST.contains(this.number)) {
      return POINT_10;
    }

    return this.number;
  }
}
