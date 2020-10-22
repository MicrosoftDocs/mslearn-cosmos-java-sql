// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
package com.azure.cosmos.examples.mslearnbasicapp.datatypes;

import lombok.Data;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShippingPreference {

    /**
     * User's preferred shipping priority.
     */
    private int priority;

    /**
     * User address line 1.
     */
    private String addressLine1;

    /**
     * User address line 2.
     */
    private String addressLine2;

    /**
     * User address city.
     */
    private String city;

    /**
     * User address state.
     */
    private String state;

    /**
     * User address zip code.
     */
    private String zipCode;

    /**
     * User address country.
     */
    private String country;
}
