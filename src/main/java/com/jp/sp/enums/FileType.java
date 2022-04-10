package com.jp.sp.enums;

import com.jp.sp.interfaces.IFileParser;
import com.jp.sp.interfaces.IFileType;
import com.jp.sp.parser.CSVFileParser;
import com.jp.sp.parser.JSONFileParser;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public enum FileType implements IFileType<IFileParser> {
    CSV("csv") {
        /**
         * Create CSV parser object.
         *
         * @return CSV parser object.
         */
        @Override
        public IFileParser parserClass() {
            return new CSVFileParser();
        }
    },
    JSON("json") {
        /**
         * Create JSON parser object.
         *
         * @return JSON parser object.
         */
        @Override
        public IFileParser parserClass() {
            return new JSONFileParser();
        }
    };

    private static Map<String, FileType> mapping = new HashMap<>();

    /**
     * Map hold enum value as key and enum as value.
     */
    static {
        Arrays.stream(FileType.values()).forEach(fileType -> mapping.put(fileType.getType(), fileType));
    }

    private String type;

    FileType(String type) {
        this.type = type;
    }

    /**
     * Give enum based on its value.
     * @param value
     * @return
     */
    public static FileType enumOf(String value) {
        return mapping.get(value);
    }

    public String getType() {
        return type;
    }


}
