import java.util.ArrayList;
import java.util.Random;

public class RandomQueue {
  private static Random random = new Random();
  private ArrayList<String> strList;

  RandomQueue(){
    this.strList = new ArrayList<String>();
  }

  /**
   * リストの最後に要素を追加する
   * @param value 追加する値
   */
  public void push(String value){
    this.strList.add(value);
  }

  /**
   * ランダムな要素を取得し、削除する
   * @return 取得した値
   */
  public String pop(){
    int index = random.nextInt(this.strList.size());
    String returnValue = this.strList.get(index);
    strList.remove(index);
    return returnValue;
  }
}
