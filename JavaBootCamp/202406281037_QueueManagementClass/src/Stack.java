import java.util.ArrayList;

public class Stack {
  private ArrayList<String> strList;
  
  Stack(){
    this.strList = new ArrayList<String>();
  }

  public void push(String value){
    this.strList.add(value);
  }

  public String pop(){
    int index = this.strList.size() - 1;
    String returnValue = this.strList.get(index);
    strList.remove(index);
    return returnValue;
  }
}
