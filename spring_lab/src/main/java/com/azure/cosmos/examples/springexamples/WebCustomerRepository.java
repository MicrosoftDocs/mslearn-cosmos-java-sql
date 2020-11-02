// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
package com.azure.cosmos.examples.springexamples;

import com.azure.spring.data.cosmos.repository.CosmosRepository;
import com.azure.spring.data.cosmos.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WebCustomerRepository extends CosmosRepository<WebCustomer, String> {

    Iterable<WebCustomer> findByFirstName(String firstName);

    WebCustomer findByIdAndLastName(String id, String lastName);

    // Query for all documents
    @Query(value = "SELECT * FROM c")
    List<WebCustomer> getAllUsers();

    // Query for equality using ==
    @Query(value = "SELECT * FROM c WHERE c.id = @documentId")
    List<WebCustomer> getUsersWithEquality(@Param("documentId") String documentId);

    // Query for inequality using !=
    @Query(value = "SELECT * FROM c WHERE c.id != @documentId")
    List<WebCustomer> getUsersWithInequalityMethod1(@Param("documentId") String documentId);

    // Query for inequality using NOT
    @Query(value = "SELECT * FROM c WHERE c.id <> @documentId")
    List<WebCustomer> getUsersWithInequalityMethod2(@Param("documentId") String documentId);

    // Query combining equality and inequality
    @Query(value = "SELECT * FROM c WHERE c.lastName = @documentLastName AND c.id != @documentId")
    List<WebCustomer> getUsersWithEqualityAndInequality(@Param("documentLastName") String documentLastName, @Param("documentId") String documentId);

    // Query using range operators like >, <, >=, <=
    @Query(value = "SELECT * FROM Families f WHERE f.Children[0].Grade > 5")
    List<WebCustomer> getUsersWithRange();

    // Query using range operators against strings
    @Query(value = "SELECT * FROM Families f WHERE f.Address.State > 'NY'")
    List<WebCustomer> getUsersWithRangeAgainstStrings();

    // Query with ORDER BY
    @Query(value = "SELECT * FROM Families f WHERE f.LastName = 'Andersen' ORDER BY f.Children[0].Grade")
    List<WebCustomer> getUsersWithOrderBy();

    // Query with DISTINCT
    @Query(value = "SELECT DISTINCT c.lastName from c")
    List<WebCustomer> getUsersWithDistinct();

    // Query with aggregate functions
    @Query(value = "SELECT VALUE COUNT(f) FROM Families f WHERE f.LastName = 'Andersen'")
    List<WebCustomer> getUsersWithAggregate();

    // Query with aggregate functions within documents
    @Query(value = "SELECT VALUE COUNT(child) FROM child IN f.Children")
    List<WebCustomer> getUsersWithAggregateWithinDocuments();

    // Work with subdocuments
    @Query(value = "SELECT VALUE c FROM c IN f.Children")
    List<WebCustomer> getUsersSubdocuments();

    // Query with single intra-document join
    @Query(value = "SELECT f.id FROM Families f JOIN c IN f.Children")
    List<WebCustomer> getUsersWithSingleJoin();

    // Query with two joins
    @Query(value = "SELECT f.id as family, c.FirstName AS child, p.GivenName AS pet \" +\n" +
            "                                           \"FROM Families f \" +\n" +
            "                                           \"JOIN c IN f.Children \" +\n" +
            "                                           \"join p IN c.Pets\"")
    List<WebCustomer> getUsersWithTwoJoins();

    // Query with two joins and a filter
    @Query(value = "SELECT f.id as family, c.FirstName AS child, p.GivenName AS pet \" +\n" +
            "                                           \"FROM Families f \" +\n" +
            "                                           \"JOIN c IN f.Children \" +\n" +
            "                                           \"join p IN c.Pets \" +\n" +
            "                                           \"WHERE p.GivenName = 'Fluffy'")
    List<WebCustomer> getUsersByWithTwoJoinsAndFilter();

    // Query with String STARTSWITH operator
    @Query(value = "SELECT * FROM family WHERE STARTSWITH(family.LastName, 'An')")
    List<WebCustomer> getUsersWithStringStartswith();

    // Query with math FLOOR operator
    @Query(value = "SELECT VALUE FLOOR(family.Children[0].Grade) FROM family")
    List<WebCustomer> getUsersWithMathFloor();

    // Query with array length operator
    @Query(value = "SELECT VALUE ARRAY_LENGTH(family.Children) FROM family")
    List<WebCustomer> getUsersWithArrayLength();
}
