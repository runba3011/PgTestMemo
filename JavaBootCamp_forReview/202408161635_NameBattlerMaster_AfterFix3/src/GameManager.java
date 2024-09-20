import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

import party.Party;
import player.Fighter;
import player.Priest;
import player.Gojo;
import player.Wizard;
import player.base.Occupation;
import player.base.Player;
import specialEffect.SpecialEffect;
import tactics.AllTactics;
import tactics.base.Tactics;

public class GameManager {
  private final Boolean IS_DEBUG = true;
  private final static ArrayList<String> PLAYER_NAME_FOR_DEBUG = new ArrayList<>(Arrays.asList("野獣", "遠野", "五条悟", "三浦", "木村", "DAISUKE"));
  // private final static ArrayList<String> PLAYER_OCCUPATION_FOR_DEBUG = new ArrayList<>(Arrays.asList("0", "0", "0", "0", "0", "0")); //戦士の動作確認
  // private final static ArrayList<String> PLAYER_OCCUPATION_FOR_DEBUG = new ArrayList<>(Arrays.asList("1", "1", "1", "1", "1", "1")); // 魔法使いの動作確認
  // private final static ArrayList<String> PLAYER_OCCUPATION_FOR_DEBUG = new ArrayList<>(Arrays.asList("2", "2", "2", "2", "2", "2")); //僧侶の動作確認
  // private final static ArrayList<String> PLAYER_OCCUPATION_FOR_DEBUG = new ArrayList<>(Arrays.asList("3","3","3","3","3","3")); //五条悟の動作確認
  private final static ArrayList<String> PLAYER_OCCUPATION_FOR_DEBUG = new ArrayList<>(Arrays.asList("0", "1", "3", "2", "0", "1")); //各職業混合
  private final static String TACTICS_FOR_DEBUG = null; // 戦術のみ自動設定しないようにする場合はnullにすること

  private final static int PARTY_COUNT_MAX = 2;
  private final static int PLAYER_PER_PARTY = 3;
  private final static int MP_HEAL_AMOUNT = 5;

  public void start() {
    ArrayList<Party> partyList = generateParties();
    // ArrayList<Player> playerList = generatePlayers();
    showCharactorInformation(partyList);
    waitEnter();
    startBattle();
    // 素早さ順に行動させる必要があるため、リスト内で素早さ順で並び替える。
    // ただし、元のリスト(partyList)に対して行うと、各パーティーでプレイヤー1、プレイヤー2が逆転してしまう場合がある。
    // そのため上記を複製し、それに対して並び替え及び攻撃処理を行う。また、攻撃順はパーティー関係なく素早さ順のため、Playerのリストに格納する。
    ArrayList<Player> playerListCopy = new ArrayList<>();
    partyList.forEach(party -> party.getMembers().forEach(player -> playerListCopy.add(player)));
    sortPlayersByAgi(playerListCopy);
    BATTLE_LOOP: while (true) {
      for (int i = 0; i < playerListCopy.size(); i++) {
        Player attacker = playerListCopy.get(i);
        if (attacker.isDead()) {
          continue;
        }

        SpecialEffect cantMoveSpecialEffect = new SpecialEffect();
        if (attacker.isCantMoveBySpecialEffects(cantMoveSpecialEffect)) {
          printCantMoveMessage(attacker , cantMoveSpecialEffect);
          continue;
        }

        Party attackerParty = getPartyPlayerAffiliate(partyList, attacker);
        Party attackedParty = partyList.get(Math.abs(partyList.indexOf(attackerParty) - 1));
        Player attacked = selectAttackedPlayer(attackerParty, attackedParty);
        if (attacked == null) {
          // 攻撃側でないパーティーのプレイヤーのすべての体力が0以下の状態で攻撃処理が行われている。
          // 想定していない処理のため処理を終了する。
          // 上記の旨のメッセージはselectAttackedPlayer内で表示しているためここで表示する必要はない。
          return;
        }
        attacker.attack(attacked, attackerParty.getTactics());
        if (attacked.isDead()) {
          System.out.printf("%sは力尽きた…\n", attacked.getName());

          if (attackedParty.getPartyDeadCount() == PLAYER_PER_PARTY) {
            printAnnihilationMessage(attackedParty);
            printWinMessage(attackerParty);
            break BATTLE_LOOP;
          }
        }
      }

      giveSpecialEffectDamage(partyList);
      decrementSpecialEffectTurns(partyList);
      destroyFinishedSpecialEffect(partyList);
      healAllPlayerMp(partyList);
      nextTurn();
      showNowCharactorInformation(partyList);
      waitEnter();
    }
  }

