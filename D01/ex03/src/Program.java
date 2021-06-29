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

        User userFirst = new User("First", 900);
        System.out.println("User: " + userFirst.getName() + "\nwith the identifier: " + userFirst.getIdentifier()
                + "\nwith the balance of: " + userFirst.getBalance());
        User userSecond = new User( "Second", 0);
        System.out.println("User: " + userSecond.getName() + "\nwith the identifier: " + userSecond.getIdentifier()
                + "\nwith the balance of: " + userSecond.getBalance());


        Transaction creditTransaction = new Transaction(userSecond.getName(), userFirst.getName(), Category.credit, -50);
        userFirst.getTransactionsList().addTransaction(creditTransaction);
        userFirst.setBalance(userFirst.getBalance() + creditTransaction.getTransferAmount());
        UUID trID1 = creditTransaction.getIdentifier();






        creditTransaction = new Transaction(userSecond.getName(), userFirst.getName(), Category.credit, -30);
        userFirst.getTransactionsList().addTransaction(creditTransaction);
        userFirst.setBalance(userFirst.getBalance() + creditTransaction.getTransferAmount());
        UUID trID2 = creditTransaction.getIdentifier();

        for (int i = -100; i > -400; i -= 100) {
            creditTransaction = new Transaction(userSecond.getName(), userFirst.getName(), Category.credit, i);
            userFirst.getTransactionsList().addTransaction(creditTransaction);
            userFirst.setBalance(userFirst.getBalance() + creditTransaction.getTransferAmount());
        }

        creditTransaction = new Transaction(userSecond.getName(), userFirst.getName(), Category.credit, -10);
        userFirst.getTransactionsList().addTransaction(creditTransaction);
        userFirst.setBalance(userFirst.getBalance() + creditTransaction.getTransferAmount());
        UUID trID3 = creditTransaction.getIdentifier();

        userFirst.getTransactionsList().removeTransaction(trID2);





//        Transaction debitTransaction = new Transaction(userSecond.getName(), userFirst.getName(), Category.debit, 500);
//        userFirst.getTransactionsList().addTransaction(debitTransaction);
//        userFirst.setBalance(userFirst.getBalance() + debitTransaction.getTransferAmount());
//
//        System.out.println("User: " + userFirst.getName() + "\nwith the identifier: " + userFirst.getIdentifier()
//                + "\nwith the balance of: " + userFirst.getBalance());

        userFirst.printTransactionList();
        System.out.println('\n');
        userFirst.printTransactionListFirstLast();



    }
}
