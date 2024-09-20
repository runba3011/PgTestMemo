import java.util.ArrayList;

public class ArrayUpdater {
  public static void main(String[] args) throws Exception {
    ArrayList<Integer> array = new ArrayList<Integer>();
    array.add(1);
    array.add(2);
    array.add(3);
    array.add(4);
    array.add(5);
    updateArray(array);
    for(int number : array){
      System.out.printf("%d\n" , number);
    }
  }

  public static void updateArray(ArrayList<Integer> array){
    ArrayList<Integer> reversedArray = new ArrayList<>();
    for(int i = array.size() - 1;0 <= i;i--){
      reversedArray.add(array.get(i));
    }

    for(int i = 0;i <array.size();i++){
      array.set(i, reversedArray.get(i));
    }
  }
}
