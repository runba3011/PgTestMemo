public class ShortCircuit {
  public static void main(String[] args) throws Exception {
    String str = null;
    if(str != null && GetTrue()){
      System.out.println("1つ目のif文の中");
    }

    if(str == null  || GetFalse()){
      System.out.println("2つ目のif文の中");
    }


  }

  public static boolean GetTrue(){
    System.out.println("GetTrue()を実行しました");
    return true;
  }

  public static boolean GetFalse(){
    System.out.println("GetFalse()を実行しました");
    return false;
  }
}
