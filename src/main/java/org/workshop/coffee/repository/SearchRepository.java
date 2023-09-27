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
        // create a string query using named parameters that matches the input to the product name or description
        var query = em.createQuery("SELECT p FROM Product p WHERE LOWER(p.productName) LIKE :input OR LOWER(p.description) LIKE :input", Product.class);
        // set the named parameter to the input
        query.setParameter("input", "%" + lowerInput + "%");
        // return the result of the query
        return query.getResultList();
    }

}
