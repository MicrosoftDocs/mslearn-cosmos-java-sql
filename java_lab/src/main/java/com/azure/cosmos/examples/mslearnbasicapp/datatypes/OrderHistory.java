// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
package com.azure.cosmos.examples.mslearnbasicapp.datatypes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderHistory {

    /**
     * Document ID (Azure Cosmos DB requirement).
     */
    private String id;

    /**
     * Order ID.
     */
    private String orderId;

    /**
     * Date order shipped.
     */
    private String dateShipped;

    /**
     * Total cost of order.
     */
    private String total;
}
