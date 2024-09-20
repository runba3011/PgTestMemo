import java.util.Random;
import java.util.Scanner;

public class JankenGame {
  private static final int GU = 0;
  private static final int CHOKI = 1;
  private static final int PA = 2;
  private static final String SIGN_PATTERN_STRING[] = {"グー","チョキ","パー"};

  public static void main(String[] args) {
    System.out.println("じゃんけん勝負");
    System.out.println("グーチョキパーを数字で入力してね");
    System.out.println("0:グー");
    System.out.println("1:チョキ");
    System.out.println("2:パー");
    System.out.println("");
    System.out.print("最初はぐー、じゃんけん：");

    Scanner scan = new Scanner(System.in);
    Random rand = new Random();

    //決着がつくまで繰り返すための無限ループ
    while(true){
      int computerSign = rand.nextInt(SIGN_PATTERN_STRING.length);
      try{
        int playerSign = scan.nextInt();
        System.out.printf("%s(COM)と%s(Player)で…", SIGN_PATTERN_STRING[computerSign],SIGN_PATTERN_STRING[playerSign]);

        //ユーザーが勝利する条件は下記の通り。
        //コンピューターがグー(0)を出した場合：2
        //コンピューターがチョキ(1)を出した場合：0
        //コンピューターがパー(2)を出した場合：1
        //そのため、コンピューターがグー(0)を出した場合のみパー(2)、
        //それ以外はコンピューターの出したものより1少ないものを勝つことができるものとして設定する。
        int winSign = computerSign == 0 ? PA : computerSign - 1;
        if(computerSign == playerSign){
          System.out.println("あいこだよ！");
          System.out.println("");
          System.out.print("あいこで：");
          //決着がついていないためループを行う。そのためbreakしない
        }else if(playerSign == winSign){
          System.out.println("あなたの勝ち");
          break;
        }else{
          System.out.println("あなたの負け");
          break;
        }
        
      }catch(Exception e){
        //ユーザーが数字以外を入力した場合の処理
        System.out.println("数字で入力してね");
      }
    }
  }
}
