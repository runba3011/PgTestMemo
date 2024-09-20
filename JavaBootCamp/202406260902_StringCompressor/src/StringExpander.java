public class StringExpander {
  public static void main(String[] args){
    String Text = decode("A5B10CD9E2F5G");
    System.out.println(Text);
  }

  public static String decode(String str){
    String result = "";
    String beforeStr = getStringAtIndex(str , 0);
    int nowStrSameCount = 0;
    
    String alphabet = "";
    String sameCountString = "";

    //次に見つかったアルファベットまでの文字列をもとに結果の文字列を作成している。
    //例：A5B10→Aを5個追加する（Bについてはその後にアルファベットが存在しないため追加しない)
    //そのため、引数に渡された文字列の最後にアルファベットを追加することで、引数に渡されたもの全てを追加できるようにしている。
    //例：A5B10A→Aを5個、Bを10個追加する（最後のAについては上記と同じ理由より追加されない）
    str += "A";
    for(int i = 0;i < str.length() ; i++){
      String nextStr = getStringAtIndex(str , i);
      if(isAlphabet(nextStr)){
        if (nextStr != beforeStr && alphabet != "") {
          result += createAlphabets(alphabet, sameCountString);
          alphabet = "";
          sameCountString = "";
        }
        alphabet = nextStr;
      }else{
        sameCountString += nextStr;
      }
    }
    return result;
  }

  private static String getStringAtIndex(String str , int index){
    return String.valueOf(str.charAt(index));
  }

  private static boolean isAlphabet(String str){
    return str.matches("[A-Z]+");
  }

  private static String createAlphabets(String alphabet , String sameCountString){
    String result = "";
    if(sameCountString == ""){
      sameCountString = "1";
    }
    for(int j = 0; j < Integer.parseInt(sameCountString);j++){
      result += alphabet;
    }
    return result;
  }
}
