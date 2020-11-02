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
@Container(containerName = "WebCustomer", ru = "400")
public class WebCustomer {

    /** Document ID (required by Azure Cosmos DB). */
    private String id;

    /** WebCustomer ID. */
    private String userId;

    /** WebCustomer last name. */
    @PartitionKey
    private String lastName;

    /** WebCustomer first name. */
    private String firstName;

    /** WebCustomer email address. */
    private String email;

    /** WebCustomer dividend setting. */
    private String dividend;

    /** WebCustomer shipping preferences. */
    private ShippingPreference shippingPreference;

    /** WebCustomer order history. */
    private List<OrderHistory> orderHistory;

    /** Coupons recorded by the user. */
    private List<CouponsUsed> coupons;
}
