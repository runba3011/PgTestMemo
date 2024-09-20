import java.util.ArrayList;

public class Stack {
  private ArrayList<String> strList;
  
  Stack(){
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
   * 最後に追加した要素を取得し、削除する
   * @return 取得した値
   */
  public String pop(){
    return strList.remove(this.strList.size() - 1);
  }
}