  /**
   * Enterが押されるまで待機する
   */
  private static void waitEnter() {
    // 入力を待機することで、Enterが入力されるまで待つ
    System.out.print("待機中…(Enterを押してください)");
    App.scanner.nextLine();
    System.out.println("");
  }

  private ArrayList<Party> generateParties() {
    // 下記の処理の流れとする。
    // partyListに生成するパーティー数だけパーティーを追加する
    // 各パーティーに、パーティーの人数分だけプレイヤーを追加する
    // 戦術を設定する
    ArrayList<Party> partyList = new ArrayList<>();
    for (int i = 0; i < PARTY_COUNT_MAX; i++) {
      partyList.add(new Party());
    }

    // パーティー0、プレイヤー0とならないよう、繰り返しは1始まりとする。
    for (int partyNumber = 1; partyNumber <= partyList.size(); partyNumber++) {
      Party party = partyList.get(partyNumber - 1);
      for (int playerNumber = 1; playerNumber <= PLAYER_PER_PARTY; playerNumber++) {
        party.appendPlayer(generatePlayer(partyNumber, playerNumber));
      }

      System.out.print("戦術を選択してください:");
      printAllTactics();
      party.setTactics(selectTactics());
    }

    return partyList;
  }

  /**
   * 名前と職業を入力させ、その情報をもとにキャラクターを2体生成する
   * 
   * @return 生成したキャラクターのリスト
   */
  private Player generatePlayer(int partyNumber, int playerNumber) {
    Player player = null;
    System.out.printf("パーティ %d の プレイヤー %d の名前を入力してください:", partyNumber, playerNumber);
    String debugName = PLAYER_NAME_FOR_DEBUG.get((partyNumber - 1) * PLAYER_PER_PARTY + (playerNumber - 1));
    String name = IS_DEBUG ? debugName : inputCharactoName();
    if (IS_DEBUG) {
      System.out.println(debugName);
    }
    System.out.print("職業を入力してください:");
    printAllOccupation();

    // 職業を入力する際は数字を使用するが、inputCharactorOccupationにて
    // nextIntなどを使用しようとするとこの関数内で数字を入力されたことについてのチェック処理が必要となり、
    // 1つの関数で1つの役割とはならなくなってしまう。
    // そのため、文字列型で受け取ることで必ずnextLineで対応できるようにし、それが各職業の選択肢にあてはまるかについてisInputtedOccupationでチェックを行う。
    String debugOccupation = IS_DEBUG ? PLAYER_OCCUPATION_FOR_DEBUG.get((partyNumber - 1) * PLAYER_PER_PARTY + (playerNumber - 1)) : null;
    String inputtedOccupationStr = IS_DEBUG ? debugOccupation : inputCharactorOccupation();
    if (IS_DEBUG) {
      System.out.println(debugOccupation);
    }
    while (!isInputtedOccupation(inputtedOccupationStr)) {
      System.err.println("数字で表示されているいずれかの数字で職業を入力してください");
      inputtedOccupationStr = inputCharactorOccupation();
    }

    int inputtedOccupation = Integer.valueOf(inputtedOccupationStr);
    switch (convertintToOccupation(inputtedOccupation)) {
      case FIGHTER:
        player = new Fighter(name);
        break;
      case WIZARD:
        player = new Wizard(name);
        break;
      case PRIEST:
        player = new Priest(name);
        break;
      case GOJO:
        player = new Gojo(name);
        break;
      default:
        System.out.println("キャラクターの作成で想定外のエラーが発生しました。");
        break;
    }
    return player;
  }

  private Tactics selectTactics() {
    Boolean isTacticsDebug = IS_DEBUG && TACTICS_FOR_DEBUG != null;
    String inputtedOccupationStr = isTacticsDebug ? TACTICS_FOR_DEBUG : inputTactics();
    if (isTacticsDebug) {
      System.out.println(TACTICS_FOR_DEBUG);
    }
    while (!isInputtedTactics(inputtedOccupationStr)) {
      System.err.println("数字で表示されているいずれかの数字で戦術を入力してください");
      inputtedOccupationStr = inputTactics();
    }

    int inputtedTactics = Integer.valueOf(inputtedOccupationStr);
    return convertintToTactics(inputtedTactics);
  }

  private void printAllOccupation() {
    for (Occupation occupation : Occupation.values()) {
      System.out.printf("%s : %d  ", occupation.getName(), occupation.ordinal());
    }
  }

