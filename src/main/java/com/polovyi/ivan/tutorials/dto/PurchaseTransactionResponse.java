package com.polovyi.ivan.tutorials.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
public class PurchaseTransactionResponse {

    private String id;

    private String paymentType;

    private BigDecimal amount;

    private LocalDate createdAt;

    private Long customerId;

}
