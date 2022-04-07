package dev.matthias.utilities;

public class IncorrectLoginCredentialsException extends Exception {
    public IncorrectLoginCredentialsException(String errorMsg){
        super(errorMsg);
    }
}
