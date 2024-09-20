import java.util.ArrayList;

public class PriorityQueue {
  private ArrayList<PriorityQueueItem> strList;

  PriorityQueue() {
    this.strList = new ArrayList<PriorityQueueItem>();
  }

  public void push(String str , int priority) {
    this.strList.add(new PriorityQueueItem(str , priority));
  }

  public String pop() {
    int index = getMaxPriorityIndex();
    String returnValue = this.strList.get(index).getStr();
    strList.remove(index);
    return returnValue;
  }

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
