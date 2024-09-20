public class App {
  /**
   * 課題2の「Ranking クラスの使用例」の通り実行する
   */
  public static void main(String[] args) {
    Ranking ranking = new Ranking();
    ranking.entryScore(300, "name300");
    ranking.entryScore(100, "name100");
    ranking.entryScore(400, "name400");
    ranking.entryScore(200, "name200");
    ranking.printRanking();
  }
}
