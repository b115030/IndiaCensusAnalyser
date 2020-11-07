package StateCodeAnalyze;
public class CSVException extends Exception {

    public enum ExceptionType {
        CENSUS_FILE_PROBLEM,UNABLE_TO_PARSE , INCORRECT_EXTENSION, INTERNAL_FILE_ISSUES
    }
    ExceptionType type;

    public CSVException(String message, ExceptionType type) {
        super(message);
        this.type = type;
    }
}


