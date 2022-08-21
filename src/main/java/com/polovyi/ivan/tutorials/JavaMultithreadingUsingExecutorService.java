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
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Slf4j
public class JavaMultithreadingUsingExecutorService {

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        LocalDateTime startTime = LocalDateTime.now();

        CustomerPurchaseTransactionClient customerPurchaseTransactionClient = new CustomerPurchaseTransactionClient();
        CustomerDataClient customerDataClient = new CustomerDataClient();

        ExecutorService executorService = Executors.newFixedThreadPool(2);

        Future<List<PurchaseTransactionResponse>> purchaseTransactionResponsesFuture = executorService.submit(
                () -> customerPurchaseTransactionClient.fetchByCustomerId(
                        1L));
        Future<CustomerDataResponse> customerDataResponseFuture = executorService.submit(
                () -> customerDataClient.fetchCustomerById(1L));

        List<PurchaseTransactionResponse> purchaseTransactionResponses = purchaseTransactionResponsesFuture.get();
        CustomerDataResponse customerDataResponse = customerDataResponseFuture.get();

        executorService.shutdown();

        CustomerWithTransactionResponse customerWithTransactionResponse = CustomerWithTransactionResponse.builder()
                .customerDataResponse(customerDataResponse)
                .purchaseTransactionResponse(purchaseTransactionResponses)
                .build();
        log.info("Customer with purchase transaction {} ", customerWithTransactionResponse);
        log.info("Operation duration {} ", Duration.between(startTime, LocalDateTime.now()).toSeconds());

    }
}

