public class MultiDemendionalArray {
  private static int HORIZONTAL_INDEX = 2;
  private static int VERTICAL_INDEX = 1;

  public static void main(String[] args) throws Exception {
    int[][] dimArray = {
      {1,2,3,4},
      {5,6,7,8},
      {9,10,11,12}
    };
    dimArray[HORIZONTAL_INDEX][VERTICAL_INDEX] = 7;
    System.out.printf("水平方向に2、垂直方向に1のインデックスにある値 => %d" , dimArray[HORIZONTAL_INDEX][VERTICAL_INDEX]);
  }
}
