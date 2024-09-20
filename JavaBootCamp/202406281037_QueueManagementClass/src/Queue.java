import java.util.ArrayList;

public class Queue {
  private ArrayList<String> strList;

  Queue(){
    this.strList = new ArrayList<String>();
  }

  public void push(String value){
    this.strList.add(value);
  }

  public String pop(){
    String returnValue = this.strList.get(0);
    this.strList.remove(0);
    return returnValue;
  }
}
