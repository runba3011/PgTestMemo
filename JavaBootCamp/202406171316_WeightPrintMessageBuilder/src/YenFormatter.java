import java.text.NumberFormat;

public class YenFormatter {
  public static void main(String[] args) {
    int noteBookPrice = 123456789;
    NumberFormat yenFormat = NumberFormat.getCurrencyInstance();
    System.out.printf(yenFormat.format(noteBookPrice));
  }
}
