import java.util.ArrayList;
import java.util.List;

public class NumberList  {
    public static void main(String[] args) throws Exception {
      List<Integer> list = new ArrayList<Integer>();
      list.add(3);
      list.add(4);
      list.add(5);
      list.add(6);
      list.add(7);
      list.add(8);
      list.add(9);
      list.add(10);
      list.add(11);
      for(int i = 0 ; i < list.size();i++){
        System.out.printf("[%d] %d\n" , i , list.get(i));
      }
    }
}
