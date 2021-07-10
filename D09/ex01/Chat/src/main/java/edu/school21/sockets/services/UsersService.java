package edu.school21.sockets.services;

import java.sql.SQLException;

public interface UsersService {
    void signUp(String login, String password) throws SQLException;
    boolean signIn(String login, String password) throws SQLException;
    void sendMessage(String username, String text) throws SQLException;
}
