package stateCodeAnanlyser;

public class StateCodeAnalyserException extends Exception {
    public enum ExceptionType {
        CENSUS_FILE_PROBLEM , UNABLE_TO_PARSE , INCORRECT_EXTENSION,INTERNAL_FILE_ISSUES
    }
    public ExceptionType type;

    public StateCodeAnalyserException(String message, ExceptionType type) {
        super(message);
        this.type = type;
    }
}
