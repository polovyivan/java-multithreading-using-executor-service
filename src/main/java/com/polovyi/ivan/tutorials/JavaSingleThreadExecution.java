package com.polovyi.ivan.tutorials;

import com.polovyi.ivan.tutorials.client.CustomerDataClient;
import com.polovyi.ivan.tutorials.client.CustomerPurchaseTransactionClient;
import com.polovyi.ivan.tutorials.dto.CustomerDataResponse;
import com.polovyi.ivan.tutorials.dto.CustomerWithTransactionResponse;
import com.polovyi.ivan.tutorials.dto.PurchaseTransactionResponse;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
public class JavaSingleThreadExecution {

    public static void main(String[] args) {

        LocalDateTime startTime = LocalDateTime.now();

        CustomerPurchaseTransactionClient customerPurchaseTransactionClient = new CustomerPurchaseTransactionClient();
        List<PurchaseTransactionResponse> purchaseTransactionResponses = customerPurchaseTransactionClient.fetchByCustomerId(
                1L);

        CustomerDataClient customerDataClient = new CustomerDataClient();
        CustomerDataResponse customerDataResponse = customerDataClient.fetchCustomerById(1L);

        CustomerWithTransactionResponse customerWithTransactionResponse = CustomerWithTransactionResponse.builder()
                .customerDataResponse(customerDataResponse)
                .purchaseTransactionResponse(purchaseTransactionResponses)
                .build();
        log.info("Customer with purchase transaction {} ", customerWithTransactionResponse);
        log.info("Operation duration {} ", Duration.between(startTime, LocalDateTime.now()).toSeconds());

    }
}
