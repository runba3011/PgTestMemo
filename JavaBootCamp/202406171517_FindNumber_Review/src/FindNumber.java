import java.util.Random;
import java.util.Scanner;

public class FindNumber {
  final static int RANDOM_MAX = 100; // ランダムな数字の最大値 + 1
  final static int ANSWER_MAX_COUNT = 5;

  public static void main(String[] args) {
    Random rand = new Random();
    Scanner scanner = new Scanner(System.in);
    int randomNumber = rand.nextInt(RANDOM_MAX);
    int answerNumber = -1; // ランダムな数字と一致しないよう負の値で初期化する
    int answerCount = 0;
    System.out.printf("数字を当ててみてね。\n答えられるのは%d回までだよ。\n", ANSWER_MAX_COUNT);
    // デバッグ用コード 答えを出力する
    System.out.printf("%d\n", randomNumber);

    // 正解するか回答できる最大回数に達するまで回答を行うためのループ
    while (answerCount < ANSWER_MAX_COUNT) {
      System.out.printf("%d回目:", ++answerCount);
      try {
        answerNumber = scanner.nextInt();
        if (randomNumber == answerNumber) {
          break;
        } else if (randomNumber < answerNumber) {
          System.out.println("もっと小さい数字だよ");
        } else {
          System.out.println("もっと大きい数字だよ");
        }
      } catch (Exception e) {
        // ユーザーが数字以外を入力し、エラーが発生した場合の処理
        System.out.println("数字を入力してね");
      }
    }

    if (randomNumber == answerNumber) {
      System.out.printf("すごい！！%d回で当てられちゃった！", answerCount);
    } else {
      System.out.printf("残念！！ 正解は %d でした！", randomNumber);
    }
  }
}
