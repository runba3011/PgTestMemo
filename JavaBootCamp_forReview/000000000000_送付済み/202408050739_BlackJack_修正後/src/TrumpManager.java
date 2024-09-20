import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * トランプを管理するためのクラス
 * ※残り枚数が0の状態でドローしようとしたときは例外を返す。そのため、このクラスを使う範囲はすべてtryで囲うこと。
 */
public class TrumpManager {
  private List<Trump> lastTrumps = new ArrayList<>();
  /**
   * トランプをリセット（各種のマークのトランプが13枚格納された状態に）する
   */
  public void reset() {
    lastTrumps = new ArrayList<>();
    for (Trump.Mark mark : Trump.Mark.values()) {
      for (int number = Trump.NUMBER_MIN; number <= Trump.NUMBER_MAX; number++) {
        lastTrumps.add(new Trump(mark, number));
      }
    }
  }

  /**
   * カードのシャッフルを行う
   */
  public void shuffle() {
    Collections.shuffle(lastTrumps);
  }

  public boolean isAbleDraw(){
    return 1 <= lastTrumps.size();
  }

  /**
   * カードのドローを行う<p>
   * isAbleDrawを実行し、ドローが可能であることを確認してから実行すること。
   * @return
   * @throws OutOfCardsException 
   * カードが0枚になった後にドローしようとした場合に<p>
   * ゲームを中断するようにするためのエラー。<p>
   * 
   * ※カードが存在しない null ではなくエラーを返すようにしたのは<p>
   * この関数はいろいろな箇所で実行しており、nullが返されたらゲームを中断する<p>
   * というようにするとnullが返されたことを複数回確認しなければならないため。<p>
   * エラーを返すことでtry-catch及びthrowsのみでゲームの中断が可能。
   * isAbleDrawを実行し忘れた状態でも対応できるようにするためのエラー。
   */
  public Trump draw() throws OutOfCardsException {
    if(isAbleDraw()){
      Trump trump = lastTrumps.remove(0);
      if(lastTrumps.size() <= 0){
        System.out.println("このドローでカードが0枚になります!");
      } 
      return trump;
    }
    else{
      //カードが0枚の状態でドローされようとした。エラーを返す。
      throw new OutOfCardsException(); 
    }
  }
}
