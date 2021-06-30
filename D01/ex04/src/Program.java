import java.util.UUID;

public class Program {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_PURPLE = "\u001B[35m";

    public static void main(String args[]) throws UserNotFoundException {


        TransactionsService transactionsService = new TransactionsService();

        System.out.println(ANSI_PURPLE + "---------*************---------" + ANSI_RESET);
        System.out.println(ANSI_PURPLE + "CREATING 2 USERS, WITH THE BALANCES 900 AND 500: " + ANSI_RESET);
        System.out.println(ANSI_PURPLE + "---------*************---------" + ANSI_RESET);

        User userFirst = new User("First", 900);
        System.out.println("User: " + userFirst.getName() + "--> with the identifier: " + userFirst.getIdentifier()
                + "--> with the balance of: " + userFirst.getBalance());
        User userSecond = new User("Second", 500);
        System.out.println("User: " + userSecond.getName() + "--> with the identifier: " + userSecond.getIdentifier()
                + "--> with the balance of: " + userSecond.getBalance());


        System.out.println(ANSI_PURPLE + "---------*************---------" + ANSI_RESET);
        System.out.println(ANSI_PURPLE + "ADDING USERS TO THE SERVICE: " + ANSI_RESET);
        System.out.println(ANSI_PURPLE + "---------*************---------" + ANSI_RESET);

        transactionsService.addUser(userFirst);
        transactionsService.addUser(userSecond);

        System.out.println(ANSI_PURPLE + "---------*************---------" + ANSI_RESET);
        System.out.println(ANSI_PURPLE + "PERFORMING TRANSFER TRANSACTION: " + ANSI_RESET);
        System.out.println(ANSI_PURPLE + "---------*************---------" + ANSI_RESET);
        transactionsService.transferTransaction(userFirst.getIdentifier(), userSecond.getIdentifier(), 50);
        transactionsService.transferTransaction(userFirst.getIdentifier(), userSecond.getIdentifier(), 10);
        transactionsService.transferTransaction(userFirst.getIdentifier(), userSecond.getIdentifier(), 30);
        transactionsService.transferTransaction(userSecond.getIdentifier(), userFirst.getIdentifier(), 50);
        transactionsService.transferTransaction(userSecond.getIdentifier(), userFirst.getIdentifier(), 10);
        transactionsService.transferTransaction(userSecond.getIdentifier(), userFirst.getIdentifier(), 30);


        System.out.println(ANSI_PURPLE + "---------*************---------" + ANSI_RESET);
        System.out.println(ANSI_PURPLE + "RETRIEVING BALANCE: " + ANSI_RESET);
        System.out.println(ANSI_PURPLE + "---------*************---------" + ANSI_RESET);

        int balance1 = transactionsService.retrieveUserBalance(userFirst.getIdentifier());
        int balance2 = transactionsService.retrieveUserBalance(userSecond.getIdentifier());

        System.out.println(ANSI_GREEN + "User 1 balance: " + balance1 + ANSI_RESET);
        System.out.println(ANSI_GREEN + "User 2 balance: " + balance2 + ANSI_RESET);


        System.out.println(ANSI_PURPLE + "---------*************---------" + ANSI_RESET);
        System.out.println(ANSI_PURPLE + "TRANSFORMING TRANSACTIONS INTO ARRAY: " + ANSI_RESET);
        System.out.println(ANSI_PURPLE + "---------*************---------" + ANSI_RESET);
        Transaction transactions[] = transactionsService.retrieveTransfer(userFirst.getIdentifier());

        System.out.println("\nFirst user transaction list after toArray:");
        for (int i = 0; i < transactions.length; ++i) {
            transactions[i].printTransaction();
        }

        System.out.println(ANSI_PURPLE + "---------*************---------" + ANSI_RESET);
        System.out.println(ANSI_PURPLE + "REMOVING 2nd transaction form the 2nd user: " + ANSI_RESET);
        System.out.println(ANSI_PURPLE + "---------*************---------" + ANSI_RESET);

        transactionsService.removeTransaction(userSecond.getIdentifier(), transactions[1].getIdentifier());

        System.out.println(ANSI_GREEN + "\nFirst user transaction list:" + ANSI_RESET);
        userFirst.printTransactionList();
        System.out.println(ANSI_GREEN + "\nSecond user transaction list:" + ANSI_RESET);
        userSecond.printTransactionList();

        System.out.println(ANSI_PURPLE + "---------*************---------" + ANSI_RESET);
        System.out.println(ANSI_PURPLE + "CHECKING UNPAIRED TRANSACTIONS: " + ANSI_RESET);
        System.out.println(ANSI_PURPLE + "---------*************---------" + ANSI_RESET);

        Transaction[] unpairedTr = transactionsService.checkValidityOfTransaction();

        for (int i = 0; i < unpairedTr.length; i++) {
            unpairedTr[i].printTransaction();
        }
    }
}
