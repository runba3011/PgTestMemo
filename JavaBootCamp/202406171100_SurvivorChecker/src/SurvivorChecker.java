import java.util.Random;

public class SurvivorChecker {
    public static void main(String[] args) throws Exception {
      for(int i = 0 ; i < 10 ; i++){
        Random r = new Random();
        String myName = "山本一郎";
        boolean isAlive = r.nextBoolean();
        ShowMessage(myName , isAlive);
      }
    }

    private static void ShowMessage(String name , boolean isAlive){
      if(isAlive){
        System.out.printf("%s は 生きているよ.%n",name);
      }
      else{
        System.out.printf("%s は 死んでいるよ.%n",name);
      }
    }
}
