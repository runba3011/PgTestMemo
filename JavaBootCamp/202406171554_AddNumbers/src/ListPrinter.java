import java.util.ArrayList;

public class ListPrinter {
  public static void main(String[] args) throws Exception {
    ArrayList<String> list = new ArrayList<String>();
    list.add("Chiwawa");
    list.add("Pomeranian");
    list.add("Bulldog");
    list.add("Akita");
    list.add("Poodle");
    ListPrinter printer = new ListPrinter();
    printer.printAllElements(list);
  }

  public void printAllElements(ArrayList<String> list){
    for(int i = 0;i < list.size();i++){
      System.out.printf("%d => %s\n",i,list.get(i));
    }
  }
}
