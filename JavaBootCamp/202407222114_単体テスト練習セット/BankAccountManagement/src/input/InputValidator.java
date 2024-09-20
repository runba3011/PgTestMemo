package input;

import java.util.regex.Pattern;

/**
 * 入力値のバリデーションクラス
 */
public final class InputValidator {

    /**
     * 半角英数字バリデーションメソッド
     * @param value バリデーション対象の文字列
     * @return {@code value}が半角英数字のみで構成されている場合は{@code true}を返す
     */
    public static boolean isNumber(String value){
        boolean result = false;

        if (value != null) {
            Pattern pattern = Pattern.compile("^[0-9]+$|-[0-9]+$");
            result = pattern.matcher(value).matches();
        }

        return result;
    }

    /**
     * 正数バリデーションメソッド
     * @param value バリデーション対象の数値
     * @return {@code value > 0}の場合は{@code true}を返す
     */
    public static boolean isPositiveNumber(int value){
        return value > 0;
    }

    /**
     * 負数バリデーションメソッド
     * @param value バリデーション対象の数値
     * @return {@code value < 0}の場合は{@code true}を返す
     */
    public static boolean isNegativeNumber(int value){
        return value < 0;
    }

    /**
     * 半角英字バリデーションメソッド
     * @param value バリデーション対象の数値
     * @return {@code value}が半角英字のみで構成されている場合は{@code true}を返す
     */
    public static boolean isAlphabet(String value){
        boolean result = false;

        if(value != null){
            Pattern pattern = Pattern.compile("^[a-zA-Z]+$");
            result = pattern.matcher(value).matches();
        }

        return result;
    }

    /**
     * 桁数バリデーションメソッド
     * @param value バリデーション対象の値
     * @param digit 桁数
     * @return {@code value}の桁数が{@code digit}で指定した数の場合は{@code true}を返す
     */
    public static boolean hasDigits(String value, int digit){
        boolean result = false;

        if(value != null && value.length() == digit){
            result = true;
        }

        return result;
    }

    /**
     * インスタンス化防止のためのプライベートコンストラクタ
     */
    private InputValidator(){}
}
