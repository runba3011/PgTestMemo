public class MultipleStringConcatter {
  private static String HALF_SPACE = " ";
  public static void main(String[] args){
    String firstName = "一郎";
    String lastName = "山本";
    StringBuilder sb = new StringBuilder();
    sb.append(lastName);
    sb.append(HALF_SPACE);
    sb.append(firstName);

    System.out.println(sb);
  }
}
