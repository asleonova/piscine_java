public class UserIdsGenerator {
    private static int Identifier;
    private static UserIdsGenerator instance;

    public static UserIdsGenerator getInstance() {
        if (instance == null) {
            instance = new UserIdsGenerator();
        }
        return instance;
    }

    private UserIdsGenerator() {}

    public static int generateId()
    {
        return Identifier++;
    }

}

