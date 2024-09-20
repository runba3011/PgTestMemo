import java.util.ArrayList;
import java.util.Random;

public class RandomQueue {
  private static Random random = new Random();
  private ArrayList<String> strList;

  RandomQueue(){
    this.strList = new ArrayList<String>();
  }

  public void push(String value){
    this.strList.add(value);
  }

  public String pop(){
    int index = random.nextInt(this.strList.size());
    String returnValue = this.strList.get(index);
    strList.remove(index);
    return returnValue;
  }
}
