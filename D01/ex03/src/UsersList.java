public interface UsersList {

    void addUser(User user);
    User RetrieveUserById(int id) throws UserNotFoundException;
    User RetrieveUserByIndex(int index);
    int RetrieveNumberOfUsers();

}
