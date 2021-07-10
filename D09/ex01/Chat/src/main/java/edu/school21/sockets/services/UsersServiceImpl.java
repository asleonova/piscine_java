package edu.school21.sockets.services;

import edu.school21.sockets.models.User;
import edu.school21.sockets.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.Optional;

@Component
public class UsersServiceImpl implements UsersService {


    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UsersServiceImpl(UsersRepository usersRepository, PasswordEncoder passwordEncoder) {
        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void signUp(String login, String password) throws SQLException {

        User user = new User();
        user.setUsername(login);
        user.setPassword(password);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        usersRepository.save(user);
    }

    @Override
    public boolean signIn(String login, String password) throws SQLException {
        Optional<User> user = usersRepository.findByLogin(login);
        if (user.isPresent() == true) {
            if (passwordEncoder.matches(password, user.get().getPassword()))
                return true;
        }
        return false;
    }
}
