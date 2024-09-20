import java.util.ArrayList;

public class Queue {
  private ArrayList<String> strList;

  Queue(){
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
   * 最初に追加した要素を取得し、削除する
   * @return 取得した値
   */
  public String pop(){
    return this.strList.remove(0);
  }
}
