import java.io. *;
import java.util.Scanner;

public class Program {

    int _num = 0;
    boolean _isPrime = false;

    public Program(int num) {
        this._num = num;
        checkIfPrime();
    }

    private void checkIfPrime() {
        
        if (_num == 2) {
            _isPrime = false;
        }
        else {
            for (int i = 2; i * i <= _num; ++i) {
                if (_num % i == 0) {
                    _isPrime = false;
                    return ;
                }
            }
            _isPrime = true;
        }
    }

    public static void main(String args[])
    {
        boolean query = true;
        int count = 0;
        while (query) {
            Scanner userInput = new Scanner(System.in);
            Program res = new Program(userInput.nextInt());
            if (res._num == 42)
                query = false;
            if (res.checkIfPrime() == true && res._num != 42)
                count++;            
        }
        System.out.println("Count of coffee-request - " + count);
    }
}