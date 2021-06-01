import java.io.*;

public class Program {
    int _num;

    public Program(int num) {
        this._num = num;
    }

    public int calculate() {

        int res = 0;
        int remainder = 0;

        while (_num > 0)
        {
            remainder = _num % 10;
            res = res + remainder;
            _num = _num / 10;
        }
        return res;
    }

public static void main(String args[])
{
    Program res = new Program(479598);
    System.out.println(res.calculate());    
}
}