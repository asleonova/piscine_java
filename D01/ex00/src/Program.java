public class Program {

    public static void main(String args[])
    {
        System.out.println("User with negative balance (should set to 0");
        User UserOne = new User(4, "UserOne", -5);
        System.out.println("balance: " + UserOne.getBalance());
        System.out.println("Creating second user with the balance of 10 ");
        User UserTwo = new User(1, "User Two", 10);
        System.out.println("balance: " + UserTwo.getBalance());
        System.out.println("performing transactions: ");
        Transaction deb = new Transaction(UserOne.getName(), UserTwo.getName(), Category.debit, 5);
        Transaction cr = new Transaction(UserTwo.getName(), UserOne.getName(), Category.credit, -5);
        System.out.println("transfer amount: " + deb.getTransferAmount());
        System.out.println("transfer amount: " + cr.getTransferAmount());
        System.out.println("trying invalid transactions: ");
        Transaction deb_inv = new Transaction(UserOne.getName(), UserTwo.getName(), Category.debit, -5);
        Transaction cr_inv = new Transaction(UserTwo.getName(), UserOne.getName(), Category.credit, 5);
        System.out.println("transfer amount: " + deb_inv.getTransferAmount());
        System.out.println("transfer amount: " + cr_inv.getTransferAmount());

    }
}
