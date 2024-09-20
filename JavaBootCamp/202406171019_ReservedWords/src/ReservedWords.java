public class ReservedWords{
  public static void main(String[] args){
    int thisChanged = 12;
    int that = 23;
    int defaultChanged = 0;

    defaultChanged = thisChanged + that;
    System.out.printf("%d + %d = %d%n" , thisChanged,that , defaultChanged);
  }
}