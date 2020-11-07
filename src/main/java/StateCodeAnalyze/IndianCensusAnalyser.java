package StateCodeAnalyze;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.stream.StreamSupport;

public class IndianCensusAnalyser {

    public static void validateExtension(String csvFilePath) throws CensusAnalyserException {
        InputValidator inputValidator = new InputValidator();
        boolean result = inputValidator.validateFileExtension(csvFilePath);
        if (!result)
            throw new CensusAnalyserException("Please check extension of your file", CensusAnalyserException.ExceptionType.INCORRECT_EXTENSION);
    }

    public int loadCensusData(String csvFilePath ) throws CensusAnalyserException {
        validateExtension(csvFilePath);
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))){
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator<IndianCensusData> censusCSVIterator = csvBuilder.getCSVFileIterator(reader, IndianCensusData.class);
            return this.getCountOfRecords(censusCSVIterator);
        } catch (IOException e) {
            throw new CensusAnalyserException("Please check your file path", CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        }catch (RuntimeException e) {
            throw new CensusAnalyserException("Internal file error.Please check your csv file." , CensusAnalyserException.ExceptionType.INTERNAL_FILE_ISSUES);
        }
    }

    public int loadStateCodeData(String csvFilePath ) throws CensusAnalyserException {
        validateExtension(csvFilePath);
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))){
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator<IndianCensusData> censusCSVIterator = csvBuilder.getCSVFileIterator(reader, IndianCensusData.class);
            return this.getCountOfRecords(censusCSVIterator);
        } catch (IOException e) {
            throw new CensusAnalyserException("Please check your file path", CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        }catch (RuntimeException e) {
            throw new CensusAnalyserException("Internal file error.Please check your csv file." , CensusAnalyserException.ExceptionType.INTERNAL_FILE_ISSUES);
        }
    }
    private <E> int getCountOfRecords(Iterator<E> iterator) {
        Iterable<E> csvIterable = () -> iterator;
        int countEntries = (int) StreamSupport.stream(csvIterable.spliterator(), false).count();
        return countEntries;
    }
}
