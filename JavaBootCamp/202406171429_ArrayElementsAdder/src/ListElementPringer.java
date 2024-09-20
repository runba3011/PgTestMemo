import java.util.ArrayList;

public class ListElementPringer {
  public static void main(String[] args) {
    ArrayList<String> list = new ArrayList<String>();
    list.add("000");
    list.add("111");
    list.add("222");
    
    for(String str : list){
      System.out.printf("%d 番目の要素 => %s%n",list.indexOf(str),str);
    }
  }
}
