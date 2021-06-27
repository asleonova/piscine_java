public class User {

    private int Identifier;
    private String Name;
    private int Balance;

    public User(int identifier, String name, int balance) {
        Identifier = identifier;
        Name = name;
        if (balance < 0) {
            System.out.println("error: balance cannot be negative. Set to 0 by default.");
            Balance = 0;
        } else
            Balance = balance;
    }

    public int getIdentifier() {
        return Identifier;
    }

    public void setIdentifier(int identifier) {
        Identifier = identifier;
    }


    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getBalance() {
        return Balance;
    }

    public void setBalance(int balance) {
        Balance = balance;
    }
}
