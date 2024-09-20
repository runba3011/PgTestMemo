import java.util.ArrayList;
import java.util.List;

public class Player {
  protected String name;
  protected List<Trump> trumpList = new ArrayList<Trump>();

  public Player(String name) {
    this.name = name;
  }

  /**
   * 名前を取得する
   * 
   * @return 名前
   */
  public String getName() {
    return name;
  }

  /**
   * トランプのリストを取得する
   * 
   * @return トランプのリスト
   */
  public List<Trump> getTrumpList() {
    return trumpList;
  }

  /**
   * トランプのリストに新たなトランプを追加する
   * 
   * @param trump 追加するトランプ
   */
  public void addTrump(Trump trump) {
    this.trumpList.add(trump);
  }
}
