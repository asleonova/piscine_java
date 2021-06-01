import java.io. *;
import java.util.Scanner;

public class Program {


    public static void main(String args[])
    {
        int weekCount = 1;
        int 
        Scanner userInput = new Scanner(System.in);

        while (weekCount <= 18)
        {
            String s = userInput.next();
            if ("Week".equals(s) == false)
            {
                System.err.println("Wrong input");
                System.exit(-1);
            }
            int tmp = userInput.nextInt();
            if (weekCount != tmp)
            {
                System.err.println("Wrong num in input");
                System.exit(-1); 
            }
            userInput.nextLine();
            int num = userInput.nextInt();
            System.out.println(num);
            weekCount++;
        }
    }
}