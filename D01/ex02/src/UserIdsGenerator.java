public class UserIdsGenerator {
    private static int Identifier;
    private static UserIdsGenerator instance; // static, потому что привязка объекта к текущему классу

    public static UserIdsGenerator getInstance() {
        if (instance == null) {
            instance = new UserIdsGenerator();
        }
        return instance;
    }

    private UserIdsGenerator() {} // прячем, чтобы не происходило инициализации, до вызова метода getInstance();

    public static int generateId()
    {
        return Identifier++;
    }

}

// https://www.journaldev.com/1377/java-singleton-design-pattern-best-practices-examples
// это прикольная статья, объясняющая Singleton
