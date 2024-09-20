public class CustomerManager {
    public static void main(String[] args) throws Exception {
        Customer yamamoto = new Customer("山本一郎","200 円");
        Customer yamada  = new Customer("山田太郎","300 円");
        yamamoto.pay();
        yamamoto.showIsPaid();
        yamada.showIsPaid();
    }
}


