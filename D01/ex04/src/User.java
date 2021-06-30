public class User {

    private final int Identifier;
    private String Name;
    private int Balance;
    private TransactionsLinkedList transactionsList;

    public User(String name, int balance) {
        Identifier = UserIdsGenerator.getInstance().generateId();
        Name = name;
        transactionsList = new TransactionsLinkedList();
        if (balance < 0) {
            System.out.println("error: balance cannot be negative. Set to 0 by default.");
            Balance = 0;
        } else
            Balance = balance;
    }

    public int getIdentifier() {
        return Identifier;
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
        if (balance < 0) {
            System.out.println("error: balance cannot be negative. Set to 0 by default.");
            Balance = 0;
        } else
            Balance = balance;
    }

    public TransactionsLinkedList getTransactionsList() {
        return transactionsList;
    }

    public void printTransactionList() {
        transactionsList.printTransactionList();
    }

    public void printTransactionListFirstLast() {
        transactionsList.printTransactionListFirstLast();
    }

}
