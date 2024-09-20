import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Ranking {
  ArrayList<RankingData> rankingDataList = new ArrayList<RankingData>();

  /**
   * ランキングとして表示するデータのリストにデータを追加する
   * @param score
   * @param name
   */
  public void entryScore(int score , String name){
    this.rankingDataList.add(new RankingData().score(score).name(name));
  }

  /**
   * ランキングデータを表示する
   */
  public void printRanking(){
    //2位以降を表示する際、1回目の処理で1位の値をリストから削除した後、その状態で1位を取得することで実現する。
    //ランキングデータを表示するのみでリセットする目的では使用しないため、コピーを作成しそれを以下で使用する。
    ArrayList<RankingData> rankingDataListCopy = (ArrayList<RankingData>)this.rankingDataList.clone(); 
    int nowRank = 0;
    while(rankingDataListCopy.size() != 0){
      nowRank ++;

      // maximumName について
      // スコアが最大値の要素が何番目かを記憶しているため、for文の中でスコアが最大のデータの名前を記憶しなくとも何番目かより名前を取得可能である。
      // しかし、ランキングデータの最大スコア(maximumScore)については比較の中でfor文の中で記憶しているため、それを表示に使いまわしている。
      // ランキングデータの各要素で処理が違うと混乱を招くため名前(maximumName)についても同様の箇所で記憶しておく。
      int maximumIndex = 0;
      String maximumName = "";
      int maximumScore = Integer.MIN_VALUE;
      for(int i = 0;i < rankingDataListCopy.size() ; i++){
        RankingData nowRankingData = rankingDataListCopy.get(i);
        int nowScore = nowRankingData.getScore();
        if(maximumScore < nowScore){
          maximumName = nowRankingData.getName();
          maximumScore = nowScore;
          maximumIndex = i;
        }
      }
      System.out.printf("%d 位: %s %d\n" , nowRank , maximumName , maximumScore);
      rankingDataListCopy.remove(maximumIndex);
    }
  }
}
