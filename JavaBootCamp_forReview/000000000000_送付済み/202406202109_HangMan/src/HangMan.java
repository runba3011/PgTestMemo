import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

public class HangMan {
  static int ANSWER_MAX = 10;
  static String[] words = {
      "APPLE", "BOOK", "FRIEND", "GARDEN", "HOUSE",
      "FLOWER", "JUMP", "LION", "MOUSE", "ORANGE",
      "PENCIL", "QUEEN", "RABBIT", "SCHOOL", "TREE",
      "OCEAN", "WATCH", "GUITAR", "YELLOW", "BALL",
      "DESK", "FISH", "GOAT", "LAMP", "MONKEY",
      "NOSE", "OCTOPUS", "SNAKE", "TABLE", "VIOLIN"
  };

  public static void main(String[] args) {
    Random rand = new Random();
    String randomWord = words[rand.nextInt(words.length)];
    ArrayList<String> inputtedChars = new ArrayList<String>();
    String currentWordState = ConvertAsterisk(randomWord, inputtedChars);
    System.out.println("英単語を当てよう！");
    System.out.printf("%s\n",currentWordState);

    for (int i = ANSWER_MAX; 0 < i; i--) {
      //動作確認用コード
      //残り回数を表示することで10回繰り返されることを確認した。課題に表示する用記載がないため必要ではないコードである。
      // System.out.printf("残り%d回\n", i);
      System.out.print("文字を入力：");
      AtomicBoolean isSuccess = new AtomicBoolean();
      String inputtedChar = CommonInputManager.inputUppercaseAlphabet(1, isSuccess);
      if(isSuccess.get()){
        inputtedChars.add(inputtedChar);
        System.out.println("");
      }else{
        System.out.println("1文字の大文字のアルファベットで回答してね");
      }
      
      currentWordState = ConvertAsterisk(randomWord, inputtedChars);
      System.out.printf("%s\n",currentWordState);
      if(randomWord.equals(currentWordState)){
        //全ての文字を当てたためループを抜ける。
        System.out.printf("正解！答えは %s でした。",randomWord);
        break;
      }
    }

    if(!randomWord.equals(currentWordState)){
      //全ての文字を当てられなかったため答えを表示する
      System.out.printf("残念！答えは %s でした。",randomWord);
    }

  }

  public static String ConvertAsterisk(String word, ArrayList<String> inputtedChars) {
    String result = "";
    for (int i = 0; i < word.length(); i++) {
      String checkChar = word.substring(i, i + 1);
      if (inputtedChars.contains(checkChar)) {
        //ユーザーが入力した文字に含まれているため文字を表示する
        result += checkChar;
      } else {
        //ユーザーが入力した文字に含まれていないため*を表示する
        result += "*";
      }
    }

    return result;
  }
}
