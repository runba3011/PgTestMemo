import java.util.Random;;

public class MoreNarrowScope {
  public static void main(String[] args) {
    Random rand = new Random();
    int count = 0;
    boolean isHit = false;
    while (!isHit) {
      int num = rand.nextInt(10);
      count++;
      System.out.printf(("%d 回目の数字 -> %d\n"),count , num);
      if (num == 5)
        isHit = true;
    }
  }
}
