import java.util.UUID;

public class Program {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_PURPLE = "\u001B[35m";

    public static void main(String args[]) throws UserNotFoundException {
        UsersArrayList uList = new UsersArrayList();

        for (int i = 0; i < 22; ++i) {
            uList.addUser(new User(String.valueOf(i), i));

        }

        System.out.println(ANSI_PURPLE + "---------*************---------" + ANSI_RESET);
        System.out.println(ANSI_PURPLE + "CREATING 2 USERS, WITH THE BALANCES 900 AND 0: " + ANSI_RESET);
        System.out.println(ANSI_PURPLE + "---------*************---------" + ANSI_RESET);

        User userFirst = new User("First", 900);
        System.out.println("User: " + userFirst.getName() + "--> with the identifier: " + userFirst.getIdentifier()
                + "--> with the balance of: " + userFirst.getBalance());
        User userSecond = new User( "Second", 0);
        System.out.println("User: " + userSecond.getName() + "--> with the identifier: " + userSecond.getIdentifier()
                + "--> with the balance of: " + userSecond.getBalance());



        System.out.println(ANSI_PURPLE + "---------*************---------" + ANSI_RESET);
        System.out.println(ANSI_PURPLE + "ADDING TRANSACTIONS TO THE LIST: " + ANSI_RESET);
        System.out.println(ANSI_PURPLE + "---------*************---------" + ANSI_RESET);

        Transaction creditTransaction = new Transaction(userSecond.getName(), userFirst.getName(), Category.credit, -50);
        userFirst.getTransactionsList().addTransaction(creditTransaction);
        userFirst.setBalance(userFirst.getBalance() + creditTransaction.getTransferAmount());


        creditTransaction = new Transaction(userSecond.getName(), userFirst.getName(), Category.credit, -30);
        userFirst.getTransactionsList().addTransaction(creditTransaction);
        userFirst.setBalance(userFirst.getBalance() + creditTransaction.getTransferAmount());
        UUID trID = creditTransaction.getIdentifier();

        for (int i = -100; i > -400; i -= 100) {
            creditTransaction = new Transaction(userSecond.getName(), userFirst.getName(), Category.credit, i);
            userFirst.getTransactionsList().addTransaction(creditTransaction);
            userFirst.setBalance(userFirst.getBalance() + creditTransaction.getTransferAmount());
        }

        creditTransaction = new Transaction(userSecond.getName(), userFirst.getName(), Category.credit, -10);
        userFirst.getTransactionsList().addTransaction(creditTransaction);
        userFirst.setBalance(userFirst.getBalance() + creditTransaction.getTransferAmount());
        UUID trID1 = creditTransaction.getIdentifier();

        userFirst.printTransactionList();

        System.out.println(ANSI_PURPLE + "---------*************---------" + ANSI_RESET);
        System.out.println(ANSI_PURPLE + "TRANSFORMING TRANSACTIONS INTO ARRAY: " + ANSI_RESET);
        System.out.println(ANSI_PURPLE + "---------*************---------" + ANSI_RESET);
        Transaction transactionArrayUserFirst[] = userFirst.getTransactionsList().toArray();
        System.out.println("\nFirst user transaction list after toArray:");
        for(int i = 0; i < transactionArrayUserFirst.length; ++i) {
            transactionArrayUserFirst[i].printTransaction();
        }

        System.out.println(ANSI_PURPLE + "---------*************---------" + ANSI_RESET);
        System.out.println(ANSI_PURPLE + "REMOVING 2ND AND LAST TRANSACTIONS: " + ANSI_RESET);
        System.out.println(ANSI_PURPLE + "---------*************---------" + ANSI_RESET);

        userFirst.getTransactionsList().removeTransaction(trID);
        userFirst.getTransactionsList().removeTransaction(trID1);

        userFirst.printTransactionList();

    }
}
