public class Program {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_PURPLE = "\u001B[35m";

    public static void main(String args[]) {
        System.out.println(ANSI_PURPLE + "User with negative balance (should set to 0" + ANSI_RESET);
        User userOne = new User(4, "User One", -5);

        System.out.println("User: " + userOne.getName() + " --> with the identifier: " + userOne.getIdentifier()
                + " --> with the balance of: " + userOne.getBalance());


        System.out.println(ANSI_PURPLE + "\nCreating second user with the balance of 100" + ANSI_RESET);
        User userTwo = new User(1, "User Two", 100);

        System.out.println("User: " + userTwo.getName() + " --> with the identifier: " + userTwo.getIdentifier()
                + " --> with the balance of: " + userTwo.getBalance());

        System.out.println(ANSI_PURPLE + "\nPerforming transactions: " + ANSI_RESET);

        Transaction deb = new Transaction(userOne, userTwo, Category.debit, 5);
        Transaction cr = new Transaction(userTwo, userOne, Category.credit, -5);
        System.out.println("transfer amount from userTwo: " + deb.getTransferAmount());
        System.out.println("transfer amount to userOne: " + cr.getTransferAmount());
        userOne.setBalance(userOne.getBalance() + deb.getTransferAmount());
        userTwo.setBalance(userTwo.getBalance() + cr.getTransferAmount());

        System.out.println(ANSI_PURPLE + "\nUsers after transactions: " + ANSI_RESET);
        System.out.println("User: " + userOne.getName() + " --> with the identifier: " + userOne.getIdentifier()
                + " --> with the balance of: " + userOne.getBalance());
        System.out.println("User: " + userTwo.getName() + " --> with the identifier: " + userTwo.getIdentifier()
                + " --> with the balance of: " + userTwo.getBalance());

        System.out.println("\nTrying invalid transactions: ");
        Transaction deb_inv = new Transaction(userOne, userTwo, Category.debit, -5);
        Transaction cr_inv = new Transaction(userTwo, userOne, Category.credit, 5);
        System.out.println("transfer amount: " + deb_inv.getTransferAmount());
        System.out.println("transfer amount: " + cr_inv.getTransferAmount());

    }
}
