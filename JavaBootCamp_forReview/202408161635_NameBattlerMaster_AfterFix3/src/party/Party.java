package party;
import java.util.ArrayList;

import player.base.Player;
import tactics.base.Tactics;

public class Party {

  private ArrayList<Player> members;
  private Tactics tactics;

  /**
   * パーティーのコンストラクタ
   */
  public Party() {
    members = new ArrayList<>();
  }

  /**
   * このパーティーのプレイヤーのリストを取得する
   * @return
   */
  public ArrayList<Player> getMembers() {
    return members;
  }

  public ArrayList<Player> getAliveMembers() {
    ArrayList<Player> playerList = new ArrayList<>();
    for (Player player : this.getMembers()) {
      if (!player.isDead()) {
        playerList.add(player);
      }
    }
    return playerList;
  }

  /**
   * パーティーにプレイヤーを追加する
   * 
   * @param player 追加するプレイヤー
   */
  public void appendPlayer(Player player) {
    members.add(player);
  }

  //現在使用していないためコメントアウトしている。
  // /**
  //  * パーティーからプレイヤーを離脱させる
  //  * 
  //  * @param player 離脱させるプレイヤー
  //  */
  // public void removePlayer(Player player) {
  //   members.remove(player);
  // }

  public Tactics getTactics() {
    return tactics;
  }

  public void setTactics(Tactics tactics) {
    this.tactics = tactics;
  }


  public int getPartyDeadCount() {
    int deadCount = 0;
    for (Player player : this.getMembers()) {
      if (player.isDead()) {
        deadCount++;
      }
    }
    return deadCount;
  }
}