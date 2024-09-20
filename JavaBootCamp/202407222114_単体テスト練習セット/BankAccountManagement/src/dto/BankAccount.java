package dto;

/*
 * 銀行口座クラス
 */
public class BankAccount{
    /** 口座番号 */
    private int accountNo;
    /** 口座残高 */
    private int balance;
    /** 口座名義 */
    private String holderName;

    /**
     * 口座番号を取得
     * @return 口座番号
     */
    public int getAccountNo(){
        return accountNo;
    }

    /**
     * 講座残高を取得
     * @return 講座残高
     */
    public int getBalance(){
        return balance;
    }

    /**
     * 口座名義を取得
     * @return 口座名義
     */
    public String getHolderName(){
        return holderName;
    }

    /**
     * 口座番号を設定する
     * @param accountNo 設定する口座番号
     */
    public void setAccountNo(int accountNo){
        this.accountNo = accountNo;
    }

    /**
     * 口座残高を設定する
     * @param balance 設定する口座残高
     */
    public void setBalance(int balance){
        this.balance = balance;
    }

    /**
     * 口座名義を設定する
     * @param holderName 口座名義
     */
    public void setHolderName(String holderName){
        this.holderName = holderName;
    }
}
