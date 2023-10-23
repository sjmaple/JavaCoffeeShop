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

    public List<Product> searchProduct(String input) {
        //create a sql query using input for name or description
        var query = "SELECT * FROM product WHERE product_name LIKE '%" + input + "%' OR description LIKE '%" + input + "%'";
        //create a native query
        var nativeQuery = em.createNativeQuery(query, Product.class);
        //return the result list
        return nativeQuery.getResultList();
    }

}
