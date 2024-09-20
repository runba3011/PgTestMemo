public class RankingData {
  private int score;
  private String name;

  public RankingData score(int score){
    this.score = score;
    return this;
  }

  public RankingData name(String name){
    this.name = name;
    return this;
  }

  public int getScore() {
    return score;
  }

  public String getName() {
    return name;
  }
}
