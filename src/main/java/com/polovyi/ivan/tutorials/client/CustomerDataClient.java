package com.polovyi.ivan.tutorials.client;

import com.github.javafaker.Faker;
import com.polovyi.ivan.tutorials.util.SleepUtils;
import com.polovyi.ivan.tutorials.dto.CustomerDataResponse;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Random;
import java.util.stream.LongStream;

import static java.util.stream.Collectors.toList;

@Slf4j
public class CustomerDataClient {

    private static final List<CustomerDataResponse> allCustomers;

    static {
        Faker faker = new Faker();
        allCustomers = LongStream.range(1, 3)
                .mapToObj(i -> CustomerDataResponse.builder()
                        .id(i)
                        .createdAt(
                                LocalDate.now().minus(Period.ofDays((new Random().nextInt(365 * 10)))))
                        .fullName(faker.name().fullName())
                        .phoneNumber(faker.phoneNumber().cellPhone())
                        .address(faker.address().fullAddress())
                        .build())
                .collect(toList());
    }

    public CustomerDataResponse fetchCustomerById(Long customerId) {
        log.info("Getting customer by id {}....", customerId);
        SleepUtils.loadingSimulator(5);
        return allCustomers.stream()
                .filter(customer -> customer.getId().equals(customerId))
                .findAny()
                .orElseThrow();

    }
}
