import java.io. *;
import java.util.Scanner;

public class Program {


    public static void main(String args[])
    {
        Scanner userInput = new Scanner(System.in);
        String s = userInput.next();
        if ("Week".equals(s) == false)
        {
            System.err.println("Wrong input");
            System.exit(-1);
        }

    }
}