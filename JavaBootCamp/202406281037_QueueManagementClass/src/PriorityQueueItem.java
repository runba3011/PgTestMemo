public class PriorityQueueItem {
  private String str;
  private int priority;

  PriorityQueueItem(String str , int priority){
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
