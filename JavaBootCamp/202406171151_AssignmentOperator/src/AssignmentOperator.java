public class AssignmentOperator {
  public static void main(String[] args) throws Exception {
    int a = 4;
    int target;
    target = a;
    System.out.printf("target = a: %d%n",target);
    target += a;
    System.out.printf("target += a: %d%n",target);
    target *= a;
    System.out.printf("target *= a: %d%n",target);
  }
}
