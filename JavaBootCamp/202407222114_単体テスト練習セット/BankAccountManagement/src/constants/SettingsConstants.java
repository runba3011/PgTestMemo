package constants;

/**
 * 設定値の定数クラス
 */
public final class SettingsConstants {
    /** データベースのホスト名 */
    public static final String DB_HOST_NAME = "localhost"; 
    /** データベースのデータベース名 */
    public static final String DB_DATABASE_NAME = "bankaccoutdb";
    /** データベースのポート番号 */
    public static final String DB_PORT = "13306";
    /** データベースの接続文字列 */
    public static final String DB_CONNECTION_URL = "jdbc:mysql://" + DB_HOST_NAME + ":" + DB_PORT + "/" + DB_DATABASE_NAME;
    /** データベースのユーザー名 */
    public static final String DB_USER = "bamUser";
    /** データベースのパスワード */
    public static final String DB_PASSWORD = "bamP@ssword";

    /**
     * インスタンス化防止のためのプライベートコンストラクタ
     */
    private SettingsConstants(){}
}
