import org.junit.Assert;
import org.junit.Test;
import stateCodeAnanlyser.StateCodeAnalyser;
import stateCodeAnanlyser.StateCodeAnalyserException;

public class StateCodeAnalyserTest {

    private static final String STATE_CODE_CSV_FILE_PATH = "./src/main/resources/StateCode.csv" ;

    @Test
    public void givenStateCensusCSVFile_ShouldMatchNumberOfRecordsInFile() {
        try {
            StateCodeAnalyser codeAnalyser = new StateCodeAnalyser();
            int count = codeAnalyser.loadStateCodeData(STATE_CODE_CSV_FILE_PATH);
            Assert.assertEquals(37 , count);
        }catch (StateCodeAnalyserException stateCodeAnalyserException) {
        }
    }
}
