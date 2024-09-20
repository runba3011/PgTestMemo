public class LabeledContinueStatement {
  public static void main(String[] args){
    first:
    for(int i = 1;i < 10;i++){
      for(int j = 1;j<10;j++){
        int result = i * j;
        if( j == 3){
          System.out.print(" @@@@@@ ");
          continue;
        }
        if( i == 7){
          for(int k  = 1;k <= 10;k++){
            System.out.print(" @@@@@@ ");
          }
          System.out.println();
          continue first;
        }
        System.out.printf("%2d x %2d = %2d",i,j,result);
      }
      System.out.println();
    }
  }
}
