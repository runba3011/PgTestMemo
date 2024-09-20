import java.util.ArrayList;

public class PriorityQueue {
  // 文字列と優先度がセットのクラスはこのPriorityQueue内でしか使用しないため、このクラスのネストクラスとする
  public class PriorityQueueItem {
    private String str;
    private int priority;

    PriorityQueueItem(String str, int priority) {
      this.str = str;
      this.priority = priority;
    }

    public String getStr() {
      return str;
    }

    public int getPriority() {
      return priority;
    }
  }

  private ArrayList<PriorityQueueItem> strList;

  PriorityQueue() {
    this.strList = new ArrayList<PriorityQueueItem>();
  }

  /**
   * リストに要素を追加する
   * 
   * @param value 追加する値
   */
  public void push(String str, int priority) {
    this.strList.add(new PriorityQueueItem(str, priority));
  }

  /**
   * もっとも優先度が高い要素を取得し、削除する
   * @return 取得した値
   */
  public String pop() {
    int index = getMaxPriorityIndex();
    String returnValue = this.strList.get(index).getStr();
    strList.remove(index);
    return returnValue;
  }

  /**
   * もっとも優先度が高い要素のインデックスを取得する
   * @return
   */
  public int getMaxPriorityIndex() {
    int maxPriorityIndex = 0;
    int maxPriority = Integer.MIN_VALUE;
    for (int i = 0; i < this.strList.size(); i++) {
      int nowPriority = this.strList.get(i).getPriority();
      if (maxPriority < nowPriority) {
        maxPriority = nowPriority;
        maxPriorityIndex = i;
      }
    }
    return maxPriorityIndex;
  }
}
