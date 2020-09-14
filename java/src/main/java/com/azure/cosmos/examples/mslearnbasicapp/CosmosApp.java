// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
package com.azure.cosmos.examples.mslearnbasicapp;

import com.azure.cosmos.ConsistencyLevel;
import com.azure.cosmos.CosmosAsyncClient;
import com.azure.cosmos.CosmosAsyncContainer;
import com.azure.cosmos.CosmosAsyncDatabase;
import com.azure.cosmos.CosmosClientBuilder;
import com.azure.cosmos.CosmosException;
import com.azure.cosmos.examples.mslearnbasicapp.datatypes.CouponsUsed;
import com.azure.cosmos.examples.mslearnbasicapp.datatypes.OrderHistory;
import com.azure.cosmos.examples.mslearnbasicapp.datatypes.ShippingPreference;
import com.azure.cosmos.examples.mslearnbasicapp.datatypes.User;
import com.azure.cosmos.implementation.HttpConstants;
import com.azure.cosmos.models.CosmosItemRequestOptions;
import com.azure.cosmos.models.CosmosItemResponse;
import com.azure.cosmos.models.PartitionKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class CosmosApp {

    /** For application to log INFO and ERROR. */
    private static Logger logger = LoggerFactory.getLogger(CosmosApp.class.getSimpleName());

    /** Azure Cosmos DB endpoint URI. */
    private static String endpointUri = "<your-cosmosdb-hostname>";

    /** Azure Cosmos DB primary key. */
    private static String primaryKey = "<your-cosmosdb-master-key>";

    /** Azure Cosmos DB client instance. */
    private static CosmosAsyncClient client;

    /** Azure Cosmos DB database instance. */
    private static CosmosAsyncDatabase database;

    /** Azure Cosmos DB container instance. */
    private static CosmosAsyncContainer container;

    private CosmosApp() {
        // not called
    }

    /**
     * Main.
     * @param args Command line arguments
     */
    public static void main(final String[] args) {
        try {
            CosmosApp p = new CosmosApp();
            p.basicOperations();
        } catch (CosmosException e) {
            logger.error("Failed while executing app.", e);
        } finally {
            logger.info("End of demo, press any key to exit.");
        }
    }

    /** Database access code. */
    private void basicOperations() {
        client = new CosmosClientBuilder()
                .endpoint(endpointUri)
                .key(primaryKey)
                .consistencyLevel(ConsistencyLevel.EVENTUAL)
                .directMode()
                .contentResponseOnWriteEnabled(true)
                .buildAsyncClient();

        database = client.getDatabase("Users");
        container = database.getContainer("WebCustomers");

        logger.info("Database and container validation complete");

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
        client.close();
    }

    /**
     * Take in list of Java POJOs, check if each exists, and if not insert it.
     * @param users List of User POJOs to insert.
     */
    private static void createUserDocumentsIfNotExist(final List<User> users) {
        Flux.fromIterable(users).flatMap(user -> {
            try {
                container.readItem(user.getId(), new PartitionKey(user.getUserId()), User.class).block();
                logger.info("User {} already exists in the database", user.getId());
                return Mono.empty();
            } catch (Exception err) {
                logger.info("Creating User {}", user.getId());
                return container.createItem(user, new PartitionKey(user.getUserId()), new CosmosItemRequestOptions());
            }
        }).blockLast();
    }

    /**
     * Take in a Java POJO argument, extract id and partition key, and read the corresponding document from the container.
     * In this case the id is the partition key.
     * @param user User POJO to pull id and partition key from.
     */
    private static CosmosItemResponse<User> readUserDocument(final User user) {
        CosmosItemResponse<User> userReadResponse = null;

        try {
            userReadResponse = container.readItem(user.getId(), new PartitionKey(user.getUserId()), User.class).block();
            logger.info("Read user {}", user.getId());
        } catch (CosmosException de) {
            logger.error("Failed to read user {}", user.getId(), de);
        }

        return userReadResponse;
    }
}
