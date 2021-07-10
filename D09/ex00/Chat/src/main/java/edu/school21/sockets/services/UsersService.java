package edu.school21.sockets.services;

import java.sql.SQLException;

public interface UsersService {
    void signUp(String login, String password) throws SQLException;
}
