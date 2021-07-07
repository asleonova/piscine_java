package edu.school21.repositories;

import edu.school21.models.Product;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductsRepositoryJdbcImpl implements ProductsRepository {

    private DataSource dataSource;
    private ResultSet resultSet;
    private PreparedStatement prepStmt;
    private Connection connection;

    private void closeConnections() throws SQLException {
        resultSet.close();
        prepStmt.close();
        connection.close();
    }

    public ProductsRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    private void selectAndResult(Long id) throws SQLException {

        connection = dataSource.getConnection();
        String selectSQL = ("SELECT * FROM product.productTable WHERE productID =" + id);
        prepStmt = connection.prepareStatement(selectSQL);
        resultSet = prepStmt.executeQuery();

        if (resultSet.next() == false) {
            closeConnections();
        }
    }

    @Override
    public List<Product> findAll() {

        List<Product> products = new ArrayList<>();
        Long i = 1L;
        Optional <Product>pr;
        while ((pr = findById(i)).isPresent()) {
            products.add(pr.get());
            i++;
        }
        if (products.isEmpty())
            return null;
        return products;
    }

    @Override
    public Optional<Product> findById(Long id) {

        try {
            PreparedStatement prepStmt = null;
            Connection connection = dataSource.getConnection();
            selectAndResult(id);


            Product product = new Product();
            product.setProductID(resultSet.getLong(1));
            product.setName(resultSet.getString(2));
            product.setPrice(resultSet.getLong(3));

            closeConnections();
            return Optional.of(product);

        }
        catch (SQLException e) {
            e.getMessage();
        }
        return Optional.empty();
    }

    @Override
    public void save(Product product) {

        Long id = product.getProductID();
        String name = product.getName();
        Long price = product.getPrice();
        if (name != null) {
            name = "\'" + name + "\'";
        }
        if (id == 0 || name == null || price == 0) {
            return ;
        }
        try {
            connection = dataSource.getConnection();
            prepStmt = connection.prepareStatement(String.format("INSERT INTO product.productTable VALUES (default, '%s', %d);", product.getName(), product.getPrice()));
            prepStmt.execute();

            connection.close();
            prepStmt.close();
        }
        catch (SQLException e) {
            e.getMessage();
        }
    }

    @Override
    public void delete (Long id) {

        try {
            connection = dataSource.getConnection();
            prepStmt = connection.prepareStatement("DELETE FROM product.productTable WHERE productID = " + id);
            resultSet = prepStmt.executeQuery();

            closeConnections();
        }
        catch (SQLException e) {
            e.getMessage();
        }
    }

    @Override
    public void update(Product product) {

        String pName = product.getName();
        if (pName != null) {
            pName = "\'" + pName + "\'";
        }
        Long pPrice = product.getPrice();
        try {
            connection = dataSource.getConnection();
            prepStmt = connection.prepareStatement("UPDATE product.productTable SET name = " + pName + ", price = " + pPrice + " WHERE productID = " + product.getProductID());
            prepStmt.execute();
            connection.close();
            prepStmt.close();
        }
        catch (SQLException e) {
            e.getMessage();
        }

    }
}
