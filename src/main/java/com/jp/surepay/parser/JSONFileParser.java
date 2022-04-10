package com.jp.surepay.parser;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jp.surepay.interfaces.IFileParser;
import com.jp.surepay.pojo.FileDataObject;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class JSONFileParser implements IFileParser {
    /**
     * Read the uploaded the file as inputStream and parse it to the file data object.
     *
     * @param fileStream data inputStream from uploaded file.
     * @return list of data object based on file.
     * @throws IOException
     */
    @Override
    public List<FileDataObject> parse(InputStream fileStream) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        List<FileDataObject> dataObjects = mapper.readValue(IOUtils.toString(fileStream, StandardCharsets.UTF_8),
                new TypeReference<ArrayList<FileDataObject>>() {});
        return dataObjects;
    }
}
