package edu.school21.sockets.repositories;

import javax.sql.DataSource;
import javax.xml.crypto.Data;
import java.util.List;
import java.util.Optional;

import edu.school21.sockets.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


@Component
public class UsersRepositoryImpl implements UsersRepository {

    private JdbcTemplate jdbcTemplate;


    @Autowired
    public UsersRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public User findById(Long id) {
        return jdbcTemplate.query("SELECT * FROM users.usertable WHERE id=?", new Object[]{id}, new BeanPropertyRowMapper<>(User.class))
                .stream().findAny().orElse(null);
    }

    @Override
    public List<User> findAll() {

        List<User> users = jdbcTemplate.query("SELECT * FROM users.usertable", new BeanPropertyRowMapper<>(User.class));
        return users;
    }

    @Override
    public void save(User entity) {
        jdbcTemplate.update(String.format("INSERT INTO users.usertable VALUES (default, '%s', '%s');", entity.getUsername(), entity.getPassword()));
    }

    @Override
    public void update(User entity) {
        jdbcTemplate.update("UPDATE users.usertable SET username=?, password=? WHERE id=?", entity.getUsername(), entity.getPassword(),entity.getId());
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update("DELETE FROM users.usertable WHERE id=?", id);
    }

    @Override
    public Optional<User> findByLogin(String login) {
        return Optional.ofNullable(jdbcTemplate.query("SELECT * FROM users WHERE login =?",
                new Object[]{login}, new BeanPropertyRowMapper<>(User.class)).stream().findAny().orElse(null));
    }
}
