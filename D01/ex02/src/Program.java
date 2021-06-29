
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
        System.out.println(ANSI_PURPLE + "RETRIEVE NUMBER OF USERS: " + ANSI_RESET);
        System.out.println(ANSI_PURPLE + "---------*************---------" + ANSI_RESET);
        System.out.println("Num of users: " + uList.RetrieveNumberOfUsers());

        System.out.println(ANSI_PURPLE + "---------*************---------" + ANSI_RESET);
        System.out.println(ANSI_PURPLE + "RETRIEVE USER BY INDEX TEST: " + ANSI_RESET);
        System.out.println(ANSI_PURPLE + "---------*************---------" + ANSI_RESET);
        User user = uList.RetrieveUserByIndex(5);
        System.out.println(ANSI_GREEN + "Retrieving user with the index of 5: " + ANSI_RESET);
        System.out.println("User : " + user.getName() + "\nwith the identifier: " + user.getIdentifier()
                + "\nwith the balance of: " + user.getBalance());
        System.out.println(ANSI_GREEN + "Retrieving user with the index of -1: " + ANSI_RESET);
        System.out.println("User : " + user.getName() + "\nwith the identifier: " + user.getIdentifier()
                + "\nwith the balance of: " + user.getBalance());

        System.out.println(ANSI_PURPLE + "---------*************---------" + ANSI_RESET);
        System.out.println(ANSI_PURPLE + "RETRIEVE USER BY ID TEST: " + ANSI_RESET);
        System.out.println(ANSI_PURPLE + "---------*************---------" + ANSI_RESET);

        for (int i = 0; i < 13; ++i) {
            user = uList.RetrieveUserById(i);
            if (user != null) {
                System.out.println("User: " + user.getName() + "\nwith the identifier: " + user.getIdentifier()
                        + "\nwith the balance of: " + user.getBalance());
            }

        }
    }
}
