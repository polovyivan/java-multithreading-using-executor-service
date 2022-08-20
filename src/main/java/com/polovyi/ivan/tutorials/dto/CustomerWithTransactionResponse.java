package com.polovyi.ivan.tutorials.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CustomerWithTransactionResponse {

    private CustomerDataResponse customerDataResponse;

    private List<PurchaseTransactionResponse> purchaseTransactionResponse;

}
