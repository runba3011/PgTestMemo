public class CommaNumber {
  public static void main(String[] args) throws Exception {
    System.out.println(commaSeparatedString(123));
    System.out.println(commaSeparatedString(12345));
    System.out.println(commaSeparatedString(1234567));
    System.out.println(commaSeparatedString(-1234567));
    System.out.println(commaSeparatedString(123456789));
    System.out.println(commaSeparatedString(-123456789));
  }

  private static String commaSeparatedString(int number) {
    StringBuilder result = new StringBuilder();
    String numStr = Integer.toString(Math.abs(number));
    int length = numStr.length();

    for (int i = 0; i < length; i++) {
      result.append(numStr.charAt(i));
      
      int fromRightCharCount = length - i - 1;
      boolean isLastNumber = (length - 1 < i);
      if (fromRightCharCount % 3 == 0 && !isLastNumber) {
        result.append(',');
      }
    }

    if (number < 0) {
      result.insert(0, '-');
    }

    return result.toString();

    // String result = "";
    // int loopCount = 0;
    // int firstNumber = number;
    // while (number != 0) {
    // // int 型を10で割ることで一番下の位を削除することを実現する
    // int firstDigitsNumber0 = number / 10;
    // result = Math.abs((number - firstDigitsNumber0 * 10)) + result;
    // number = firstDigitsNumber0;

    // loopCount++;
    // if (number != 0 && loopCount % 3 == 0)
    // result = "," + result;
    // }

    // if(firstNumber < 0) result = "-"+result;
    // return result;

  }
}
