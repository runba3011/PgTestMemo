import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class App {
  private static final String DESIGN_PATTERN[] = { "ハート", "ダイヤ", "スペード", "クローバー" };

  // 数字だが、1,11,12,13に該当する箇所がアルファベットのため文字列として扱う
  // ユーザーが回答したとき、以下に含まれるかを確認するためArrayListを使用している
  private static final ArrayList<String> NUMBERS = new ArrayList<String>() {
    {
      add("A");
      add("2");
      add("3");
      add("4");
      add("5");
      add("6");
      add("7");
      add("8");
      add("9");
      add("10");
      add("J");
      add("Q");
      add("K");
    }
  };

  public static void main(String[] args) {
    // 図柄と数字を選択する
    Random rand = new Random();
    Scanner scanner = new Scanner(System.in);
    int selectedDesign = rand.nextInt(DESIGN_PATTERN.length);
    String selectedNumber = NUMBERS.get(rand.nextInt(NUMBERS.size()));
    System.out.println("トランプを選んだよ");
    System.out.println("トランプの絵柄を当ててね");
    for (int i = 0; i < DESIGN_PATTERN.length; i++) {
      System.out.printf("%d:%s\n", i, DESIGN_PATTERN[i]);
    }

    // 図柄について問題を出す、正解するまで繰り返す
    while (true) {
      System.out.print("どれだと思う？:");
      try {
        int answerDesign = scanner.nextInt();
        if (answerDesign != selectedDesign) {
          System.out.printf("残念!%sじゃないよ\n", DESIGN_PATTERN[answerDesign]);
        } else {
          // 正解したためループを抜ける
          System.out.printf("正解!図柄は%sだよ\n", DESIGN_PATTERN[selectedDesign]);
          System.out.println("次は数字を当ててね");
          break;
        }
      } catch (Exception e) {
        // 数字以外が入力された際の処理
        System.out.println("数字で回答してね");
        // nextIntで入力した改行は、情報として残っているため再度nextIntを実行したときに
        // 何もせずとも入力が完了したと認識される。それを防ぐためnextLineを実行し、改行を読み込んでおく。
        scanner.nextLine();
      }
    }

    // 数字について問題を出す、正解するまで繰り返す
    while (true) {
      System.out.print("どれだと思う？:");
      String answerNumber = scanner.next();
      if (!NUMBERS.contains(answerNumber)) {
        System.out.println("トランプのA,2,3,...10,J,Q,Kで回答してね");
      } else if (!answerNumber.equals(selectedNumber)) {
        System.out.printf("残念!%sじゃないよ\n", answerNumber);
      } else {
        // 正解したためループを抜ける
        System.out.printf("正解!%sの%sだよ", DESIGN_PATTERN[selectedDesign], selectedNumber);
        break;
      }
    }

    scanner.close();
  }
}
