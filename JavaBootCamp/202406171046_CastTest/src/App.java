public class App {
  public static void main(String[] args) throws Exception {
    double myDouble = 1.9;
    int downToInt = (int)myDouble;
    System.out.printf("myDouble:%s%n",myDouble);
    System.out.printf("downToInt:%s%n",downToInt);
    float upToFloat = downToInt;
    System.out.printf("downToInt:%s%n",downToInt);
    System.out.printf("upToFloat:%s%n",upToFloat);

  }
}
