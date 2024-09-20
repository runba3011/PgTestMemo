public class AutoUpcasting {
  public static void main(String[] args){
    char myChar = 'a';
    int ancii = myChar;
    System.out.printf("cast -> int への自動キャスト%n");
    System.out.printf("%s -> %s%n%n",myChar , ancii);
    int castAncii = (int)myChar;
    System.out.printf("cast -> int へのアップキャスト%n");
    System.out.printf("%s -> %s",myChar , castAncii);


  }
}
