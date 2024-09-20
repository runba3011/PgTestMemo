public class App {
  public static boolean IS_DEBUG = true;
  public static void main(String[] args) throws Exception {
    if(IS_DEBUG){
      System.out.println("デバッグモードです！");
    }
    BlackJack blackJack = new BlackJack();
    blackJack.start();
  }
}
