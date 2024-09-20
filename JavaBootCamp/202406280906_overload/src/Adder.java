public class Adder {
  public void showResultAdded(int a , int b){
    int array[] = {a , b};
    showResultAdded(array);
  }

  public void showResultAdded(int array[]){

    String str = "";
    int total = 0;
    for(int i = 0 ; i < array.length; i++){
      str += String.format("%d ", array[i]);
      total += array[i];
      if(i != array.length -1){
        str += "+ ";
      }
      else{
        str += "= ";
      }
    }
    str += Integer.toString(total);

    System.out.println(str);
  }
}
