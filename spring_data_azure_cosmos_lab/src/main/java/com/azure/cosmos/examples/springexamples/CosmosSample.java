// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
package com.azure.cosmos.examples.springexamples;

import com.azure.cosmos.examples.springexamples.common.CouponsUsed;
import com.azure.cosmos.examples.springexamples.common.OrderHistory;
import com.azure.cosmos.examples.springexamples.common.ShippingPreference;
import com.azure.cosmos.models.CosmosItemRequestOptions;
import com.azure.cosmos.models.PartitionKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

@SpringBootApplication
public class CosmosSample implements CommandLineRunner {

    private final Logger logger = LoggerFactory.getLogger(CosmosSample.class);

    @Autowired
    private ReactiveUserRepository reactiveUserRepository;

    public static void main(String[] args) {
        SpringApplication.run(CosmosSample.class, args);
    }

    public void run(String... var1) {

        // Create User POJO instances

        User maxaxam = new User(
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

        User nelapin = new User(
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

        createUserDocumentsIfNotExist(new ArrayList(Arrays.asList(maxaxam, nelapin)));

        /*

        logger.info("Using sync repository");

        // <Delete>

        userRepository.deleteAll();

        // </Delete>

        // <Create>

        logger.info("Saving user : {}", testUser1);
        userRepository.save(testUser1);

        // </Create>

        logger.info("Saving user : {}", testUser2);
        userRepository.save(testUser2);

        // <Read>        
        
        // to find by Id, please specify partition key value if collection is partitioned
        final User result = userRepository.findByIdAndLastName(testUser1.getId(), testUser1.getLastName());
        logger.info("Found user : {}", result);
        
        // </Read>        
        
        Iterator<User> usersIterator = userRepository.findByFirstName("testFirstName").iterator();

        logger.info("Users by firstName : testFirstName");
        while (usersIterator.hasNext()) {
            logger.info("user is : {}", usersIterator.next());
        }

        logger.info("Using reactive repository");

        // <Query>

        Flux<User> users = reactiveUserRepository.findByFirstName("testFirstName");
        users.map(u -> {
            logger.info("user is : {}", u);
            return u;
        }).subscribe();

        // </Query>


         */
    }

    /**
     * Take in list of Java POJOs and insert them into the database.
     * @param users List of User POJOs to insert.
     */
    private void createUserDocumentsIfNotExist(final List<User> users) {
        Flux.fromIterable(users).flatMap(user -> {
            logger.info("Creating User {}", user.getId());
            return this.reactiveUserRepository.save(user);
        }).blockLast();
    }
}
