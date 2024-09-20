package constants;

/**
 * メッセージの定数クラス
 */
public final class MessageConstants {
    public static final String INFO_START_SYSTEM = "ATMシステムを起動しました。\n";
    public static final String INFO_END_SYSTEM = "ATMシステムを終了しました。\n";

    public static final String INPUT_ACCOUNTNO = "口座番号8桁を入力してください：";
    public static final String INPUT_PIN = "暗証番号4桁を入力してください：";
    public static final String INPUT_TRANSACTION_NUMBER = "ご希望のお取引番号を入力してください（1～5）：";
    public static final String INPUT_DEPOSIT_AMOUNT = "預入金額を入力してください：";
    public static final String INPUT_WITHDRAW_AMOUNT = "引出金額を入力してください：";
    public static final String INPUT_TRANSFER_DESTINATION = "振込先の口座番号8桁を入力してください：";
    public static final String INPUT_TRANSFER_AMOUNT = "振込金額を入力してください：";

    public static final String OUTPUT_BALANCE = "残高：%,d\n";
    public static final String OUTPUT_BALANCE_AFTER_TRANSACTION = "お取引後残高：%,d\n";
    public static final String OUTPUT_RECEIVER_NAME = "受取人名：%s 様\n";
    public static final String OUTPUT_TRANSFER_AMOUNT = "振込金額：%,d\n";

    public static final String TRANSACTION_MENU = "〔メニュー〕\n(1)お預入れ  (2)お引出し\n(3)残高照会  (4)お振込み\n(5)取引終了\n";
    public static final String TRANSACTION_END = "取引を終了しました。";
    public static final String TRANSACTION_HEADER_DEPOSIT = "〔お預入れ〕\n";
    public static final String TRANSACTION_HEADER_WITHDRAW = "〔お引出し〕\n";
    public static final String TRANSACTION_HEADER_BALANCE = "〔残高照会〕\n";
    public static final String TRANSACTION_HEADER_TRANSFER = "〔お振込み〕\n";
    public static final String TRANSACTION_SEPARATOR_START = "---------------------------------\n";
    public static final String TRANSACTION_SEPARATOR_END = "---------------------------------\n\n";

    public static final String AUTHENTICATION_SUCCESS = "認証ができました。\n\n";
    public static final String AUTHENTICATION_FAIL = "照合ができませんでした。\n口座番号またはパスワードが間違っています。\n\n";

    public static final String WARNING_INVALID_TRANSACTION_NUMBER = "取引番号が正しくありません。\n\n";
    public static final String WARNING_INVALID_TRANSFER_DESTINATION = "振込先の口座が存在しません。\n";
    public static final String WARNING_INSUFFICIENT_BALANCE = "残高が不足しています。\n";
    public static final String WARNING_SQL_ERROR = "データベース関連の例外が発生しました！\n";
    /**
     * インスタンス化防止のためのプライベートコンストラクタ
     */
    private MessageConstants(){}
}
