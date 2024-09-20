public class StringCutOut {
  private static int START_POINT = 2;
  private static int END_POINT = 6;
  public static void main(String[] args){
    String targetString = "investment";
    String resultString = targetString.substring(START_POINT,END_POINT);
    System.out.println(resultString);
  }
}
