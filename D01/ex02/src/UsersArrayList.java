public class UsersArrayList implements UsersList {
    private int arraySize = 10;
    private int capacity = 10;
    private int size = 0;

    private User []usersArray = new User[arraySize];

    @Override
    public void addUser(User user) {
        if (capacity == size)
        {
            User []newArray = new User[capacity * 2];
            int i = 0;
            for (; i < capacity; ++i)
                newArray[i] = usersArray[i];
            newArray[i++] = user;
            capacity *= 2;
            usersArray = newArray;
        }
        else
        {
            usersArray[size] = user;
            size++;
        }
    }

    @Override
    public User RetrieveUserById(int id) {
        return null;
    }

    @Override
    public User RetrieveUserByIndex(int index) {
        return null;
    }

    @Override
    public int RetrieveNumberOfUsers() {
        return 0;
    }
}