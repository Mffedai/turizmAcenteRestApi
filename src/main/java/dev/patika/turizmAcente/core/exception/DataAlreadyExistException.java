package dev.patika.turizmAcente.core.exception;

public class DataAlreadyExistException extends RuntimeException{
    public DataAlreadyExistException(String message){
        super(message);
    }
}
