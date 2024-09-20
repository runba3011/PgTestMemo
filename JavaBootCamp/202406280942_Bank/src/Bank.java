public class Bank {
  public static void main(String[] args) {

    BankAccount account = new BankAccount();
    account.setId(777);
    account.setName("山本一郎");
    account.setAge(3);
    account.setBalance(5211111110.0);

    System.out.printf(
        "ID:%d %s（%d 歳）さんの預金残高は %.0f 円です. %n",
        account.getId(),
        account.getName(),
        account.getAge(),
        account.getBalance());
  }
}