import java.io. *;
import java.util.Scanner;

public class Program {

    int _num = 0;

    public Program(int num) {
        this._num = num;
        checkIfPrime();
    }

    private boolean checkIfPrime() {
        
        for (int i = 2; i * i <= _num; ++i) {
            if (_num % i == 0) {
                return false;
            }
        }
    }
    
    public static void main(String args[])
    {
        boolean query = true;
        int count = 0;
        while (query) {
            Scanner userInput = new Scanner(System.in);
            Program res = new Program(userInput.nextInt());
            if (res.checkIfPrime() == true)
                count++;     
            if (res._num == 42)
                query = false;       
        }
        System.out.println("Count of coffee-request - " + count);
    }
}