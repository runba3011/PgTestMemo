import java.math.BigDecimal;

public class AmountCalculator {
  public static void main(String[] args) {

    BigDecimal strictShippingRate = new BigDecimal("0.3");
    BigDecimal noteBookPrice = new BigDecimal("1000");

    BigDecimal resultAmount = noteBookPrice.multiply(strictShippingRate);
    System.out.format(
        "%.0f 円のノートの送料（%.0f%%）: %n",
        noteBookPrice,
        shippingRate * 100);

    System.out.println(resultAmount);
  }
}