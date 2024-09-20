import java.util.Random;

public class BattleField {
  private static final int MAX_TURN_NO = 5;

  public static void main(String[] args) {
    String[] players = { "山本", "浜田", };

    Start();
    AttackTurn(players);
    Start();
  }

  public static void Start() {
    System.out.println("-----");
    System.out.println("処理をスタートします");
    System.out.println("-----");
  }

  public static void AttackTurn(String[] players) {
    Random rand = new Random();
    for (int i = 1; i <= MAX_TURN_NO; i++) {
        int randomIndex = rand.nextInt(players.length);
        String attackerName = players[randomIndex];

        String defenderName
            = players[Math.abs(randomIndex - 1)];

        System.out.printf("ターン %d: %n", i);
        System.out.printf(
            "%s が %s を攻撃した. %n",
            attackerName, defenderName
        );
        System.out.println();
    }

  }
}