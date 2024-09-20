public class NumberAdder {
  public static void main(String[] args) throws Exception {
    NumberAdder numberAdder = new NumberAdder();
    numberAdder.addNumbers(25, 15);
  }

  public void addNumbers(int orangeCount, int appleCount) {
    System.out.printf("みかん: %d個\n" , orangeCount);
    System.out.printf("りんご: %d個\n" , appleCount);;
    System.out.printf("合計: %d個\n" , orangeCount + appleCount);
  }
}
