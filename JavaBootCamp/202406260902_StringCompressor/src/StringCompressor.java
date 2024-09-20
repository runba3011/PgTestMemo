public class StringCompressor {
  public static void main(String[] args){
    String Text = encode("AAAAABBBBBBBBBBCDDDDDDDDDEEFFFFFG");
    System.out.println(Text);
  }

  public static String encode(String str){
    String result = "";
    String beforeStr = getStringAtIndex(str, 0);
    int nowStrSameCount = 0;
    
    //次に見つかった異なる文字までの文字列をもとに結果の文字列を作成している。
    //例：AAAAABB→A5個追加する（Bについてはその後に異なるアルファベットが存在しないため追加しない)
    //そのため、引数に渡された文字列の最後に数字を追加することで、引数に渡されたもの全てを追加できるようにしている。
    //例：AAAAABB1→A5B2を追加する。 引数には数字が渡されない想定のため数字は重複しない。
    str += "1";
    for(int i = 0;i < str.length() ; i++){
      String nextStr = getStringAtIndex(str, i);
      if(nextStr.equals(beforeStr)){
        nowStrSameCount++;
      }else{
        result += nowStrSameCount == 1 ? String.valueOf(beforeStr) : String.valueOf(beforeStr) + nowStrSameCount;
        nowStrSameCount = 1;
        beforeStr = nextStr;
      }
    }
    return result;
  }

  private static String getStringAtIndex(String str , int index){
    return String.valueOf(str.charAt(index));
  }
}
