package com.jp.surepay.interfaces;

import com.jp.surepay.pojo.FileDataObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public interface IFileParser {
    List<FileDataObject> parse(InputStream fileStream) throws IOException;
}
