public class PayInformation{
  private  String money;
  private boolean isPaid;

  public PayInformation(String money){
    this.money = money;
    this.isPaid = false;
  }

  public void pay(){
    this.isPaid = true;
  }

  public String getMoney(){
    return this.money;
  }

  public boolean getIsPaid(){
    return this.isPaid;
  }
}