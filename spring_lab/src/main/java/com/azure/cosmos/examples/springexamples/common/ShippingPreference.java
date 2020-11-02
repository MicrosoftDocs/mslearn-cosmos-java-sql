// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
package com.azure.cosmos.examples.springexamples.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShippingPreference {

    /** WebCustomer's preferred shipping priority. */
    private int priority;

    /** WebCustomer address line 1. */
    private String addressLine1;

    /** WebCustomer address line 2. */
    private String addressLine2;

    /** WebCustomer address city. */
    private String city;

    /** WebCustomer address state. */
    private String state;

    /** WebCustomer address zip code. */
    private String zipCode;

    /** WebCustomer address country. */
    private String country;
}
