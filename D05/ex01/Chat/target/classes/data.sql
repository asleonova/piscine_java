INSERT INTO chat.user VALUES
(default, 'Richard', '1234');
INSERT INTO chat.user VALUES
(default, 'Erlich', '1234');
INSERT INTO chat.user VALUES
(default, 'Big Head', '1234');
INSERT INTO chat.user VALUES
(default, 'Gilfoyle', '1234');
INSERT INTO chat.user VALUES
(default, 'Dinesh', '1234');

INSERT INTO chat.chatroom VALUES
(default, 'Chat1', (SELECT userID FROM chat.user WHERE login = 'Richard')),
(default, 'Chat2', (SELECT userID FROM chat.user WHERE login = 'Erlich')),
(default, 'Chat3', (SELECT userID FROM chat.user WHERE login = 'Big Head')),
(default, 'Chat4', (SELECT userID FROM chat.user WHERE login = 'Gilfoyle')),
(default, 'Chat5', (SELECT userID FROM chat.user WHERE login = 'Dinesh'));

INSERT INTO chat.message VALUES
(default, (SELECT userID FROM chat.user WHERE login = 'Richard'), (SELECT chatID FROM chat.chatroom WHERE chatName = 'Chat1'), 'Message1'),
(default, (SELECT userID FROM chat.user WHERE login = 'Erlich'), (SELECT chatID FROM chat.chatroom WHERE chatName = 'Chat2'), 'Message2'),
(default, (SELECT userID FROM chat.user WHERE login = 'Big Head'), (SELECT chatID FROM chat.chatroom WHERE chatName = 'Chat3'), 'Message3'),
(default, (SELECT userID FROM chat.user WHERE login = 'Gilfoyle'), (SELECT chatID FROM chat.chatroom WHERE chatName = 'Chat4'), 'Message4'),
(default, (SELECT userID FROM chat.user WHERE login = 'Dinesh'), (SELECT chatID FROM chat.chatroom WHERE chatName = 'Chat5'), 'Message5');

INSERT INTO chat.chats VALUES
(default, (SELECT userID FROM chat.user WHERE login = 'Richard'), (SELECT chatID FROM chat.chatroom WHERE chatName = 'Chat1')),
(default, (SELECT userID FROM chat.user WHERE login = 'Erlich'), (SELECT chatID FROM chat.chatroom WHERE chatName = 'Chat2')),
(default, (SELECT userID FROM chat.user WHERE login = 'Big Head'), (SELECT chatID FROM chat.chatroom WHERE chatName = 'Chat3')),
(default, (SELECT userID FROM chat.user WHERE login = 'Gilfoyle'), (SELECT chatID FROM chat.chatroom WHERE chatName = 'Chat4')),
(default, (SELECT userID FROM chat.user WHERE login = 'Dinesh'), (SELECT chatID FROM chat.chatroom WHERE chatName = 'Chat5'));
