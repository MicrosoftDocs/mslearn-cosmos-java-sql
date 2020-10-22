// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
package com.azure.cosmos.examples.springexamples;

import com.azure.spring.data.cosmos.repository.Query;
import com.azure.spring.data.cosmos.repository.ReactiveCosmosRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.util.List;

@Repository
public interface ReactiveUserRepository extends ReactiveCosmosRepository<User, String> {

    Flux<User> findByFirstName(String firstName);

    Flux<User> findByIdAndLastName(String id, String lastName);

    // Query for all documents
    @Query(value = "SELECT * FROM c")
    Flux<User> getAllUsers();

}

