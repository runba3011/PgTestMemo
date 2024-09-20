public class FizzBuzz {
  public static void main(String[] args) throws Exception {
    for(int i = 1;i <= 15;i++){
      if (i % 3 == 0 && i % 5 == 0) {
        System.err.println("FizzBuzz");
      }
      else if(i % 3 == 0){
        System.err.println("Fizz");
      }
      else if(i % 5 == 0){
        System.err.println("Buzz");
      }else{
        System.err.printf("%d%n",i);
      }
    }
  }
}
