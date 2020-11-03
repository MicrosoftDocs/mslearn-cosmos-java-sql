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

        // Create WebCustomer POJO instances

        WebCustomer maxaxam = new WebCustomer(
                "1",
                "maxaxam",
                "Axam",
                "Max",
                "maxaxam@contoso.com",
                "2.0",
                new ShippingPreference(
                        1,
                        "90 W 8th St",
                        "",
                        "New York",
                        "NY",
                        "10001",
                        "USA"
                ),
                new ArrayList<OrderHistory>(Arrays.asList(
                        new OrderHistory(
                                "3",
                                "1000",
                                "08/17/2018",
                                "52.49"
                        )
                )),
                new ArrayList<CouponsUsed>(Arrays.asList(
                        new CouponsUsed(
                                "A7B89F"
                        )
                ))
        );

        WebCustomer nelapin = new WebCustomer(
                "2",
                "nelapin",
                "Pindakova",
                "Nela",
                "nelapin@contoso.com",
                "8.50",
                new ShippingPreference(
                        1,
                        "505 NW 5th St",
                        "",
                        "New York",
                        "NY",
                        "10001",
                        "USA"
                ),
                new ArrayList<OrderHistory>(Arrays.asList(
                        new OrderHistory(
                                "4",
                                "1001",
                                "08/17/2018",
                                "105.89"
                        )
                )),
                new ArrayList<CouponsUsed>(Arrays.asList(
                        new CouponsUsed(
                                "Fall 2018"
                        )
                ))
        );

        createWebCustomerDocumentsIfNotExist(new ArrayList(Arrays.asList(maxaxam, nelapin)));
        readWebCustomerDocument(maxaxam);

        logger.info("Running derived query...");
        Flux<WebCustomer> webCustomers = reactiveWebCustomerRepository.findByFirstName("Max");
        webCustomers.flatMap(webCustomer -> {
            logger.info("- WebCustomer is : {}", webCustomer.getUserId());
            return Mono.empty();
        }).blockLast();

        logger.info("Running custom query...");
        webCustomers = reactiveWebCustomerRepository.findByLastName("Axam");
        webCustomers.flatMap(webCustomer -> {
            logger.info("- WebCustomer is : {}", webCustomer.getUserId());
            return Mono.empty();
        }).blockLast();

        deleteWebCustomerDocument(maxaxam);
    }

    /**
     * Take in list of Java POJOs and insert them into the database.
     * @param webCustomers List of WebCustomer POJOs to insert.
     */
    private void createWebCustomerDocumentsIfNotExist(final List<WebCustomer> webCustomers) {
        Flux.fromIterable(webCustomers).flatMap(webCustomer -> {
            logger.info("Creating WebCustomer {}", webCustomer.getId());
            return this.reactiveWebCustomerRepository.save(webCustomer);
        }).blockLast();
    }

    /**
     * Take in a Java POJO argument, extract ID and partition key, and read the corresponding document from the container.
     * In this case the ID is the partition key.
     * @param webCustomer User POJO to pull ID and partition key from.
     */
    private WebCustomer readWebCustomerDocument(final WebCustomer webCustomer) {
        WebCustomer webCustomerResult = null;

        try {
            logger.info("Read webCustomer {}", webCustomer.getId());
            webCustomerResult = this.reactiveWebCustomerRepository.findById(webCustomer.getId(), new PartitionKey(webCustomer.getLastName())).block();
        } catch (CosmosException de) {
            logger.error("Failed to read webCustomer {}", webCustomer.getId(), de);
        }

        return webCustomer;
    }

    /**
     * Take in a Java POJO argument, extract ID and partition key,
     * and delete the corresponding document.
     * @param webCustomer User POJO representing the document update.
     */
    private void deleteWebCustomerDocument(final WebCustomer webCustomer) {
        try {
            this.reactiveWebCustomerRepository.deleteById(webCustomer.getId(),new PartitionKey(webCustomer.getLastName())).block();
            logger.info("Deleted webCustomer {}", webCustomer.getId());
        } catch (CosmosException de) {
            logger.error("User {} could not be deleted.", webCustomer.getId());
        }
    }
}
