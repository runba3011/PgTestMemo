// プリミティブ型の値 12 をオートボクシングするメソッド、ラッパー型の値 12 をアンボクシングするメソッド、 
// その 2 つのメソッドを使って、実行結果のようになる処理を main メソッドに持つ AutoBoxing クラスを作成しなさい

public class AutoBoxing {
  public static void main(String[] args) throws Exception {
    int primitive = 12;
    Integer autoBoxed = autoBoxing(primitive);
    System.out.printf("%d\n" , autoBoxed);
    int unBoxed = unBoxing(autoBoxed);
    System.out.printf("%d\n" , unBoxed);
  }

  public static Integer autoBoxing(int value){
    return Integer.valueOf(value);
  }

  private static int unBoxing(Integer value){
    return value.intValue();
  }
}
