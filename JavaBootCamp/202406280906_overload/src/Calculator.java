public class Calculator {
  public static void main(String[] args){
    int a = 1;
    int b = 2;
    int array[] = {1,2,3,4,5};
    Adder adder = new Adder();
    adder.showResultAdded(a , b);
    adder.showResultAdded(array);
  }
}
