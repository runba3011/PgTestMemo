import java.util.Random;

public class HpChecker {
  public static void main(String[] args) throws Exception {
    for(int i = 1;i <= 10;i++){
      Random rand = new Random();
      int hp = rand.nextInt(10);
      boolean isDead = isDead(hp);
      System.out.printf("HP => %d: %sいます\n", hp, isDead ? "生きて" : "死んで");
    }
  }

  public static boolean isDead(int hp){
    if(hp == 0){
      return false;
    }
    else{
      return true;
    }
  }
}
