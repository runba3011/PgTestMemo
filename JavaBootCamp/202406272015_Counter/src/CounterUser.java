public class CounterUser {

  private static final int INSTANCE_NUMS = 6;

  public static void main(String[] args) {

    Counter[] counters = new Counter[INSTANCE_NUMS];

    for (Counter counter : counters) {
      counter = new Counter();
    }

    Counter.showCounter();
  }
}