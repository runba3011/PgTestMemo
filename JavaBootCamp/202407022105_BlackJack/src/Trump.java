import java.util.Arrays;
import java.util.List;

public class Trump {
  public enum mark {
    Diamonds,
    Hearts,
    Clubs,
    Spades
  }

  public final static Integer NUMBER_MIN = 1;
  public final static Integer NUMBER_MAX = 13;
  public final static List<Integer> POINT_10_NUMBER_LIST = Arrays.asList(11, 12 , 13);
  public final static Integer POINT_10 = 10;
  public final static Integer NUMBER_ACE = 1;
  public final static Integer ACE_POINT_HIGH = 11;
  public final static Integer ACE_POINT_LOW = 1;

  private mark mark;
  private Integer number;

  Trump(mark mark, Integer number) {
    this.mark = mark;
    this.number = number;
  }

  public String getInformation() {
    String value = "";
    switch (this.mark) {
      case Diamonds:
        value += "ダイヤ";
        break;
      case Hearts:
        value += "ハート";
        break;
      case Clubs:
        value += "クラブ";
        break;
      case Spades:
        value += "スペード";
        break;
      default:
        return "想定外の処理が行われました";
    }

    value += " ";

    switch (this.number) {
      case 1:
        value += "A";
        break;
      case 11:
        value += "J";
        break;
      case 12:
        value += "Q";
        break;
      case 13:
        value += "K";
        break;
      default:
        value += String.valueOf(this.number);
        break;
    }

    return value;
  }

  public Integer getPoint() {
    if (POINT_10_NUMBER_LIST.contains(this.number)) {
      return POINT_10;
    }

    return this.number;
  }
}
