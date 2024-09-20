public class Kanzikun {

  public static void main(String[] args) {
    fairDevision(12000,3,3);
    devisionInFavorOfWoman(12000,3,3);
  }

  public static void fairDevision(int totalPrice, int numberOfMale, int numberOfFemale) {
    int amountPerPerson = totalPrice / (numberOfMale + numberOfFemale);
    System.out.printf("男性： %d 円\n",amountPerPerson);
    System.out.printf("女性： %d 円\n",amountPerPerson);
  }

  public static void devisionInFavorOfWoman(int totalPrice, int numberOfMale, int numberOfFemale) {
    //男性は女性よりも 1000 円多く支払う時だが、1000をそのまま設定すると女性は1000円減額、男性は1000円増額で2000円多く支払うことになってしまう。
    //そのため、1000/2を設定することで、上記を実現する。
    int REDUCTION_FOR_FEMALE = 1000 / 2;
    int amountPerPerson = totalPrice / (numberOfMale + numberOfFemale);
    System.out.printf("男性： %d 円\n",amountPerPerson+REDUCTION_FOR_FEMALE);
    System.out.printf("女性： %d 円\n", Math.max(0, amountPerPerson - REDUCTION_FOR_FEMALE));
  }
}
