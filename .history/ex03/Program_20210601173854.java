import java.io. *;
import java.util.Scanner;

public class Program {


    public static void main(String args[])
    {
        Scanner userInput = new Scanner(System.in);
        Program res = new Program(userInput.next());
        System.out.println(res);
    }
}