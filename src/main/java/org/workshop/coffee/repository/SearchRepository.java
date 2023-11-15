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
        //convert input to lowercase
        input = input.toLowerCase(Locale.ROOT);
        //create name query with bind variable for input for product_name or description
        String query = "SELECT p FROM Product p WHERE lower(p.productName) LIKE :input OR lower(p.description) LIKE :input";
        //create query
        List<Product> products = em.createQuery(query, Product.class)
                .setParameter("input", "%" + input + "%")
                .getResultList();
        //return list of products
       return products;

    }

}
