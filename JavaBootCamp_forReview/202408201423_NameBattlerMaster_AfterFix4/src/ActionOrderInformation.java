/**
 * 行動の順番を格納するためのクラス
 * ※使用するときにはArrayList<ActionOrderInformation>とするため何番目かについての値は格納していない
 */
public class ActionOrderInformation {
  private int partyNumber;
  private int playerNumber;

  ActionOrderInformation(int partyNumber , int playerNumber){
    this.partyNumber = partyNumber;
    this.playerNumber = playerNumber;
  }

  public int getPartyNumber() {
    return partyNumber;
  }

  public int getPlayerNumber() {
    return playerNumber;
  }

  
}
