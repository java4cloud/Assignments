package com.jp.sp.enums;

import com.jp.sp.interfaces.IFileValidator;
import com.jp.sp.pojo.FileDataObject;
import com.jp.sp.pojo.FileValidationResult;

import java.util.List;
import java.util.stream.Collectors;

public enum FileValidation implements IFileValidator<List<FileDataObject>, List<FileValidationResult>> {
    UNIQUE_REFERENCE_VALIDATOR("Duplicate reference number.") {
        /**
         * Check the transaction reference for any duplicate record in file uploaded.
         *
         * @param fileDataObject file records
         * @return list of duplicates reference and message.
         */
        @Override
        public List<FileValidationResult> validate(List<FileDataObject> fileDataObject) {
            List<FileDataObject> duplicates = fileDataObject.stream()
                    .collect(Collectors.groupingBy(ref -> ref.getTransactionReference(), Collectors.toList()))
                    .values()
                    .stream()
                    .filter(i -> i.size() > 1)
                    .flatMap(j -> j.stream())
                    .collect(Collectors.toList());
            return duplicates
                    .stream()
                    .map(FileDataObject::getTransactionReference)
                    //.distinct()
                    .map(ref -> new FileValidationResult(ref, UNIQUE_REFERENCE_VALIDATOR.getValidationMessage()))
                    .collect(Collectors.toList());
        }
    },

    BALANCE_VALIDATOR("Balance calculation is wrong.") {
        /**
         * Verify the balance calculation based on start balance and mutations.
         *
         * @param fileDataObject file records
         * @return list of duplicates reference and message.
         */
        @Override
        public List<FileValidationResult> validate(List<FileDataObject> fileDataObject) {
            List<FileDataObject> mismatchBalance = fileDataObject.stream()
                    .filter(e -> !e.getStartBalance().add(e.getMutation()).stripTrailingZeros().equals(e.getEndBalance()))
                    .collect(Collectors.toList());
            return mismatchBalance
                    .stream()
                    .map(FileDataObject::getTransactionReference)
                    .map(ref -> new FileValidationResult(ref, BALANCE_VALIDATOR.getValidationMessage()))
                    .collect(Collectors.toList());
        }
    };
    private String validationMessage;

    FileValidation(String validationMessage) {
        this.validationMessage = validationMessage;
    }

    public String getValidationMessage() {
        return validationMessage;
    }
}
