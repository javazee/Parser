package parsers;

import exceptions.FileIsDirectoryException;
import exceptions.NoSuchParserException;
import parsers.impl.CSVParser;
import parsers.impl.xmlParser.XMLParser;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;

public class ParserFactory {

    private ParserFactory(){}

    private static ParserFactory factory;

    public static ParserFactory getInstance(){
        if (factory == null){
            synchronized (ParserFactory.class){
                factory = new ParserFactory();
            }
        }
        return factory;
    }

    public Parser getParser(String path) throws NoSuchParserException, FileIsDirectoryException {
        if (new File(path).isDirectory()) throw new FileIsDirectoryException("Файл по указанному пути является директорией");
        if (isXML(path)){
            return new XMLParser(path);
        } else if (isCSV(path)){
            return new CSVParser();
        } else throw new NoSuchParserException("parser for documents with such format doesn't exist");
    }

    private static boolean isXML(String path) {
        try {
            byte[] bytes = Files.readAllBytes(Paths.get(path));
            return (char) bytes[0] == '<'
                    && (char) bytes[1] == '?'
                    && (char) bytes[2] == 'x'
                    && (char) bytes[3] == 'm'
                    && (char) bytes[4] == 'l';
        } catch (IOException e){
            e.printStackTrace();
            return false;
        }
    }

    private static boolean isCSV(String path){
        return Objects.equals(new File(path).getName().split("\\.")[1], "csv");
    }
}