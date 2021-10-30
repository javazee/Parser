package parsers.impl.xmlParser;

import org.xml.sax.SAXException;
import parsers.Parser;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class XMLParser implements Parser {

    private final String path;

    public HashMap<Integer, Integer> statistics;

    public XMLParser(String path){
        this.path = path;
    }

    @Override
    public void parse(){
        try {
            File file = new File(path);
            SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
            SaxHandler handler = new SaxHandler();
            parser.parse(file, handler);
            statistics = handler.getStatistics();
        } catch (ParserConfigurationException | IOException | SAXException ex){
            ex.printStackTrace();
        }
    }

    public void printStatistics(){
        if (statistics == null){
            System.out.println("Для получения статистики необходимо предварительно распарсить файл");
        } else {
            System.out.println(this.getStatistics(statistics));
        }
    }
}
