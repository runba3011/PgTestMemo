import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StringsFixedLengthSplitter {
  public static void main(String[] args) {
    List<String> lines = splitFixedLengthWithLineBreakCodeAndPeriod(
        "1行目。2行目。\n3行目。\n4行目。\n\n5行目。",
        8);

    for (String line : lines) {
      System.out.println(line);
    }
  }

  private static List<String> splitFixedLengthWithLineBreakCodeAndPeriod(String rootStr , int spritInterval) {
    List<String> resultList = new ArrayList<>();
    resultList = spritWithLine(rootStr);
    resultList = spritWithNotLastPeriod(resultList);
    resultList = spritRegularInterval(resultList , spritInterval);

    return resultList;
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

  private static List<String> spritWithNotLastPeriod(List<String> list) {
    for (int i = 0; i < list.size(); i++) {
      String str = list.get(i);
      boolean isLastCharacterPeriod = isLastCharacterPeriod(str);

      List<String> resultList2 = Arrays.asList(str.split("。"));
      int lastIndex = resultList2.size() - 1;

      //最後の要素以外は「。」で区切ったため、各要素の最後の「。」を復元する。
      for(int j = 0;j < resultList2.size()-1;j++){
        resultList2.set(j, resultList2.get(j)+"。");
      }

      //区切る前、最後に「。」が付くことを確認した。ついていたのであれば復元する。
      if (isLastCharacterPeriod) resultList2.set(lastIndex, resultList2.get(lastIndex) + "。");

      list.remove(i);
      for (int j = 0; j < resultList2.size(); j++) {
        list.add(i + j, resultList2.get(j));
      }

    }

    return list;
  }

  private static boolean isLastCharacterPeriod(String str) {
    if (str == "")
      return false;
    return str.charAt(str.length() - 1) == '。';
  }

  private static List<String> spritRegularInterval(List<String> list, int spritInterval) {
    for (int i = 0; i < list.size(); i++) {
      if (list.get(i).length() <= spritInterval)
        continue;

      for (int j = 0; j < (list.get(i).length() / spritInterval) + 1; j++) {
        int targetIndex = i + j;
        if (list.get(targetIndex).length() <= spritInterval)
          continue;
        String nextStr = list.get(targetIndex).substring(0, Math.min(spritInterval, list.get(targetIndex).length()));
        list.add(targetIndex, nextStr);
        if (spritInterval < list.get(targetIndex + 1).length()) {
          deleteAddedString(list, targetIndex + 1, spritInterval);
        }
      }
    }

    return list;
  }

  private static List<String> deleteAddedString(List<String> list , int index , int spritInterval){
    list.set(index , list.get(index).substring(spritInterval , list.get(index).length()));
    return list;
  }
}
