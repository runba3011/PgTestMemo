import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StringsMoreSplitter {
  public static void main(String[] args) {
    List<String> lines = spritWithLineBreakCodeAndPeriod("1行目。2行目。\n3行目。\n4行目。\n\n5行目。");
    for (String line : lines) {
      System.out.println(line);
    }
  }

  private static List<String> spritWithLineBreakCodeAndPeriod(String rootStr) {
    List<String> lineBreakList = new ArrayList<>();
    lineBreakList = spritWithLine(rootStr);
    lineBreakList = spritWithNotLastPeriod(lineBreakList);

    return lineBreakList;
  }

  private static List<String> spritWithLine(String rootStr) {
    // Arrays.asListで作成したリストはその後変更することができないため、別のListに格納しなおす。
    final List<String> lineBreakListFinal = Arrays.asList(rootStr.split("\n"));
    List<String> lineBreakList = new ArrayList<>();
    for (String string : lineBreakListFinal) {
      lineBreakList.add(string);
    }
    return lineBreakList;
  }

  private static List<String> spritWithNotLastPeriod(List<String> lineBreakList) {
    for (int i = 0; i < lineBreakList.size(); i++) {
      String str = lineBreakList.get(i);
      boolean isLastCharacterPeriod = isLastCharacterPeriod(str);

      List<String> resultList2 = Arrays.asList(str.split("。"));
      int lastIndex = resultList2.size() - 1;
      if (isLastCharacterPeriod) resultList2.set(lastIndex, resultList2.get(lastIndex) + "。");

      lineBreakList.remove(i);
      for (int j = 0; j < resultList2.size(); j++) {
        lineBreakList.add(i + j, resultList2.get(j));
      }

    }

    return lineBreakList;
  }

  private static boolean isLastCharacterPeriod(String str) {
    if (str == "")
      return false;
    return str.charAt(str.length() - 1) == '。';
  }
}
