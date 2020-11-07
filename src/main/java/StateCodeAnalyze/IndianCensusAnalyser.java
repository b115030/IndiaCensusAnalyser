package StateCodeAnalyze;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;
import java.util.stream.StreamSupport;

public class IndianCensusAnalyser {
    List<IndianCensusData> censusCSVList = null;
    List<StateCodeData> stateCodeCSVList = null;

    public static void validateExtension(String csvFilePath) throws CSVException {
        InputValidator inputValidator = new InputValidator();
        boolean result = inputValidator.validateFileExtension(csvFilePath);
        if (!result)
            throw new CSVException("Please check extension of your file", CSVException.ExceptionType.INCORRECT_EXTENSION);
    }

    public int loadCensusData(String csvFilePath ) throws CSVException {
        validateExtension(csvFilePath);
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))){
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCommonsCSVBuilder();
            censusCSVList = csvBuilder.getCSVFileList(reader, IndianCensusData.class);
            return censusCSVList.size();
        } catch (IOException e) {
            throw new CSVException("Please check your file path", CSVException.ExceptionType.CENSUS_FILE_PROBLEM);
        }catch (RuntimeException e) {
            throw new CSVException("Internal file error.Please check your csv file." , CSVException.ExceptionType.INTERNAL_FILE_ISSUES);
        }
    }

    public int loadStateCodeData(String csvFilePath ) throws CSVException {
        validateExtension(csvFilePath);
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))){
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            stateCodeCSVList = csvBuilder.getCSVFileList(reader, StateCodeData.class);
            return stateCodeCSVList.size();
        } catch (IOException e) {
            throw new CSVException("Please check your file path", CSVException.ExceptionType.CENSUS_FILE_PROBLEM);
        }catch (RuntimeException e) {
            throw new CSVException("Internal file error.Please check your csv file." , CSVException.ExceptionType.INTERNAL_FILE_ISSUES);
        }
    }
    private <E> int getCountOfRecords(Iterator<E> iterator) {
        Iterable<E> csvIterable = () -> iterator;
        int countEntries = (int) StreamSupport.stream(csvIterable.spliterator(), false).count();
        return countEntries;
    }
}
