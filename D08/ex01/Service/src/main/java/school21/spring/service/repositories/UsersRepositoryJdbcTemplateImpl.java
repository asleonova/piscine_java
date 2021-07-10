package school21.spring.service.repositories;
import school21.spring.service.models.User;

import javax.sql.DataSource;
import javax.xml.crypto.Data;
import java.util.List;
import java.util.Optional;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.BeanPropertyRowMapper;


public class UsersRepositoryJdbcTemplateImpl implements UsersRepository {

    private JdbcTemplate jdbcTemplate;

    public UsersRepositoryJdbcTemplateImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public User findById(Long id) {
        return jdbcTemplate.query("SELECT * FROM users.usertable WHERE userid=?", new Object[]{id}, new BeanPropertyRowMapper<>(User.class))
                .stream().findAny().orElse(null);
    }

    @Override
    public List<User> findAll() {

        List<User> users = jdbcTemplate.query("SELECT * FROM users.usertable", new BeanPropertyRowMapper<>(User.class));
        return users;
    }

    @Override
    public void save(User entity) {
        jdbcTemplate.update(String.format("INSERT INTO users.usertable VALUES (%d, '%s');", entity.getUserId(), entity.getEmail()));
    }

    @Override
    public void update(User entity) {
        jdbcTemplate.update("UPDATE users.usertable SET email=? WHERE userid=?", entity.getEmail(), entity.getUserId());
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update("DELETE FROM users.userTable WHERE userid=?", id);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return Optional.of(jdbcTemplate.queryForObject("SELECT * FROM users.usertable WHERE email=?", new Object[]{email}, new BeanPropertyRowMapper<>(User.class)));
    }
}
