package src.main.java.searchengine.exceptions;

public class EmptyQueryException extends Exception{
    public EmptyQueryException(String message){
        super(message);
    }
}
