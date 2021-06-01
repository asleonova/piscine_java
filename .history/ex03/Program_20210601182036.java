import java.io. *;
import java.util.Scanner;

public class Program {

    public static int minNumber(int a, int b)
    {
        return (a > b) ? a : b;
    }
    public static void main(String args[])
    {
        int weekCount = 1;
        int gradesCount = 1;
        int minNum = 0;
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
            while (gradesCount <= 5)
            {
                int inputNum = userInput.nextInt();
                minNum = minNum()
                System.out.println(num);
            }
           
            weekCount++;
        }
    }
}