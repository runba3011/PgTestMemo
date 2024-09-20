public class VariableNamesCorrecter {
  public static void main(String[] args) {
    String ballPenCode = "BPBK-007";
    int ballPenPrice = 110;
    int ballPenCount = 1;
    int ballPenTotal = 0;

    String noteCode = "NTBK-102 20P";
    int notePrice = 80;
    int noteCount = 5;
    int noteTotal = 0;

    int Total = 0;

    final double T = 1.10;
    ballPenTotal = (int) Math.round((ballPenPrice * ballPenCount) * T);
    noteTotal = (int) Math.round((notePrice * noteCount) * T);
    Total = ballPenTotal + noteTotal;

    System.out.printf("%12s: 1個 %3d 円 × %1d 個 = %3d 円(税込)%n",ballPenCode,ballPenPrice,ballPenCount,ballPenTotal);
    System.out.printf("%12s: 1個 %3d 円 × %1d 個 = %3d 円(税込)%n",noteCode,notePrice,noteCount,noteTotal);
  }
}
