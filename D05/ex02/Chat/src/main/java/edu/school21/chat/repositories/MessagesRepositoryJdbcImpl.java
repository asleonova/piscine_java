package edu.school21.chat.repositories;

import edu.school21.chat.models.Message;
import edu.school21.chat.models.Chatroom;
import edu.school21.chat.models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.Optional;

public class MessagesRepositoryJdbcImpl implements MessagesRepository {

    private DataSource dataSource;

    private void closeConnections(ResultSet rs, PreparedStatement ps, Connection conn) throws SQLException {
        rs.close();
        ps.close();
        conn.close();
    }

    public MessagesRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    // This method shall return a Message object where author and chatroom will be specified
    public Optional<Message> findById(Long id) throws SQLException {

        try {
            Connection connection = dataSource.getConnection();
            String selectSQL = "SELECT * FROM chat.message WHERE messageID = " + id;
            PreparedStatement prepStmt = connection.prepareStatement(selectSQL);
            ResultSet rs = prepStmt.executeQuery();

            if (rs.next() == false) {
                closeConnections(rs, prepStmt, connection);
                return null;
            }

            // тут заполнили поля, которые пришли нам по селекту ID для сообщения
            Message message = new Message();
            message.setMessageId(rs.getLong(1));
            message.setText(rs.getString(4));
            message.setLocalDateTime(rs.getTimestamp(5).toLocalDateTime());


            // теперь надо взять юзера, чтобы можно было заполнить поле "author".
            // до этого надо запомнить его id из таблицы message:
            Long userId = rs.getLong(2);

            // и еще для того, чтобы взять инфу из Chatroom, нам нужен id chatroom'a.
            Long chatRoomId = rs.getLong(3);

            selectSQL = "SELECT * FROM chat.user WHERE userID = " + userId;
            prepStmt = connection.prepareStatement(selectSQL);
            rs = prepStmt.executeQuery();

            if (rs.next() == true) {
                User user = new User();
                user.setUserId(userId);
                user.setLogin(rs.getString(2));
                user.setPassword(rs.getString(3));
                message.setAuthor(user);
            }
            // теперь достаем инфу из таблички по chatroom
            selectSQL = "SELECT * FROM chat.chatroom WHERE chatID = " + chatRoomId;
            prepStmt = connection.prepareStatement(selectSQL);
            rs = prepStmt.executeQuery();

            if (rs.next()) {
                Chatroom chatRoom = new Chatroom();
                chatRoom.setChatId(chatRoomId);
                chatRoom.setChatName(rs.getString(2));
                message.setChatroom(chatRoom);
                // по заданию не все поля надо заполнять
            }

            closeConnections(rs, prepStmt, connection);
            return Optional.of(message);
        } catch (SQLException e) {
            e.getMessage();
        }
        return Optional.empty();
    }

    // save method shall assign ID value for the incoming model after saving data in
    // the database
    public void save (Message message) {
        try {
            Connection connection = dataSource.getConnection();
            if (message.getAuthor().getUserId() == 0 || message.getChatroom().getChatId() == 0) {
                throw new NotSavedSubEntityException();
            }
            String selectSQL = "SELECT * FROM chat.user WHERE userID = " + message.getAuthor().getUserId();
            PreparedStatement prepStmt = connection.prepareStatement(selectSQL);
            ResultSet rs = prepStmt.executeQuery();

            if (rs.next() == false) {
                closeConnections(rs, prepStmt, connection);
                throw new NotSavedSubEntityException();
            }

            String text = message.getText();
            if (text != null) {
                text = "\'" + text + "\'" ;
            }

            String localDateTime;
            if (message.getLocalDateTime() != null) {
                localDateTime = Timestamp.valueOf(message.getLocalDateTime()).toString();
                localDateTime = "\'" + localDateTime + "\'";
            }
            else {
                localDateTime = "null";
            }
//            LocalDateTime localDateTime = message.getLocalDateTime();
//            if (localDateTime == null) {
//                java.time.LocalDateTime.now();
//            }
            Long userId = rs.getLong(1);

            selectSQL = "SELECT * FROM chat.chatroom WHERE chatID = " + message.getChatroom().getChatId();
            prepStmt = connection.prepareStatement(selectSQL);
            rs = prepStmt.executeQuery();

            if (rs.next() == false) {
                closeConnections(rs, prepStmt, connection);
                throw new NotSavedSubEntityException();
            }

            Long chatroomId = rs.getLong(1);

            // добавляем сообщение в базу
            prepStmt = connection.prepareStatement("INSERT INTO chat.message VALUES (" + "default" + ", " + userId +
                    ", " + chatroomId + ", " +  text + ", " + localDateTime + ") RETURNING messageID");
            rs = prepStmt.executeQuery();
            if (rs.next() == false) {
                closeConnections(rs, prepStmt, connection);
                throw new NotSavedSubEntityException();
            }
            // сетим айдишник сообщению, чтобы у нас в коде он, как и в базе, тоже был
            message.setMessageId(rs.getLong(1));
            closeConnections(rs, prepStmt, connection);
        }

        catch (SQLException e) {
            e.getMessage();
        }

    }
}