  private void printAllTactics() {
    for (int i = 0; i < AllTactics.tacticsList.size(); i++) {
      Tactics tactics = AllTactics.tacticsList.get(i);
      System.out.print(String.format("   %d:%s", i, tactics.getName()));
    }
  }

  /**
   * ユーザーにキャラクター名を入力させる
   * 
   * @return ユーザーが入力したキャラクター名
   */
  private String inputCharactoName() {
    return App.scanner.nextLine();
  }

  /**
   * ユーザーにプレイヤーの職業を入力させる
   */
  private String inputCharactorOccupation() {
    return App.scanner.nextLine();
  }

  /**
   * ユーザーが入力したプレイヤーの職業がいずれかの職業の選択肢に当てはまるかを確認する
   * 
   * @return
   */
  private Boolean isInputtedOccupation(String inputtedOccupationStr) {
    int inputtedOccupation = 0;
    try {
      inputtedOccupation = Integer.valueOf(inputtedOccupationStr);
    } catch (NumberFormatException e) {
      // 数字で入力されなかった場合の処理
      return false;
    } catch (Exception e) {
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

  private static Occupation convertintToOccupation(int occupationIndex) {
    for (Occupation occupation : Occupation.values()) {
      if (occupationIndex == occupation.ordinal()) {
        return occupation;
      }
    }

    System.err.println("想定外の処理が行われました");
    return Occupation.FIGHTER;
  }

  /**
   * ユーザーにプレイヤーの職業を入力させる
   */
  private String inputTactics() {
    return App.scanner.nextLine();
  }

  private Boolean isInputtedTactics(String inputtedTacticsStr) {
    int inputtedTactics = 0;
    try {
      inputtedTactics = Integer.valueOf(inputtedTacticsStr);
    } catch (NumberFormatException e) {
      // 数字で入力されなかった場合の処理
      return false;
    } catch (Exception e) {
      System.out.println("想定外の例外が発生しました。");
      return false;
    }

    for (int i = 0; i < AllTactics.tacticsList.size(); i++) {
      if (i == inputtedTactics) {
        // いずれかの戦術と一致した
        return true;
      }
    }

    // いずれの戦術とも一致しなかった
    return false;
  }

  private Tactics convertintToTactics(int tacticsIndex) {
    for (int i = 0; i < AllTactics.tacticsList.size(); i++) {
      if (i == tacticsIndex) {
        // いずれかの戦術と一致した
        return AllTactics.tacticsList.get(i);
      }
    }

    System.err.println("想定外の処理が行われました");
    return AllTactics.tacticsList.get(0);
  }

  /**
   * 全キャラクターの情報を表示する
   * 
   * @param partyList 全キャラクターのリスト
   */
  private void showCharactorInformation(ArrayList<Party> partyList) {
    System.out.println("-------キャラクター情報-------");
    // パーティー0、プレイヤー0とならないよう、繰り返しは1始まりとする。
    for (int partyNumber = 1; partyNumber <= partyList.size(); partyNumber++) {
      Party party = partyList.get(partyNumber - 1);
      System.out.print(String.format("パーティー%d:", partyNumber));
      System.out.println(String.format("戦術：%s", party.getTactics().getName()));
      for (int playerNumber = 1; playerNumber <= party.getMembers().size(); playerNumber++) {
        System.out.println(String.format("プレイヤー%d:", playerNumber));
        Player player = party.getMembers().get(playerNumber - 1);
        printAbilityInformation(player);
      }
      System.out.println();
    }
  }

  /**
   * キャラクターの情報を表示する
   * 
   * @param player
   */
  private void printAbilityInformation(Player player) {
    System.out.println(String.format("%s : 職業:%s HP:%d MP:%d 攻撃力:%d 防御力:%d 運:%d 素早さ:%d", player.getName(),
        player.getOccupation().getName(), player.getHp(), player.getMp(), player.getStr(), player.getDef(),
        player.getLuck(), player.getAgi()));
  }

  /**
   * プレイヤーを素早さ順に並び替える
   * 
   * @param playerList
   * @return
   */
  private void sortPlayersByAgi(ArrayList<Player> playerList) {
    playerList.sort(Comparator.comparingInt(Player::getAgi).reversed());
  }

  /**
   * バトル開始時のテキストを表示する
   */
  private void startBattle() {
    System.out.println();
    System.out.println("=== バトル開始 ===");
  }

  /**
   * プレイヤーが状態異常で動けない場合に、その理由を表示する
   * 
   * @param attacker
   * @param cantMoveSpecialEffect
   */
  private void printCantMoveMessage(Player attacker, SpecialEffect cantMoveSpecialEffect) {
    System.out
        .println(String.format(cantMoveSpecialEffect.getSpecialEffectNum().getCantMoveMessage(), attacker.getName()));
  }

  private Player selectAttackedPlayer(Party attackerParty, Party attackedParty) {
    Player selected = attackerParty.getTactics().selectAttackedPlayer(attackedParty);
    return selected;
  }

  /**
   * 特定のプレイヤーが所属しているパーティーを取得する
   * 
   * @param partyList 全てのパーティーのリスト
   * @param player    特定のプレイヤー
   * @return 特定のプレイヤーが所属しているパーティー
   */
  private Party getPartyPlayerAffiliate(ArrayList<Party> partyList, Player player) {
    for (Party party : partyList) {
      if (party.getMembers().contains(player)) {
        return party;
      }
    }

    // 以下は特定のプレイヤーが所属しているパーティーが見つからなかった場合である。
    // すべてのプレイヤーは必ずパーティーに所属させるため想定外の処理である。
    System.out.println("想定外の処理が発生しました");
    return null;
  }

  /**
   * 状態異常によるダメージを全てのプレイヤーに与える。
   */
  private void giveSpecialEffectDamage(ArrayList<Party> partyList) {
    partyList.forEach(party -> party.getMembers().forEach(player -> {if(!player.isDead()){player.receiveSpecialEffectDamage();}}));
  }

  private void printAnnihilationMessage(Party party) {
    String message = "";
    for (Player player : party.getMembers()) {
      message += message.equals("") ? player.getName() : "、" + player.getName();
    }
    message += "は全滅した！";
    System.out.println(message);
  }

  private void printWinMessage(Party party) {
    String message = "";
    for (Player player : party.getMembers()) {
      message += message.equals("") ? player.getName() : "、" + player.getName();
    }
    message += "の勝利！";
    System.out.println(message);
  }

  private void decrementSpecialEffectTurns(ArrayList<Party> partyList) {
    partyList.forEach(party -> party.getMembers().forEach(
        player -> player.getSpecialEffectList().forEach(effect -> effect.setLastTurn((effect.getLastTurn() - 1)))));
  }

  private void destroyFinishedSpecialEffect(ArrayList<Party> partyList) {
    partyList.forEach(party -> party.getMembers().forEach(player -> {
      ArrayList<SpecialEffect> effectList = player.getSpecialEffectList();
      for (int i = 0; i < effectList.size(); i++) {
        SpecialEffect effect = effectList.get(i);
        if (effect.getLastTurn() == 0) {
          effectList.remove(effect);
          // 削除した数だけ要素の数がずれるためそれに対応する。
          i--;
        }
      }
    }));
  }

  /**
   * ターン終了時、全てのプレイヤーのMPを一定量回復する
   * 
   * @param partyList
   */
  private void healAllPlayerMp(ArrayList<Party> partyList) {
    partyList.forEach(party -> party.getMembers().forEach(player -> {
      if (!player.isDead()) {
        player.setNowMp(player.getNowMp() + MP_HEAL_AMOUNT);
      }
    }));
  }

  private void nextTurn() {
    System.out.println("");
    System.out.println("- 次のターン -");
  }

  private void showNowCharactorInformation(ArrayList<Party> partyList) {

    for (int partyNumber = 0; partyNumber < partyList.size(); partyNumber++) {
      System.out.println(String.format("パーティー%d", partyNumber + 1));
      Party party = partyList.get(partyNumber);
      ArrayList<Player> playerList = party.getMembers();
      for (int playerNumber = 0; playerNumber < playerList.size(); playerNumber++) {
        Player player = playerList.get(playerNumber);
        System.out.printf("プレイヤー %d: %s(%s)(HP %d / %d  MP %d / %d) \n", playerNumber + 1, player.getName(),
            player.getOccupation().getName(),
            player.getNowHp(),
            player.getHp(), player.getNowMp(), player.getMp());

        if (player.getNowHp() <= 0) {
          // 体力が0以下の場合は既に力尽きているため状態異常についての処理は不要。
          continue;
        }
        System.err.println("状態異常：");
        ArrayList<SpecialEffect> effectList = player.getSpecialEffectList();
        if (effectList.size() == 0) {
          System.out.println("なし");
          continue;
        }
        for (SpecialEffect effect : player.getSpecialEffectList()) {
          System.out
              .println(String.format("%s 残り%dターン", effect.getSpecialEffectNum().toString(), effect.getLastTurn()));
        }
      }
      System.out.println();
    }

    System.out.println("");
    System.out.println("---");
  }
}
