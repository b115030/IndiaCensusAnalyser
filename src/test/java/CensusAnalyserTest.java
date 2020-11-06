import org.junit.Assert;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class CensusAnalyserTest {

    private static final String INDIAN_CENSUS_CSV_FILE_PATH = "./src/main/resources/StateCensusData.csv" ;
    private static final String WRONG_CENSUS_CSV_FILE_PATH = "./src/test/resources/StateCensusData.csv" ;
    private static final String WRONG_CENSUS_CSV_FILE_EXTENSION = "./src/main/resources/StateCensusData.txt" ;
    private static final String WRONG_CENSUS_CSV_DELIMITER = "./src/main/resources/StateCensusData.csv" ;


    @Test
    public void givenStateCensusCSVFile_ShouldMatchNumberOfRecordsInFile() {
        StateCensusAnalyser censusAnalyser = new StateCensusAnalyser();
        int count = 0;
        try {
            count = censusAnalyser.loadCensusData(INDIAN_CENSUS_CSV_FILE_PATH);
        } catch (CensusAnalyserException censusAnalyserException) {
        }
        Assert.assertEquals(29, count);
    }
    @Test
    public void givenStateCensusCSVFile_WhenPathIsIncorrect_ShouldThrowCustomException() {
        try {
            StateCensusAnalyser censusAnalyser = new StateCensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadCensusData(WRONG_CENSUS_CSV_FILE_PATH);
        } catch (CensusAnalyserException censusAnalyserException) {
            System.out.println(censusAnalyserException.getMessage());
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM , censusAnalyserException.type);
        }
    }
    @Test
    public void givenStateCensusCSVFile_WhenFileExtensionIncorrect_ShouldThrowException() {
        try {
            StateCensusAnalyser censusAnalyser = new StateCensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadCensusData(WRONG_CENSUS_CSV_FILE_EXTENSION);
        } catch (CensusAnalyserException censusAnalyserException) {
            System.out.println(censusAnalyserException.getMessage());
            Assert.assertEquals(CensusAnalyserException.ExceptionType.INCORRECT_EXTENSION , censusAnalyserException.type);
        }
    }
    @Test
    public void givenStateCensusCSVFile_WhenHavingWrongDelimiter_ShouldThrowException()  {
        try {
            StateCensusAnalyser censusAnalyser = new StateCensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            int count = censusAnalyser.loadCensusData(WRONG_CENSUS_CSV_DELIMITER);
            System.out.println(count);
        } catch (CensusAnalyserException censusAnalyserException) {
            System.out.println(censusAnalyserException.getMessage());
            Assert.assertEquals(CensusAnalyserException.ExceptionType.DELIMITER_ERROR , censusAnalyserException.type);
        }
    }
}
