package edu.school21.sockets.services;

import edu.school21.sockets.models.User;
import edu.school21.sockets.models.Message;
import edu.school21.sockets.repositories.MessageRepository;
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
    private final MessageRepository messageRepository;

    @Autowired
    public UsersServiceImpl(UsersRepository usersRepository, PasswordEncoder passwordEncoder, MessageRepository messageRepository) {
        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
        this.messageRepository = messageRepository;
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

    @Override
    public void sendMessage(String username, String text) throws SQLException {
        messageRepository.save(new Message(null, usersRepository.findByLogin(username).get(), text, null));
    }
}
