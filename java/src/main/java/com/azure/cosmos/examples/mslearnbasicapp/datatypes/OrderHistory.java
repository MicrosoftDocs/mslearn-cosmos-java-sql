package com.azure.cosmos.examples.mslearnbasicapp.datatypes;

import lombok.Data;

@Data
public class OrderHistory {
    private String id;
    private String orderId;
    private String dateShipped;
    private String total;
}
