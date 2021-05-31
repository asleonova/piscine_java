import java.io.*;
import java.util.Scanner;

public class Program {

    int _num, _stepToPrime = 0;
    
    public enum Prime {
        isPrime, notPrime, illegal
    }

    Prime _isPrime; 

    public Program(int num) {
        this._num = num;
        checkPrime();
    }

    private void checkPrime() {
        if (_num < 1) {
            _isPrime = Prime.illegal;
            _stepToPrime = 1;
        }
        else if (_num == 1) {
            _isPrime = Prime.notPrime;
            _stepToPrime = 1;
        }

        else if (_num == 2) {
            _isPrime = Prime.isPrime;
            _stepToPrime = 1;
        }
        else {
            _stepToPrime = 1;
            for (int i = 2; i * i < _num; i++) {
                if (_num % i == 0) {
                    _isPrime = Prime.notPrime;
                    return;
                }
                _stepToPrime++;
            }
            _isPrime = Prime.isPrime;
        }
        
    }

    public Prime printIsPrime(){
        if (_isPrime == Prime.isPrime) {
            System.out.println("true " + _stepToPrime);
            return Prime.isPrime;
        }
        else if (_isPrime == Prime.notPrime) {
            System.out.println("false " + _stepToPrime);
            return Prime.notPrime;
        }
        else {
            System.out.println("Illegal Argument");
            return Prime.illegal;
        }
    }


    public static void main(String args[])
    {
        // for (String s: args) {
        //     System.out.println(s);
        Scanner userInput = new Scanner(System.in);
        if (!userInput.hasNextInt()) {
            System.out.println("Illegal Argument");
            System.exit(-1);
        }
        Program program1 = new Program(userInput.nextInt());
        
        if ( program1.printIsPrime() == Prime.illegal)
            System.exit(-1);
    }
}