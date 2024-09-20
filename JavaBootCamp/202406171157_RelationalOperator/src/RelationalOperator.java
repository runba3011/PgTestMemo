public class RelationalOperator {
  public static void main(String[] args) throws Exception {
    int a = 2;
    int b = 3;
    int c = 5;

    System.out.printf("a = %d , b = %d , c = %d %n%n", a, b, c);

    System.out.println(a != b + c);
    System.out.println(a > c);
    System.out.println(a + b == c);
    System.out.println(a <= b);
    System.out.println(a < b);
    System.out.println(a + b >= c);
  }
}
