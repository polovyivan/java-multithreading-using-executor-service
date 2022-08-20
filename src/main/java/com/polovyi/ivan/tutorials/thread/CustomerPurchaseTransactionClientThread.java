package com.polovyi.ivan.tutorials.thread;

import com.polovyi.ivan.tutorials.client.CustomerPurchaseTransactionClient;
import com.polovyi.ivan.tutorials.dto.PurchaseTransactionResponse;

import java.util.List;

public class CustomerPurchaseTransactionClientThread extends Thread {

    private CustomerPurchaseTransactionClient customerPurchaseTransactionClient = new CustomerPurchaseTransactionClient();
    private List<PurchaseTransactionResponse> purchaseTransactionResponses;
    private Long customerId;

    public CustomerPurchaseTransactionClientThread(Long customerId) {
        this.customerId = customerId;
    }

    public List<PurchaseTransactionResponse> getPurchaseTransactionResponses() {
        return purchaseTransactionResponses;
    }

    @Override
    public void run() {
        purchaseTransactionResponses = customerPurchaseTransactionClient.fetchByCustomerId(customerId);
    }

}
