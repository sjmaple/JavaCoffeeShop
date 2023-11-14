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
        //create sql query to search for products with productName or description containing input
        String sql = "SELECT * FROM product WHERE LOWER(product_name) LIKE '%" + input + "%' OR LOWER(description) LIKE '%" + input + "%'";
        //execute query
        List<Product> products = em.createNativeQuery(sql, Product.class).getResultList();
        return products;
    }

}
