package com.jp.surepay.annotation;

import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;

public class FileTypeAllowedValidator implements ConstraintValidator<FileTypeAllowed, MultipartFile> {

    private List<String> expectedValues;
    private String returnMessage;

    @Override
    public void initialize(FileTypeAllowed requiredIfChecked) {
        expectedValues = Arrays.asList(requiredIfChecked.values());
        returnMessage = requiredIfChecked.message().concat(expectedValues.toString());
    }

    @Override
    public boolean isValid(MultipartFile file, ConstraintValidatorContext context) {
        String extension = FilenameUtils.getExtension(file.getOriginalFilename());
        boolean valid = expectedValues.contains(extension);
        if (!valid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(returnMessage)
                    .addConstraintViolation();
        }
        return valid;
    }
}
