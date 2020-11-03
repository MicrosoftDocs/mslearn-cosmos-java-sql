// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
package com.azure.cosmos.examples.springexamples;

import com.azure.cosmos.CosmosException;
import com.azure.cosmos.examples.springexamples.common.CouponsUsed;
import com.azure.cosmos.examples.springexamples.common.OrderHistory;
import com.azure.cosmos.examples.springexamples.common.ShippingPreference;
import com.azure.cosmos.models.CosmosItemResponse;
import com.azure.cosmos.models.PartitionKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class CosmosSample implements CommandLineRunner {

    private final Logger logger = LoggerFactory.getLogger(CosmosSample.class);

    @Autowired
    private ReactiveWebCustomerRepository reactiveWebCustomerRepository;

    public static void main(String[] args) {
        SpringApplication.run(CosmosSample.class, args);
    }

    public void run(String... var1) {
        logger.info("Hello world.");
    }
}
