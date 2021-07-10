package school21.spring.service.repositories;

import org.springframework.jdbc.datasource.DriverManagerDataSource;
import school21.spring.service.models.User;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UsersRepositoryJdbcImpl implements UsersRepository {

    private DataSource dataSource;
    private ResultSet resultSet;
    private PreparedStatement prepStmt;
    private Connection connection;

    public UsersRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User findById(Long id) {

        try {
            Connection connection = dataSource.getConnection();
            prepStmt = connection.prepareStatement("SELECT * FROM users.usertable WHERE userid = " + id);
            resultSet = prepStmt.executeQuery();

            if (resultSet.next()) {
                User user = new User(resultSet.getLong(1), resultSet.getString(2));
                return user;
            }

        } catch (SQLException e) {
            e.getMessage();
        }
        return null;
    }

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        Long id = 1L;
        User userToAdd = null;
        while ((userToAdd = findById(id)) != null) {
            users.add(userToAdd);
            id++;
        }
        if (users.isEmpty())
            return null;
        return users;

    }

    @Override
    public void save(User entity) throws SQLException {
        try {
            prepStmt = connection.prepareStatement(String.format("INSERT INTO users.usertable VALUES (%d, '%s');", entity.getUserId(), entity.getEmail()));
            prepStmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(User entity) throws SQLException {

        try {
            connection = dataSource.getConnection();
            prepStmt = connection.prepareStatement("UPDATE users.usertable SET email = " +"\'" + entity.getEmail() + "\'" + " WHERE userid = " + entity.getUserId());
            prepStmt.execute();
        }
        catch (SQLException e) {
            e.getMessage();
        }

    }

    @Override
    public void delete(Long id) throws SQLException {

        try {
            connection = dataSource.getConnection();
            prepStmt = connection.prepareStatement("DELETE FROM users.usertable WHERE userid = " + id);
            prepStmt.execute();
        }
        catch (SQLException e) {
            e.getMessage();
        }

    }

    @Override
    public Optional<User> findByEmail(String email) throws SQLException {
        try {
            connection = dataSource.getConnection();
            prepStmt = connection.prepareStatement("SELECT * FROM users.usertable WHERE email = " + email);
            resultSet = prepStmt.executeQuery();
            if (resultSet.next()) {
                User user = new User(resultSet.getLong(1), resultSet.getString(2));
                return Optional.of(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
}
