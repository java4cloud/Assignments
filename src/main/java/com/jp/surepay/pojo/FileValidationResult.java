package com.jp.surepay.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FileValidationResult {
    private int transactionReference;
    private String description;
}
