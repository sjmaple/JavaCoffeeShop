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
        var query = em.createNativeQuery("Select * from Product where lower(description) like :lowerInput OR lower(product_name) like :lowerInput", Product.class);

        // set the named parameter to the lowerinput
        query.setParameter("lowerInput", "%" + lowerInput + "%");

        //var query = em.createNativeQuery("Select * from Product where lower(description) like '%" + lowerInput + "%' OR lower(product_name) like '%" + lowerInput + "%'", Product.class);

        // get the result list
        var resultList = (List<Product>) query.getResultList();
        // return the result list
        return resultList;
    }

}
