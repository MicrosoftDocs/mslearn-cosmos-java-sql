// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
package com.azure.cosmos.examples.mslearnbasicapp.datatypes;

import lombok.Data;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CouponsUsed {

    /**
     * Code on physical coupon and associated in the system.
     */
    private String couponCode;
}
