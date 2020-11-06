package StateCodeAnalyze;

import org.junit.Assert;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class CensusDataAnalyserTest {

    private static final String INDIAN_CENSUS_DATA_CSV_FILE_PATH = "./src/main/resources/StateCensusData.csv";
    private static final String WRONG_CENSUS_CSV_FILE_PATH = "./src/test/resources/StateCensusData.csv";
    private static final String WRONG_CENSUS_CSV_FILE_EXTENSION = "./src/main/resources/StateCensusData.txt";
    private static final String WRONG_CENSUS_CSV_DELIMITER = "./src/main/resources/StateCensusData.csv";
    private static final String WRONG_CENSUS_CSV_HEADER = "./src/main/resources/StateCensusData.csv";

    private static final String STATE_CODE_CSV_FILE_PATH = "./src/main/resources/StateCode.csv";
    private static final String WRONG_STATE_CODE_CSV_FILE_PATH = "./src/test/resources/StateCode.csv";
    private static final String WRONG_STATE_CODE_CSV_FILE_EXTENSION = "./src/main/resources/StateCode.ppt";
    private static final String WRONG_CENSUS_CSV_INTERNAL_FILE_ISSUES = "./src/main/resources/StateCode.csv";

    @Test
    public void givenIndianCensusDataCSVFile_ShouldMatchNumberOfRecordsInFile() {
        try {
            IndianCensusAnalyser censusAnalyser = new IndianCensusAnalyser();
            int count = censusAnalyser.loadCensusData(INDIAN_CENSUS_DATA_CSV_FILE_PATH);
            Assert.assertEquals(29, count);
        } catch (CensusAnalyserException exception) {
        }
    }

    @Test
    public void givenIndianCensusDataCSVFile_WhenPathIsIncorrect_ShouldThrowException() {
        try {
            IndianCensusAnalyser censusAnalyser = new IndianCensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadCensusData(WRONG_CENSUS_CSV_FILE_PATH);
        } catch (CensusAnalyserException exception) {
            Assert.assertEquals("Please check your file path", exception.getMessage());
        }
    }

    @Test
    public void givenIndianCensusDataCSVFile_WhenFileExtensionIncorrect_ShouldThrowException() {
        try {
            IndianCensusAnalyser censusAnalyser = new IndianCensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadCensusData(WRONG_CENSUS_CSV_FILE_EXTENSION);
        } catch (CensusAnalyserException exception) {
            Assert.assertEquals("Please check extension of your file", exception.getMessage());
        }
    }


    @Test
    public void givenStateCensusCSVFile_ShouldMatchNumberOfRecordsInFile() {
        try {
            IndianCensusAnalyser codeAnalyser = new IndianCensusAnalyser();
            int count = codeAnalyser.loadStateCodeData(STATE_CODE_CSV_FILE_PATH);
            Assert.assertEquals(37, count);
        } catch (CensusAnalyserException exception) {
        }
    }

    @Test
    public void givenStateCensusCSVFile_WhenPathIsIncorrect_ShouldThrowException() {
        try {
            IndianCensusAnalyser censusAnalyser = new IndianCensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadStateCodeData(WRONG_STATE_CODE_CSV_FILE_PATH);
        } catch (CensusAnalyserException censusAnalyserException) {
            Assert.assertEquals("Please check your file path", censusAnalyserException.getMessage());
        }
    }

    @Test
    public void givenStateCensusCSVFile_WhenFileExtensionIncorrect_ShouldThrowException() {
        try {
            IndianCensusAnalyser censusAnalyser = new IndianCensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadStateCodeData(WRONG_STATE_CODE_CSV_FILE_EXTENSION);
        } catch (CensusAnalyserException censusAnalyserException) {
            Assert.assertEquals("Please check extension of your file", censusAnalyserException.getMessage());
        }
    }

    @Test
    public void givenIndianCensusCSVFile_WhenHavingWrongDelimiter_ShouldThrowException() {
        try {
            IndianCensusAnalyser censusAnalyser = new IndianCensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadCensusData(WRONG_CENSUS_CSV_DELIMITER);
        } catch (CensusAnalyserException censusAnalyserException) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.INTERNAL_FILE_ISSUES, censusAnalyserException.type);
        }
    }

    @Test
    public void givenIndianCensusCSVFile_WhenHavingWrongHeader_ShouldThrowException() {
        try {
            IndianCensusAnalyser censusAnalyser = new IndianCensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadCensusData(WRONG_CENSUS_CSV_HEADER);
        } catch (CensusAnalyserException censusAnalyserException) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.INTERNAL_FILE_ISSUES, censusAnalyserException.type);
        }
    }

    @Test
    public void givenStateCensusCSVFile_WhenHavingWrongDelimiter_ShouldThrowException() {
        try {
            IndianCensusAnalyser censusAnalyser = new IndianCensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadCensusData(WRONG_CENSUS_CSV_INTERNAL_FILE_ISSUES);
        } catch (CensusAnalyserException censusAnalyserException) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.INTERNAL_FILE_ISSUES, censusAnalyserException.type);
        }
    }

    @Test
    public void givenStateCensusCSVFile_WhenHavingWrongHeader_ShouldThrowException() {
        try {
            IndianCensusAnalyser censusAnalyser = new IndianCensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadCensusData(WRONG_CENSUS_CSV_INTERNAL_FILE_ISSUES);
        } catch (CensusAnalyserException censusAnalyserException) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.INTERNAL_FILE_ISSUES, censusAnalyserException.type);
        }
    }


}