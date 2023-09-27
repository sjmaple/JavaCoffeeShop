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
        // lowercase the input
        var lowerInput = input.toLowerCase(Locale.ROOT);
        // create a string query that matches the input to the product name or description
        //var query = "SELECT * FROM product WHERE LOWER(product_name) LIKE '%" + lowerInput + "%' OR LOWER(description) LIKE '%" + lowerInput + "%'";

        // create a string query with named parameters that matches the input to the product name or description
        var query = "SELECT * FROM product WHERE LOWER(product_name) LIKE :input OR LOWER(description) LIKE :input";

        // create a native query from the string query
        var nativeQuery = em.createNativeQuery(query, Product.class);
        // set the named parameter to the input
        nativeQuery.setParameter("input", "%" + lowerInput + "%");

        // return the result list
        return nativeQuery.getResultList();

    }

}
