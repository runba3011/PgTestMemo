import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class UsageFileWriter {

  static final Path DIR_PATH = Paths.get("C:\\tmp");
  static final Path FILE_PATH = Paths.get("C:\\tmp\\test.txt");
  static final String QUOTE_CURSOR = ">";

  static final LocalDateTime NOW = LocalDateTime.now();
  static List<String> lines = new ArrayList<>();

  public static void main(String[] args) {

    String name = "山本一郎";
    int usageContent = 5;

    if (isFileExist(DIR_PATH)) {
      outputExistLog(DIR_PATH.toAbsolutePath().toString());
    } else {
      createDirectory(DIR_PATH);
    }

    if (isFileExist(FILE_PATH)) {
      outputExistLog(FILE_PATH.toAbsolutePath().toString());
    } else {
      createFile(FILE_PATH);
    }

    outputUseCountHead();
    outputUseDateTime();
    outputUseDetail(name,usageContent);

    writeFile(FILE_PATH , lines);

    // ファイル内容の読み込み
    List<String> readLines = readFile(FILE_PATH);
    outputFileLines(readLines);
  }

  private static boolean isFileExist(Path path){
    return Files.exists(path);
  }

  private static void outputExistLog(String logString) {

    System.out.printf("%s は既に存在します. %n", logString);

  }

  private static void createDirectory(Path path) {
    try {
      Files.createDirectory(path);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private static void createFile(Path path) {
    try {
      Files.createFile(FILE_PATH);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private static void outputUseCountHead(){
    lines.add("利用回数");
    lines.add("-----");
  }

  private static void outputUseDateTime(){
    lines.add(
        String.format(
            " - 出力日時: %s-%s-%s(%s) %s:%s:%s",
            NOW.getYear(),
            NOW.getMonth(),
            NOW.getDayOfMonth(),
            NOW.getDayOfWeek()
                .getDisplayName(
                    TextStyle.SHORT, Locale.JAPANESE),
            NOW.getHour(),
            NOW.getMinute(),
            NOW.getSecond()));
  }

  private static void outputUseDetail(String name , int usageContent){
    
    lines.add(
        String.format(" - 氏名: %s", name));
    lines.add(
        String.format(" - 回数: %s", usageContent));
  }

  private static void writeFile(Path path , List<String> lines){
    try {
      Files.write(path, lines);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private static List<String> readFile(Path path){
    List<String> readLines = new ArrayList<>();
    try {
      readLines = Files.readAllLines(path);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return readLines;
  }

  private static void outputFileLines(List<String> readLines){
    System.out.println("ファイル内容を表示します...");
    for (String line : readLines) {
      System.out.printf("%s %s %n", QUOTE_CURSOR, line);
    }
  }
}