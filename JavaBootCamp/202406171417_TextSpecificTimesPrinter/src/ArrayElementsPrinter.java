import java.util.ArrayList;

public class ArrayElementsPrinter {
  public static void main(String[] args) throws Exception {
    ArrayList<String> dogTypes = new ArrayList<String>();
    dogTypes.add("Chiwawa");
    dogTypes.add("Pomeranian");
    dogTypes.add("Bulldog");
    dogTypes.add("Akita");
    dogTypes.add("Poodle");
    for (int i = 1; i <= dogTypes.size(); i++) {
      System.out.printf("dogTypes の %d 番目の犬種は %s です.%n", i, dogTypes.get(i-1));
    }
  }
}
