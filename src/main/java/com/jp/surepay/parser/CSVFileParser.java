package com.jp.surepay.parser;

import com.jp.surepay.interfaces.IFileParser;
import com.jp.surepay.pojo.FileDataObject;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class CSVFileParser implements IFileParser {

    /**
     * Read the uploaded the file as inputStream and parse it to the file data object.
     *
     * @param fileStream data inputStream from uploaded file.
     * @return list of data object based on file.
     * @throws IOException
     */
    @Override
    public List<FileDataObject> parse(InputStream fileStream) throws IOException {
        BufferedReader fileReader = null;
        List<FileDataObject> fileDataObjectList = new ArrayList<>();
        fileReader = new BufferedReader(new InputStreamReader(fileStream, StandardCharsets.UTF_8));
        CSVParser csvParser = new CSVParser(fileReader, CSVFormat.DEFAULT.withFirstRecordAsHeader());
        Iterable<CSVRecord> csvRecords = csvParser.getRecords();
        for (CSVRecord csvRecord : csvRecords) {
            FileDataObject dataObject = new FileDataObject(Integer.parseInt(csvRecord.get(0)),
                    csvRecord.get(1),
                    csvRecord.get(2),
                    new BigDecimal(csvRecord.get(3)),
                    new BigDecimal(csvRecord.get(4)),
                    new BigDecimal(csvRecord.get(5)));
            fileDataObjectList.add(dataObject);
        }
        return fileDataObjectList;
    }
}


