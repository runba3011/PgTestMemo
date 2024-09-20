public class Customer{
  public String name;
  public PayInformation payInformation;

  public Customer(String name , String money){
    this.name = name;
    this.payInformation = new PayInformation(money);
  }

  public void pay(){
    this.payInformation.pay();;
  }

  public void showIsPaid(){
    //支払い済みかどうかを表示
    if(!this.payInformation.getIsPaid()){
      //未支払い
      System.out.printf("%s さんに %s請求中です．\n",this.name , this.payInformation.getMoney());
    }else{
      //支払い済み
      System.out.printf("%s さんは %s支払い済みです．\n",this.name , this.payInformation.getMoney());
    }
  }
}
