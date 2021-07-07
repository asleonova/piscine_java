package edu.school21.repositories;

import edu.school21.models.Product;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductsReposutoryJdbcImpl implements ProductsRepository {

    private DataSource dataSource;

    private void closeConnections(ResultSet rs, PreparedStatement ps, Connection conn) throws SQLException {
        rs.close();
        ps.close();
        conn.close();
    }

    public ProductsReposutoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    private ResultSet selectAndResult(Connection connection, Long id, PreparedStatement prepStmt) throws SQLException {

        String selectSQL = "SELECT * FROM product.productTable WHERE productID = " + id;
        prepStmt = connection.prepareStatement(selectSQL);
        ResultSet rs = prepStmt.executeQuery();

        if (rs.next() == false) {
            closeConnections(rs, prepStmt, connection);
            return null;
        }
        else
            return rs;
    }

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


    public Optional<Product> findById(Long id) {

        try {
            PreparedStatement prepStmt = null;
            Connection connection = dataSource.getConnection();
            ResultSet res = selectAndResult(connection, id, prepStmt);

            if (res == null) {
                return null;
            }
            Product product = new Product();
            product.setProductID(res.getLong(1));
            product.setName(res.getString(2));
            product.setPrice(res.getLong(3));

            closeConnections(res, prepStmt, connection);
            return Optional.of(product);

        }
        catch (SQLException e) {
            e.getMessage();
        }
        return Optional.empty();
    }


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
            Connection connection = dataSource.getConnection();
            PreparedStatement prepStmt = connection.prepareStatement("INSERT INTO product.productTable VALUES (" + "default" + ", " + name +
                    ", " + price + ") RETURNING productID");
            ResultSet res = prepStmt.executeQuery();

            if (res.next() == false) {
                closeConnections(res, prepStmt, connection);
                return ;
            }
        }
        catch (SQLException e) {
            e.getMessage();
        }
    }

    public void delete (Long id) {

        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement prepStmt = connection.prepareStatement("DELETE FROM product.productTable WHERE productID = " + id;
            ResultSet res = prepStmt.executeQuery();

            if (res.next() == false) {
                closeConnections(res, prepStmt, connection);
                return ;
            }
        }
        catch (SQLException e) {
            e.getMessage();
        }
    }

    public void update(Product product) {

        String pName = product.getName();
        if (pName != null) {
            pName = "\'" + pName + "\'";
        }
        Long pPrice = product.getPrice();
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement prepStmt = connection.prepareStatement("UPDATE product.productTable SET name = " + pName + ", price = " + pPrice + " WHERE productID = " + product.getProductID();
            ResultSet res = prepStmt.executeQuery();

            if (res.next() == false) {
                closeConnections(res, prepStmt, connection);
                return ;
            }
        }
        catch (SQLException e) {
            e.getMessage();
        }

    }
}
