package com.azure.cosmos.examples.mslearnbasicapp.datatypes;

import lombok.Data;

@Data
public class ShippingPreference {
    private int priority;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String state;
    private String zipCode;
    private String country;
}
