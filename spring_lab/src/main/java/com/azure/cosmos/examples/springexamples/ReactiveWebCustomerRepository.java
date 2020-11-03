// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
package com.azure.cosmos.examples.springexamples;

import com.azure.spring.data.cosmos.repository.Query;
import com.azure.spring.data.cosmos.repository.ReactiveCosmosRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface ReactiveWebCustomerRepository extends ReactiveCosmosRepository<WebCustomer, String> {

    Flux<WebCustomer> findByFirstName(String firstName);

    @Query(value = "SELECT * FROM User WHERE User.lastName = @lastName")
    Flux<WebCustomer> findByLastName(@Param("lastName") String lastName);

}

