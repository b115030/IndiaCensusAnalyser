import org.junit.Assert;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import stateCodeAnanlyser.StateCodeAnalyser;
import stateCodeAnanlyser.StateCodeAnalyserException;

public class StateCodeAnalyserTest {

    private static final String STATE_CODE_CSV_FILE_PATH = "./src/main/resources/StateCode.csv" ;
    private static final String WRONG_CENSUS_CSV_FILE_PATH = "./src/test/resources/StateCode.csv" ;
    private static final String WRONG_CENSUS_CSV_INTERNAL_FILE_ISSUES = "./src/main/resources/StateCode.csv" ;

    @Test
    public void givenStateCensusCSVFile_ShouldMatchNumberOfRecordsInFile() {
        try {
            StateCodeAnalyser codeAnalyser = new StateCodeAnalyser();
            int count = codeAnalyser.loadStateCodeData(STATE_CODE_CSV_FILE_PATH);
            Assert.assertEquals(37 , count);
        }catch (StateCodeAnalyserException stateCodeAnalyserException) {
        }
    }
    @Test
    public void givenStateCensusCSVFile_WhenPathIsIncorrect_ShouldThrowException() {
        try {
            StateCodeAnalyser censusAnalyser = new StateCodeAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(StateCodeAnalyserException.class);
            censusAnalyser.loadStateCodeData(WRONG_CENSUS_CSV_FILE_PATH);
        } catch (StateCodeAnalyserException stateCodeAnalyserException) {
            System.out.println(stateCodeAnalyserException.getMessage());
            Assert.assertEquals(StateCodeAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM , stateCodeAnalyserException.type);
        }
    }
    @Test
    public void givenStateCensusCSVFile_WhenHavingWrongDelimiter_ShouldThrowException()  {
        try {
            StateCodeAnalyser censusAnalyser = new StateCodeAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(StateCodeAnalyserException.class);
            censusAnalyser.loadStateCodeData(WRONG_CENSUS_CSV_INTERNAL_FILE_ISSUES);
        } catch (StateCodeAnalyserException stateCodeAnalyserException) {
            Assert.assertEquals(StateCodeAnalyserException.ExceptionType.INTERNAL_FILE_ISSUES , stateCodeAnalyserException.type);
        }
    }

    @Test
    public void givenStateCensusCSVFile_WhenHavingWrongHeader_ShouldThrowException()  {
        try {
            StateCodeAnalyser censusAnalyser = new StateCodeAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(StateCodeAnalyserException.class);
            censusAnalyser.loadStateCodeData(WRONG_CENSUS_CSV_INTERNAL_FILE_ISSUES);
        } catch (StateCodeAnalyserException stateCodeAnalyserException) {
            Assert.assertEquals(StateCodeAnalyserException.ExceptionType.INTERNAL_FILE_ISSUES , stateCodeAnalyserException.type);
        }
    }

}
