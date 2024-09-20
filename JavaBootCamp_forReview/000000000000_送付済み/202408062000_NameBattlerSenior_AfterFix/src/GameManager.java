import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

import player.Fighter;
import player.Priest;
import player.Gojo;
import player.Wizard;
import player.base.Occupation;
import player.base.Player;
import specialEffect.SpecialEffect;

public class GameManager {
  private final static int CHARACTOR_COUNT_MAX = 2;

  /**
   * ネームバトラーを開始する
   */
  public void start(){
    ArrayList<Player> playerList = generatePlayers();
    showCharactorInformation(playerList);
    waitEnter();
    startBattle();
    // 素早さ順に行動させる必要があるため、リスト内で素早さ順で並び替える。
    // ただし、元のリスト(playerList)に対して行うと、プレイヤー1、プレイヤー2が逆転してしまう場合がある。
    // そのため上記を複製し、それに対して並び替え及び攻撃処理を行う。
    // PlayerListは必ずArrayList<Player>のため警告は無視する。
    @SuppressWarnings("unchecked")
    ArrayList<Player> playerListCopy = (ArrayList<Player>) playerList.clone();
    sortPlayersByAgi(playerListCopy);
    BATTLE_LOOP: while (true) {
      for (int i = 0; i < playerListCopy.size(); i++) {
        Player attacker = playerListCopy.get(i);
        SpecialEffect cantMoveSpecialEffect = new SpecialEffect();
        if (attacker.isCantMoveBySpecialEffects(cantMoveSpecialEffect)) {
          printCantMoveMessage(attacker , cantMoveSpecialEffect);
          continue;
        }

        Player attacked = playerListCopy.get(Math.abs(i - 1));
        attacker.attack(attacked);
        if (attacked.isDead()) {
          System.out.printf("%sは力尽きた…\n", attacked.getName());
          System.out.printf("%sの勝利！\n", attacker.getName());
          break BATTLE_LOOP;
        }
      }

      giveSpecialEffectDamage(playerList);
      decrementSpecialEffectTurns(playerList);
      destroyFinishedSpecialEffect(playerList);
      nextTurn();
      showNowCharactorInformation(playerList);
      waitEnter();
    }
  }

  /**
   * Enterが押されるまで待機する
   */
  public static void waitEnter() {
    // 入力を待機することで、Enterが入力されるまで待つ
    System.out.print("待機中…(Enterを押してください)");
    App.scanner.nextLine();
    System.out.println("");
  }

  /**
   * 名前と職業を入力させ、その情報をもとにキャラクターを2体生成する
   * 
   * @return 生成したキャラクターのリスト
   */
  public static ArrayList<Player> generatePlayers() {
    ArrayList<Player> playerList = new ArrayList<Player>();
    for (int i = 1; i <= CHARACTOR_COUNT_MAX; i++) {
      System.out.printf("プレイヤー %d の名前を入力してください:", i);
      String name = inputCharactoName();

      System.out.printf("職業を入力してください:", i);
      for (Occupation occupation : Occupation.values()) {
        System.out.printf("%s : %d  ", occupation.getName(), occupation.ordinal());
      }

      int inputtedOccupation= 0;
      //職業を入力する際は数字を使用するが、inputCharactorOccupationにて
      //nextIntなどを使用しようとするとこの関数内で数字を入力されたことについてのチェック処理が必要となり、
      //1つの関数で1つの役割とはならなくなってしまう。
      //そのため、文字列型で受け取ることで必ずnextLineで対応できるようにし、それが各職業の選択肢にあてはまるかについてisInputtedOccupationでチェックを行う。
      String inputtedOccupationStr = inputCharactorOccupation();
      while(!isInputtedOccupation(inputtedOccupationStr)){
        System.err.println("数字で表示されているいずれかの数字で職業を入力してください");
        inputtedOccupationStr = inputCharactorOccupation();
      }

      inputtedOccupation = Integer.valueOf(inputtedOccupationStr);
      switch (convertintToOccupation(inputtedOccupation)) {
        case FIGHTER:
          playerList.add(new Fighter(name));
          break;
        case WIZARD:
          playerList.add(new Wizard(name));
          break;
        case PRIEST:
          playerList.add(new Priest(name));
          break;
        case GOJO:
          playerList.add(new Gojo(name));
          break;

        default:
          System.out.println("キャラクターの作成で想定外のエラーが発生しました。");
          break;
      }
    }
    return playerList;
  }

  /**
   * ユーザーにキャラクター名を入力させる
   * 
   * @return ユーザーが入力したキャラクター名
   */
  public static String inputCharactoName() {
    return App.scanner.nextLine();
  }

  /**
   * ユーザーにプレイヤーの職業を入力させる
   */
  public static String inputCharactorOccupation(){
    return App.scanner.nextLine();
  }

