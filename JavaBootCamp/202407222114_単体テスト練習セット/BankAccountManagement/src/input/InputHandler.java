package input;

import java.util.Scanner;

/**
 * 入力共通クラス
 */
public class InputHandler{
    private final String WARNING_INPUT_NOT_NUMBER = "入力値は数値ではありません。\n";
    private final String WARNING_INPUT_NOT_ALPHABET = "入力値は半角英字ではありません。\n";
    private final String WARNING_INPUT_WRONG_LENGTH = "%d桁で入力をしてください。\n\n";
    private final String WARNING_INPUT_NOT_POSITIVE = "入力値は正数ではありません。\n";
    private final String WARNING_INPUT_NOT_NEGATIVE = "入力値は負数ではありません。\n";

    private Scanner scanner;
    
    /**
     * コンストラクタ
     */
    public InputHandler(){
        scanner = new Scanner(System.in);
    }

    /**
     * 文字列の入力を行うメソッド
     * @param inputMessage 入力要求時に表示するメッセージ
     * @return キーボードから入力した文字列
     */
    public String inputString(String inputMessage){
        String input = "";
        System.out.print(inputMessage);
        input = scanner.nextLine();
        return input;
    }

    /**
     * 半角英字の入力を行うメソッド（有効な入力があるまで、入力を繰り返して行う）
     * @param inputMessage 入力要求時に表示するメッセージ
     * @return キーボードから入力した文字列（半角英字）
     */
    public String inputAlphabet(String inputMessage){
        String result = "";
        boolean isValid = false;

        while(!isValid){
            result = inputString(inputMessage);

            isValid = InputValidator.isAlphabet(result);

            if(!isValid){
                System.out.println(WARNING_INPUT_NOT_ALPHABET);
            }
        }

        return result;
    }

    /**
     * 整数の入力を行うメソッド（有効な入力があるまで、入力を繰り返して行う）
     * @param inputMessage 入力要求時に表示するメッセージ
     * @return キーボードから入力した整数
     */
    public int inputNumber(String inputMessage){
        int result = 0;
        boolean isValid = false;

        while(!isValid){
            System.out.print(inputMessage);
            String input = scanner.nextLine();

            isValid = InputValidator.isNumber(input);

            if(isValid){
                result = Integer.parseInt(input);
            }else{
                System.out.println(WARNING_INPUT_NOT_NUMBER);
            }
        }
        return result;
    }

    /**
     * 正の整数の入力を行うメソッド（有効な入力があるまで、入力を繰り返して行う）
     * @param inputMessage 入力要求時に表示するメッセージ
     * @return キーボードから入力した正の整数
     */
    public int inputPositiveNumber(String inputMessage){
        int result = 0;
        boolean isValid = false;

        while(!isValid){
            result = inputNumber(inputMessage);

            isValid = InputValidator.isPositiveNumber(result);

            if(!isValid){
                System.out.println(WARNING_INPUT_NOT_POSITIVE);
            }
        }
        return result;
    }

    /**
     * 負の整数の入力を行うメソッド（有効な入力があるまで、入力を繰り返して行う）
     * @param inputMessage 入力要求時に表示するメッセージ
     * @return キーボードから入力した整数
     */
    public int inputNegativeNumber(String inputMessage){
        int result = 0;
        boolean isValid = false;

        while(!isValid){
            result = inputNumber(inputMessage);

            isValid = InputValidator.isNegativeNumber(result);

            if(!isValid){
                System.out.println(WARNING_INPUT_NOT_NEGATIVE);
            }
        }
        return result;
    }

    /**
     * 桁数を指定して整数の入力を行うメソッド（有効な入力があるまで、入力を繰り返して行う）
     * @param inputMessage 入力要求時に表示するメッセージ
     * @param digit 入力値の桁数
     * @return キーボードから入力した整数
     */
    public int inputNumber(String inputMessage, int digit){
        int result = 0;
        boolean isValid = false;

        while(!isValid){
            System.out.print(inputMessage);
            String input = scanner.nextLine();

            boolean isNumber = InputValidator.isNumber(input);

            if(!isNumber){
                System.out.println(WARNING_INPUT_NOT_NUMBER);
                continue;
            }

            boolean hasDigits = InputValidator.hasDigits(input, digit);

            if(!hasDigits){
                System.out.printf(WARNING_INPUT_WRONG_LENGTH, digit);
                continue;
            }
            
            result = Integer.parseInt(input);
            isValid = true;
        }

        return result;
    }

    /**
     * 使用しているスキャナーを閉じるメソッド
     */
    public void close(){
        scanner.close();
    }
}
