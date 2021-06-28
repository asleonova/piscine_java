public class Program {

    public static void main(String args[])
    {
        System.out.println("User with negative balance (should set to 0");
        User UserOne = new User("UserOne", -5);
        System.out.println("balance: " + UserOne.getBalance());
        System.out.println("user id: " + UserOne.getIdentifier());
        System.out.println("Creating second user with the balance of 10 ");
        User UserTwo = new User("User Two", 10);
        System.out.println("balance: " + UserTwo.getBalance());
        System.out.println("user id: " + UserTwo.getIdentifier());
    }
}
