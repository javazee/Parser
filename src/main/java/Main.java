import main.exceptions.FileIsDirectoryException;
import main.exceptions.NoSuchParserException;
import main.parsers.Parser;
import main.parsers.ParserFactory;

import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        while (true) {
            System.out.println("Введите путь до файла и нажмите клавишу Enter." +
                    " (Для выхода из программы введите 'q' и нажмите Enter)");
            Scanner input = new Scanner(System.in);
            String path = input.nextLine();
            if (path.equals("q")){
                System.exit(0);
            } else {
                try {
                    Parser parser = ParserFactory.getInstance().getParser(path);
                    parser.parse();
                    parser.printStatistics();
                } catch (NoSuchParserException | FileIsDirectoryException ex) {
                    System.out.println(ex.getMessage());
                } catch (NoSuchFileException ex) {
                    System.out.println("Файл отсутствует по указанному пути");
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}
