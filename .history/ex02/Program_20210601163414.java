import java.io. *;
import java.util.Scanner;

public class Program {

    int _num, _numOfSteps = 0;
    boolean _isPrime = false;

    public Program(int num) {
        this._num = num;
        checkIfPrime();
    }

    private void checkIfPrime() {
        
        if (_num == 2) {
            _isPrime = false;
            _numOfSteps = 1;
        }
        else {
            _numOfSteps = 1;
            for (int i = 2; i * i <= _num; ++i) {
                if (_num % i == 0) {
                    _isPrime = false;
                    return ;
                }
                _numOfSteps++;
            }
            _isPrime = true;
        }
    }

    public boolean printResult() {

        if (_num <= 1) {
            System.out.println("Illegal Argument");
            return false;
        }
        else if (_isPrime == false) {
            System.out.println("false " + _numOfSteps);
            return true;
        }
        else {
            System.out.println("true " + _numOfSteps);
            return true;
        }

    }

    public static void main(String args[])
    {
        boolean query = true;
        while (query) {
            Scanner userInput = new Scanner(System.in);
            Program res = new Program(userInput.nextInt());

            if (res.printResult() == false)
                query = false;

        }
    }
}