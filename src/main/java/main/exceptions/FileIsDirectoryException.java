package main.exceptions;

public class FileIsDirectoryException extends Exception {

    public FileIsDirectoryException(String message){
        super(message);
    }
}
