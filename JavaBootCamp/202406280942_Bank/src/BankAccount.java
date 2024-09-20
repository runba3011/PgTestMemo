public class BankAccount {
  int id;
  String name;
  int age;
  double balance;

  public void setId(int id) {
    this.id = id;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public void setBalance(double balance) {
    this.balance = balance;
  }

  public int getId() {
    return this.id;
  }

  public String getName() {
    return this.name;
  }

  public int getAge() {
    return this.age;
  }

  public double getBalance() {
    return this.balance;
  }
}