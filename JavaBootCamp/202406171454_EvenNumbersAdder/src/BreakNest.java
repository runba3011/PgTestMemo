public class BreakNest {
  public static void main(String[] args){
    for(int i = 1;i < 10;i++){
      for(int j = 1;j<10;j++){
        int result = i * j;
        if(20 <result ){
          break;
        }
        System.out.printf("%2d x %2d = %2d",i,j,result);
      }
      System.out.println();
    }
  }
}
