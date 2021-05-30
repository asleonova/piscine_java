import java.io.*;
import java.util.Scanner;

public class Program {

    int _num;

    public Program(int num) {
        this._num = num;
    }

    public static void main(String args[])
    {
        // for (String s: args) {
        //     System.out.println(s);
        Scanner userInput = new Scanner(System.in);
        String data = userInput.nextLine();
        System.out.println(data);
    }
//}
}