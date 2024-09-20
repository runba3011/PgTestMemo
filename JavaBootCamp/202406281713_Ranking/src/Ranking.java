import java.util.ArrayList;
import java.util.List;

public class Ranking {
  List<RankingData> rankingDataList = new ArrayList<RankingData>();

  public void entryScore(int score , String name){
    this.rankingDataList.add(new RankingData().score(score).name(name));
  }

  public void printRanking(){
    List<RankingData> rankingDataListCopy = this.rankingDataList; 
    int loopCount = 0;
    while(rankingDataListCopy.size() != 0){
      loopCount ++;

      // スコアが最大値の要素が何番目かを記憶しているため、for文の中でスコアが最大のデータの名前を記憶しなくとも何番目かより名前を取得可能である。
      // しかし、ランキングデータの最大スコアについては比較の中でfor文の中で記憶しているため、それを表示に使用している。
      // ランキングデータの各要素で処理が違うと混乱を招くため名前についても同様の箇所で記憶しておく。
      int maximumIndex = 0;
      String maximumName = "";
      int maximumScore = Integer.MIN_VALUE;
      for(int i = 0;i < rankingDataListCopy.size() ; i++){
        RankingData nowRankingData = rankingDataListCopy.get(i);
        int nowScore = nowRankingData.getScore();
        if(maximumScore < nowRankingData.getScore()){
          maximumName = nowRankingData.getName();
          maximumScore = nowScore;
          maximumIndex = i;
        }
      }
      System.out.printf("%d 位: %s %d\n" , loopCount , maximumName , maximumScore);
      rankingDataListCopy.remove(maximumIndex);
    }
  }
}
