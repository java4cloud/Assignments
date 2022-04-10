package com.jp.surepay.service;

import com.jp.surepay.enums.FileValidation;
import com.jp.surepay.pojo.FileDataObject;
import com.jp.surepay.pojo.FileValidationResult;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ValidationService {

    /**
     *
     * Execute all file validations on the file data and collect the result in list of result object.
     *
     * @param fileDataObjects list of data records.
     * @return list of return validation result.
     */
    public List<FileValidationResult> processFile(List<FileDataObject> fileDataObjects) {
        List<FileValidationResult> validationResults = new ArrayList<>();
        for (FileValidation value : FileValidation.values()) {
            validationResults.addAll(value.validate(fileDataObjects));
        }
        return validationResults;
    }
}