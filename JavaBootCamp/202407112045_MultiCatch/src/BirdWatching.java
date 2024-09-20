import java.io.PrintWriter;
import java.io.StringWriter;


public class BirdWatching {

  public static void main(String[] args) {

    boolean isNeedToValidPigeon = true;
    boolean isNeedToConvertToPigeon = false;

    BirdWatching app = new BirdWatching();
    app.execute(
        isNeedToValidPigeon,
        isNeedToConvertToPigeon);
    System.out.println();

    isNeedToValidPigeon = false;
    isNeedToConvertToPigeon = true;
    app.execute(
        isNeedToValidPigeon,
        isNeedToConvertToPigeon);
    System.out.println();

    isNeedToConvertToPigeon = false;
    app.execute(
        isNeedToValidPigeon,
        isNeedToConvertToPigeon);
  }

  private void execute(
      boolean isNeedToValidPigeon,
      boolean isNeedToConvertToPigeon) {

    Bird eagle = new Eagle("オジロワシ");
    Bird pigeon = new Pigeon(null);

    try {

      eagle.fly();
      if (isNeedToValidPigeon) {
        pigeon.fly();
        pigeon.isEmptyName();
      }
      if (isNeedToConvertToPigeon) {
        Bird convertedPigeon = Bird.convertToPigeon(eagle);
        convertedPigeon.fly();
      }
    } catch (NullPointerException | ClassCastException e) {
      Logger.log(Logger.LogType.DEBUG, e);
    } finally {
      System.out.println(":: [INFO] finally ブロックです.");
    }
    System.out.println(":: [INFO] try-catch を抜けました.");
  }
}

