// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
package com.azure.cosmos.examples.mslearnbasicapp;

import com.azure.cosmos.CosmosAsyncClient;
import com.azure.cosmos.CosmosAsyncContainer;
import com.azure.cosmos.CosmosAsyncDatabase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class CosmosApp {

    /**
     * For application to log INFO and ERROR.
     */
    private static Logger logger = LoggerFactory.getLogger(CosmosApp.class.getSimpleName());

    /**
     * Azure Cosmos DB endpoint URI.
     */
    private static String endpointUri = "<your-cosmosdb-hostname>";

    /**
     * Azure Cosmos DB primary key.
     */
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
        logger.info("Hello World.");
    }
}
