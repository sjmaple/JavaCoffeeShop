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

        // create a query using named parameters that matches the lowerinput to the product description or product name
        var query = em.createQuery("Select p from Product p where lower(p.description) like :input OR lower(p.productName) like :input", Product.class);

        // set the input parameter
        query.setParameter("input", "%" + lowerInput + "%");

        // get the result list
        var resultList = (List<Product>) query.getResultList();
        return resultList;
    }

}
