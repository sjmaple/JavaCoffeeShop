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
        // create string query using named parameters that matches input to product name or description
        var query = "SELECT p FROM Product p WHERE LOWER(p.productName) LIKE :input OR LOWER(p.description) LIKE :input";
        // create query
        var q = em.createQuery(query, Product.class);
        // set named parameter
        q.setParameter("input", "%" + lowerInput + "%");
        // return list of products
        return q.getResultList();


    }

}
