package com.jp.surepay.controller;

import com.jp.surepay.annotation.FileTypeAllowed;
import com.jp.surepay.enums.FileType;
import com.jp.surepay.pojo.FileValidationResult;
import com.jp.surepay.service.ValidationService;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.ConstraintViolationException;
import java.util.List;

@Validated
@Controller
public class FileValidationController {

    @Autowired
    private ValidationService validationService;

    @PostMapping("/upload")
    public ResponseEntity<?> uploadFile(@RequestParam("file")
                                            @FileTypeAllowed(values = {"csv", "json"})
                                                    MultipartFile file) {
        List<FileValidationResult> validationResults;
        try {
            String extension = FilenameUtils.getExtension(file.getOriginalFilename());
            validationResults = validationService
                    .processFile(FileType.enumOf(extension)
                            .parserClass().parse(file.getInputStream()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(validationResults);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ResponseEntity<String> handleConstraintViolationException(ConstraintViolationException e) {
        return new ResponseEntity<>("Validation error: " + e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
