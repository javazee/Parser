import main.exceptions.FileIsDirectoryException;
import main.exceptions.NoSuchParserException;
import main.parsers.Parser;
import main.parsers.ParserFactory;

public class Main {
    public static void main(String[] args) {

        String path = "/home/eduard/Рабочий стол/Parser/src/main/resources/address.xml";

        try {
            Parser parser = ParserFactory.getInstance().getParser(path);
            parser.parse();
            parser.printStatistics();
        } catch (NoSuchParserException | FileIsDirectoryException ex) {
            ex.printStackTrace();
        }
    }
}
