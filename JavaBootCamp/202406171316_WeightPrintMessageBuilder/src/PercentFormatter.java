import java.text.DecimalFormat;

public class PercentFormatter {
  public static void main(String[] args){
    Double companyGrowRatePerYear = 123456.78935465;
    DecimalFormat percentFormat = new DecimalFormat("0,000.##%");
    System.out.println(percentFormat.format(companyGrowRatePerYear));
  }
}
