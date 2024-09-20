package actionNum;

public enum ActionNum {
  APPEAL_FOR_PEACE,
  NORMAL_ATTACK,
  MAGIC_ATTACK,
  MAGIC_HEAL,
  SPECIAL_EFFECT_MAGIC;
  
  public static ActionNum convertintToActionNum(int actionInt){
    for(ActionNum actionNum : ActionNum.values()){
      if(actionInt == actionNum.ordinal()){
        return actionNum;
      }
    }

    //以下は引数に渡された値がどの値とも一致しなかった場合である。
    //この関数が呼ばれる場合の処理の流れとして、番号を生成する→enumのどの値と一致するかを確認する→プレイヤーが行動する を
    //想定しているため、返す値が引数の値の通りでなくとも動作はする。
    //メッセージだけ表示し、処理を続行する。
    System.out.println("数値→行動 の変換にて想定外の処理が発生しました");
    return APPEAL_FOR_PEACE;
  }
}
