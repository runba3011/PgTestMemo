import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StringsJaHyphenationSplitter {
  public static void main(String[] args) {
    List<String> lines = splitFixedLengthJaHyphenationWithLineBreakCodeAndPeriod(
        "このプログラムは、句読点を行頭禁則処理するサンプル。¥n"
            + "最後の行です",
        8);

    for (String line : lines) {
      System.out.println(line);
    }
  }

  private static List<String> splitFixedLengthJaHyphenationWithLineBreakCodeAndPeriod(String rootStr,
      int spritInterval) {
    List<String> resultList = new ArrayList<>();
    List<String> hyphenation = new ArrayList();
    hyphenation.add("、");
    hyphenation.add("。");
    resultList = spritWithLine(rootStr);
    resultList = spritWithNotLastPeriod(resultList);
    resultList = spritRegularInterval(resultList, spritInterval);
    resultList = MoveHyphenationAndAdjustCharactorCount(resultList , hyphenation , spritInterval);

    return resultList;
  }

  private static List<String> spritWithLine(String rootStr) {
    // Arrays.asListで作成したリストはその後変更することができないため、別のListに格納しなおす。
    final List<String> lineBreakListFinal = Arrays.asList(rootStr.split("¥n"));
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

      // 最後の要素以外は「。」で区切ったため、各要素の最後の「。」を復元する。
      for (int j = 0; j < resultList2.size() - 1; j++) {
        resultList2.set(j, resultList2.get(j) + "。");
      }

      // 区切る前、最後に「。」が付くことを確認した。ついていたのであれば復元する。
      if (isLastCharacterPeriod)
        resultList2.set(lastIndex, resultList2.get(lastIndex) + "。");

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

  private static List<String> deleteAddedString(List<String> list, int index, int spritInterval) {
    list.set(index, list.get(index).substring(spritInterval, list.get(index).length()));
    return list;
  }

  private static List<String> MoveHyphenationAndAdjustCharactorCount(List<String> list , List<String> hyphenation , int spritInterval){
    while(isContainFirstHyphenation(list , hyphenation)){
      while(isContainFirstHyphenation(list , hyphenation)){
        list = MoveHyphenation(list,hyphenation);
      }

      for(int i = 0;i < list.size();i++){
        if(true){
          list = AdjustCharactorCount(list,hyphenation,spritInterval);
          break;
        }
      }
    }
    return list;
  }

  private static boolean isContainFirstHyphenation(List<String>list , List<String> hyphenation){
    for(String str : list){
      if(hyphenation.contains(String.valueOf(str.charAt(0)))){
        return true;
      }
    }

    return false;
  }

  private static List<String> MoveHyphenation(List<String> list , List<String> hyphenation){
    for(int i = 0;i < list.size();i++){
      String firstStr = String.valueOf(list.get(i).charAt(0));
      if(hyphenation.contains(firstStr)){
        list.set(i - 1 , list.get(i-1) + firstStr);
        list.set(i , list.get(i).substring(1,list.get(i).length()));
      }
    }

    return list;
  }

  private static List<String> AdjustCharactorCount(List<String> list, List<String> hyphenation, int spritInterval) {

    //下記を実装する。
    //句点を移動したことにより1行あたりの文字数が、改行する文字数を下回った場合次の行の1文字目を末尾に追加する。
    //次の行の1文字目を削除する。これにより先頭に句点が来た場合、句点を一つ前の行に移動する。

    //

    return list;
  }
}
