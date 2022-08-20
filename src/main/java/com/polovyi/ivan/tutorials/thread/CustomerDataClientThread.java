package com.polovyi.ivan.tutorials.thread;

import com.polovyi.ivan.tutorials.client.CustomerDataClient;
import com.polovyi.ivan.tutorials.dto.CustomerDataResponse;

public class CustomerDataClientThread implements Runnable {

    private CustomerDataClient customerDataClient = new CustomerDataClient();
    private CustomerDataResponse customerDataResponse;
    private Long customerId;

    public CustomerDataClientThread(Long customerId) {
        this.customerId = customerId;
    }

    @Override
    public void run() {
        customerDataResponse = customerDataClient.fetchCustomerById(customerId);
    }

    public CustomerDataResponse getCustomerResponse() {
        return customerDataResponse;
    }
}
