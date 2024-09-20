public class Counter {
  private static int count;

  static {
    count = 0;
  }

  public Counter() {

    ++count;

    System.out.printf(
        "Counter インスタンスが生成されました: count=%d %n",
        count);
  }

  public static void showCounter() {
    System.out.printf("counter=%d %n", count);
  }
}