// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
package com.azure.cosmos.examples.mslearnbasicapp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class CosmosApp {

    /**
     * For application to log INFO and ERROR.
     */
    private static Logger logger = LoggerFactory.getLogger(CosmosApp.class.getSimpleName());

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