  /**
   * ユーザーが入力したプレイヤーの職業がいずれかの職業の選択肢に当てはまるかを確認する
   * @return
   */
  public static Boolean isInputtedOccupation(String inputtedOccupationStr){
    int inputtedOccupation = 0;
    try{
      inputtedOccupation = Integer.valueOf(inputtedOccupationStr);
    }
    catch(NumberFormatException e){
      //数字で入力されなかった場合の処理
      return false;
    }catch(Exception e){
      System.out.println("想定外の例外が発生しました。");
      return false;
    }

    //anyMatch で使用できるようにするためにfinalの変数に格納してから使用する
    final int finalInputtedOccupation = inputtedOccupation;
    if(Arrays.stream(Occupation.values()).anyMatch(occupation -> occupation.ordinal() == finalInputtedOccupation)){
      return true;
    }
    else{
      return false;
    }
  }

  public static Occupation convertintToOccupation(int occupationIndex) {
    for (Occupation occupation : Occupation.values()) {
      if (occupationIndex ==occupation.ordinal()) {
        return occupation;
      }
    }

    System.err.println("想定外の処理が行われました");
    return Occupation.FIGHTER;
  }

  /**
   * 全キャラクターの情報を表示する
   * 
   * @param playerList 全キャラクターのリスト
   */
  public static void showCharactorInformation(ArrayList<Player> playerList) {
    System.out.println("-------キャラクター情報-------");
    for (int i = 0; i < playerList.size(); i++) {
      Player player = playerList.get(i);
      printAbilityInformation(player);
    }
  }
  public static void printAbilityInformation(Player player) {
    System.out.println(String.format("%s : 職業:%s HP:%d MP:%d 攻撃力:%d 防御力:%d 運:%d 素早さ:%d", player.getName(),
    player.getOccupation().getName(), player.getHp(), player.getMp(), player.getStr(), player.getDef(), player.getLuck(), player.getAgi()));
  }

  /**
   * プレイヤーを素早さ順に並び替える
   * 
   * @param playerList
   * @return
   */
  public static ArrayList<Player> sortPlayersByAgi(ArrayList<Player> playerList) {
    playerList.sort(Comparator.comparingInt(Player::getAgi).reversed());
    return playerList;
  }

  /**
   * バトル開始時のテキストを表示する
   */
  public static void startBattle() {
    System.out.println();
    System.out.println("=== バトル開始 ===");
  }

  /**
   * プレイヤーが状態異常で動けない場合に、その理由を表示する
   * @param attacker
   * @param cantMoveSpecialEffect
   */
  private static void printCantMoveMessage(Player attacker , SpecialEffect cantMoveSpecialEffect){
    System.out.println(String.format(cantMoveSpecialEffect.getSpecialEffectNum().getCantMoveMessage(), attacker.getName()));
  }

  /**
   * 状態異常によるダメージを全てのプレイヤーに与える。
   */
  public static void giveSpecialEffectDamage(ArrayList<Player> playerList) {
    playerList.forEach(player -> player.receiveSpecialEffectDamage());
  }

  public static void decrementSpecialEffectTurns(ArrayList<Player> playerList) {
    playerList.forEach(player -> player.getSpecialEffectList().forEach(specialEffect -> specialEffect.setLastTurn(specialEffect.getLastTurn() - 1)));
  }

  public static void destroyFinishedSpecialEffect(ArrayList<Player> playerList) {
    //ループの中で状態異常のリストから値が削除されないためforEachが使えない
    for (Player player : playerList) {
      ArrayList<SpecialEffect> effectList = player.getSpecialEffectList();
      for (int i = 0; i < effectList.size(); i++) {
        SpecialEffect effect = effectList.get(i);
        if (effect.getLastTurn() == 0) {
          // 残りターン数が0のため削除する。
          // 削除した数だけ要素の数がずれるためそれに対応する。
          effectList.remove(effect);
          i--;
        }
      }
    }
  }

  public static void nextTurn() {
    System.out.println("");
    System.out.println("- 次のターン -");
  }

  public static void showNowCharactorInformation(ArrayList<Player> playerList) {
    for (int i = 0; i < playerList.size(); i++) {
      Player player = playerList.get(i);
      System.out.printf("プレイヤー %d: %s(HP %d / %d  MP %d / %d) \n", i + 1, player.getName(), player.getNowHp(),
          player.getHp(), player.getNowMp(), player.getMp());

      System.err.println("状態異常：");
      ArrayList<SpecialEffect> effectList = player.getSpecialEffectList();
      if (effectList.size() == 0) {
        System.out.println("なし");
        continue;
      }
      for (SpecialEffect effect : player.getSpecialEffectList()) {
        System.out.println(String.format("%s 残り%dターン", effect.getSpecialEffectNum().toString(), effect.getLastTurn()));
      }
    }
    System.out.println("");
    System.out.println("---");
  }
}
