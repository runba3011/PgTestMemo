package constants;

/**
 * 取引内容と入力値の管理用定数クラス
 */
public final class TransactionConstants {
    /** お預入れ */
    public static final int DEPOSIT = 1;
    /** お引出し */
    public static final int WITHDRAW = 2;
    /** 残高照会 */
    public static final int BALANCE = 3;
    /** お振込み */
    public static final int TRANSFER = 4;
    /** 取引終了 */
    public static final int END_TRANSACTION = 5;

    /**
     * インスタンス化防止のためのプライベートコンストラクタ
     */
    private TransactionConstants(){}
}
