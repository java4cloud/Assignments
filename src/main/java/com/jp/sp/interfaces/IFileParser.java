package com.jp.sp.interfaces;

import com.jp.sp.pojo.FileDataObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public interface IFileParser {
    List<FileDataObject> parse(InputStream fileStream) throws IOException;
}
