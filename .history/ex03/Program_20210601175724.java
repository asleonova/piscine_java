import java.io. *;
import java.util.Scanner;

public class Program {


    public static void main(String args[])
    {
        int weekCount = 1;

        while (weekCount <= 18)
        {
            Scanner userInput = new Scanner(System.in);
            String s = userInput.next();
            System.out.println(s);
            weekCount++;
        }
        Scanner userInput = new Scanner(System.in);
        String s = userInput.next();
        if ("Week".equals(s) == false)
        {
            System.err.println("Wrong input");
            System.exit(-1);
        }
        
        int tmp = userInput.nextInt();
        if (weekCount != tmp)
        {
            // error ;
        }

    }
}