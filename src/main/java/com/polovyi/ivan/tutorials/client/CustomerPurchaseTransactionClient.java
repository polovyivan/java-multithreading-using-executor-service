package com.polovyi.ivan.tutorials.client;

import com.github.javafaker.CreditCardType;
import com.github.javafaker.Faker;
import com.polovyi.ivan.tutorials.dto.PurchaseTransactionResponse;
import com.polovyi.ivan.tutorials.util.SleepUtils;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

import static java.util.stream.Collectors.toList;

@Slf4j
public class CustomerPurchaseTransactionClient {

    private static final List<PurchaseTransactionResponse> allPurchaseTransaction;

    static {
        Faker faker = new Faker();
        allPurchaseTransaction = LongStream.range(0, 10)
                .mapToObj(i -> PurchaseTransactionResponse.builder()
                        .id(UUID.randomUUID().toString())
                        .createdAt(LocalDate.now().minus(Period.ofDays((new Random().nextInt(365 * 10)))))
                        .amount(new BigDecimal(faker.commerce().price().replaceAll(",", ".")))
                        .paymentType(List.of(CreditCardType.values())
                                .get(new Random().nextInt(CreditCardType.values().length)).toString())
                        .customerId((new Random().nextLong(3 - 1 + 1) + 1))
                        .build())
                .collect(toList());
    }

    public List<PurchaseTransactionResponse> fetchByCustomerId(Long customerId) {
        log.info("Getting purchase transaction by customerId {}...", customerId);
        SleepUtils.loadingSimulator(10);
        return allPurchaseTransaction.stream()
                .filter(purchaseTransactionResponse -> purchaseTransactionResponse.getCustomerId().equals(customerId))
                .collect(Collectors.toUnmodifiableList());
    }

}
