import java.io. *;
import java.util.Scanner;

public class Program {

    int _num = 0;

    public Program(int num) {
        this._num = num;
        checkIfPrime();
    }

    public int sumOfDigits()
    {
        int res = 0'
        int remainder = 0;
        while (_num > 0)
        {
            remainder = _num % 10;
            res = res + remainder;
            _num = _num / 10;
        }
        return res;
    }

    public boolean checkIfPrime() {
        
        int num = sumOfDigits();
        for (int i = 2; i * i <= num; ++i) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
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