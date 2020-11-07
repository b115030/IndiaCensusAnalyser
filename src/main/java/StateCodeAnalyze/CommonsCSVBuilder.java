package StateCodeAnalyze;

import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.csv.CSVFormat;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

import java.util.Iterator;
import java.util.List;

public class CommonsCSVBuilder<E> implements ICSVBuilder {
    public Iterator getCSVFileIterator(Reader reader, Class csvClass) throws CSVException {
        try {
            CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT
                    .withFirstRecordAsHeader()
                    .withIgnoreHeaderCase()
                    .withTrim());
            return csvParser.iterator();
        } catch (IllegalStateException | IOException e) {
            throw new CSVException(e.getMessage(), CSVException.ExceptionType.UNABLE_TO_PARSE);
        }
    }

    @Override
    public List getCSVFileList(Reader reader, Class csvClass) throws CSVException {
        return null;
    }
}
