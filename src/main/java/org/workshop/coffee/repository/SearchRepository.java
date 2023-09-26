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
        // create string query with named parameters that searches for input in product name and description
        var query = "SELECT * FROM product WHERE LOWER(product_name) LIKE :input OR LOWER(description) LIKE :input";

        // create native query
        var nativeQuery = em.createNativeQuery(query, Product.class);
        // set named parameter
        nativeQuery.setParameter("input", "%" + lowerInput + "%");
        // execute query and return result list
        return nativeQuery.getResultList();
    }

}
