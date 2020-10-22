// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
package com.azure.cosmos.examples.springexamples;

import com.azure.cosmos.examples.springexamples.common.CouponsUsed;
import com.azure.cosmos.examples.springexamples.common.OrderHistory;
import com.azure.cosmos.examples.springexamples.common.ShippingPreference;
import com.azure.spring.data.cosmos.core.mapping.Container;
import com.azure.spring.data.cosmos.core.mapping.PartitionKey;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Container(containerName = "WebCustomers", ru = "400")
public class User {

    /** Document ID (required by Azure Cosmos DB). */
    private String id;

    /** User ID. */
    private String userId;

    /** User last name. */
    @PartitionKey
    private String lastName;

    /** User first name. */
    private String firstName;

    /** User email address. */
    private String email;

    /** User dividend setting. */
    private String dividend;

    /** User shipping preferences. */
    private ShippingPreference shippingPreference;

    /** User order history. */
    private List<OrderHistory> orderHistory;

    /** Coupons recorded by the user. */
    private List<CouponsUsed> coupons;
}
