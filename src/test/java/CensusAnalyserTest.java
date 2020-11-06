import org.junit.Assert;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.IOException;

public class CensusAnalyserTest {

    private static final String INDIAN_CENSUS_CSV_FILE_PATH = "./src/main/resources/StateCensusData.csv" ;
    private static final String WRONG_CENSUS_CSV_FILE_PATH = "./src/test/resources/StateCensusData.csv" ;
    private static final String WRONG_CENSUS_CSV_FILE_EXTENSION = "./src/main/resources/StateCensusData.txt" ;


    @Test
    public void givenStateCensusCSVFile_ShouldMatchNumberOfRecordsInFile() {
        StateCensusAnalyser censusAnalyser = new StateCensusAnalyser();
        int count = 0;
        try {
            InputValidator inputValidator = new InputValidator();
            boolean result = inputValidator.validateFileExtension(INDIAN_CENSUS_CSV_FILE_PATH);
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
            Assert.assertEquals(CensusAnalyserException.ExceptionType.INCORRECT_EXTENSION , censusAnalyserException.type);
        }
    }
}
