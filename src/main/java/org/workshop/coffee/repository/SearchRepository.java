package org.workshop.coffee.repository;

import org.workshop.coffee.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.util.List;
import java.util.Locale;

@Repository
public class SearchRepository {

    @Autowired
    EntityManager em;

    @Autowired
    DataSource dataSource;

    public List<Product> searchProduct (String input) {
        // lowercase input
        var lowerInput = input.toLowerCase(Locale.ROOT);

        // create a string query that matches the input to the name of the product or description
        var query = "SELECT * FROM product WHERE LOWER(product_name) LIKE '%" + lowerInput + "%' OR LOWER(description) LIKE '%" + lowerInput + "%'";

        // create a native query
        var nativeQuery = em.createNativeQuery(query, Product.class);

        // return the result list
        return nativeQuery.getResultList();
    }

}
