public interface UsersList {

    void addUser(User user);
    User RetrieveUserById(int id);
    User RetrieveUserByIndex(int index);
    int RetrieveNumberOfUsers();

}
