import java.util.ArrayList;

public class ArrayElementsAdder {
  public static void main(String[] args) throws Exception {
    ArrayList<Integer> numbers = new ArrayList<Integer>();
    numbers.add(3);
    numbers.add(4);
    numbers.add(5);
    numbers.add(-5);
    numbers.add(0);
    numbers.add(12);
    int total = 0;
    for (int num : numbers) {
      total += num;
    }
    System.out.printf("配列 numbers の %d 個の要素の合計 => %d", numbers.size(), total);
  }
}
