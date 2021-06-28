public class Program {

    public static void main(String args[])
    {
        User UserOne = new User("UserOne", 6);
        User UserTwo = new User("User Two", 10);
        User UserThree = new User("User Three", 3);
        User UserFour = new User("User Four", 4);
        User UserFive = new User("UserOne", 6);
        User UserSix = new User("User Two", 10);
        User UserSeven = new User("User Three", 3);
        User UserEight = new User("User Four", 4);
        User UserNine = new User("UserOne", 6);
        User UserTen = new User("User Two", 10);
        User UserEleven = new User("User Three", 3);
        User UserTwelve = new User("User Four", 4);

        UsersArrayList arr = new UsersArrayList();
        arr.addUser(UserOne);
        arr.addUser(UserTwo);
        arr.addUser(UserThree);
        arr.addUser(UserFour);
        arr.addUser(UserFive);
        arr.addUser(UserSix);
        arr.addUser(UserSeven);
        arr.addUser(UserEight);
        arr.addUser(UserNine);
        arr.addUser(UserTen);
        arr.addUser(UserEleven);
        arr.addUser(UserTwelve);

    }
}
