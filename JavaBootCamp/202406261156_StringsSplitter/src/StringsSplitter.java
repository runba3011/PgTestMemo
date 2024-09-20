import java.util.Arrays;
import java.util.List;

public class StringsSplitter {
  public static void main(String[] args) {
    List<String> lines = spritWithLineBreakCode("1行目。\n2行目。\n3行目。\n4行目。\n\n5行目。");
    for(String line : lines){
      System.out.println(line);
    }
  }

  private static List<String> spritWithLineBreakCode(String str){
    return Arrays.asList(str.split("\n"));
  }
}
