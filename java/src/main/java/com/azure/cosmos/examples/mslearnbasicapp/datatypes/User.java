package com.azure.cosmos.examples.mslearnbasicapp.datatypes;

import lombok.Data;
import java.util.List;

@Data
public class User {
    private String id;
    private String userId;
    private String lastName;
    private String firstName;
    private String email;
    private String dividend;
    private ShippingPreference shippingPreference;
    private List<OrderHistory> orderHistory;
    private List<CouponsUsed> coupons;
}
