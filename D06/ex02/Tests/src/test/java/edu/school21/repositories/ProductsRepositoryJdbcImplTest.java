package edu.school21.repositories;

import edu.school21.models.Product;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

public class ProductsRepositoryJdbcImplTest {

    DataSource dataSource;
   // EmbeddedDatabase database;
    ProductsRepository repository;

    @BeforeEach
    void init() {
            EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
            dataSource = builder
                    .setType(EmbeddedDatabaseType.H2)
                    .addScript("schema.sql")
                    .addScript("data.sql")
                    .build();
        repository = new ProductsRepositoryJdbcImpl(dataSource);
        }



    final List<Product> EXPECTED_FIND_ALL_PRODUCTS = new ArrayList() {{
        add(new Product( 1L, "Apples", 32L));
        add(new Product(2L, "Oranges", 62L));
        add(new Product(3L, "Cherries", 92L));
        add(new Product(4L, "Avocado", 75L));
        add(new Product(5L, "Bananas", 72L));
    }};

    final Product EXPECTED_FIND_BY_ID_PRODUCT = EXPECTED_FIND_ALL_PRODUCTS.get(2);
    final Product EXPECTED_UPDATED_PRODUCT = new Product(2L,"Updated", 72L);
    final Product EXPECTED_SAVE_PRODUCT = new Product(6L, "Mangos", 100L);


    @Test
    public void findAllTest() {
        Assertions.assertEquals(EXPECTED_FIND_ALL_PRODUCTS, repository.findAll());
    }

    @Test
    public void findByIdTest() {
        Assertions.assertEquals(EXPECTED_FIND_BY_ID_PRODUCT, repository.findById(EXPECTED_UPDATED_PRODUCT.getProductID()));
    }

    @Test
    public void updateTest() {
        repository.update(EXPECTED_UPDATED_PRODUCT);
        Assertions.assertEquals(EXPECTED_FIND_BY_ID_PRODUCT, repository.findById(EXPECTED_UPDATED_PRODUCT.getProductID()));
    }

    @Test
    public void saveTest() {
        repository.save(EXPECTED_SAVE_PRODUCT);
        Assertions.assertEquals(EXPECTED_SAVE_PRODUCT, repository.findById(EXPECTED_SAVE_PRODUCT.getProductID()));
    }

    @Test
    public void deleteTest() {
        repository.delete(EXPECTED_FIND_ALL_PRODUCTS.get(2).getProductID());
        Assertions.assertNull(repository.findById(EXPECTED_FIND_ALL_PRODUCTS.get(2).getProductID()));
    }

//    @AfterEach
//    public void shutDown() {
//        database.shutdown();
//    }


}
