package com.jp.surepay.pojo;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FileDataObject {

    @JsonAlias({"transactionReference", "reference"})
    private int transactionReference;

    private String accountNumber;

    private String description;

    private BigDecimal startBalance;

    private BigDecimal mutation;

    private BigDecimal endBalance;
}
