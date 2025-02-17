package stateCodeAnanlyser;

import StateCodeAnalyze.StateCodeData;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.stream.StreamSupport;

public class StateCodeAnalyser {

    public int loadStateCodeData(String csvFilePath) throws StateCodeAnalyserException {
        int recordCounter = 0;
        try {
            InputValidator inputValidator = new InputValidator();
            boolean result = inputValidator.validateFileExtension(csvFilePath);
            if (!result) {
                throw new StateCodeAnalyserException("Please check extension of your file", StateCodeAnalyserException.ExceptionType.INCORRECT_EXTENSION);
            }
            Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));
            CsvToBeanBuilder<StateCodeData> csvToBeanBuilder = new CsvToBeanBuilder(reader);
            csvToBeanBuilder.withType(StateCodeData.class);
            csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true);
            CsvToBean<StateCodeData> csvToBean = csvToBeanBuilder.build();
            Iterator<StateCodeData> stateCodeIterator = csvToBean.iterator();
            Iterable<StateCodeData> csvIterable = () -> stateCodeIterator;
            recordCounter = (int) StreamSupport.stream(csvIterable.spliterator(), false).count();
        } catch (IOException ioException) {
            throw new StateCodeAnalyserException("Please check given path", StateCodeAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        } catch (RuntimeException runtimeException) {
            throw new StateCodeAnalyserException("Internal file error.Please check your csv file.", StateCodeAnalyserException.ExceptionType.INTERNAL_FILE_ISSUES);
        }
        return recordCounter;
    }
}
