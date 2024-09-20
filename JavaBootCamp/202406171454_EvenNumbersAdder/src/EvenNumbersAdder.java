public class EvenNumbersAdder {
  public static void main(String[] args) throws Exception {
    int sum = 0;
    for(int i = 1;i <= 10;i++){
      if(i % 2 == 1) continue;
      System.out.printf("i = %2d\n", i);
      sum += i;
    }
    System.out.printf("\nsum = %d \n",sum);
  }
}
