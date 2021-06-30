import java.io.*;
import java.util.Scanner;
import java.util.HashMap;

public class Program {


    static String buffer;
    static HashMap<String, String> map;

    static void inputSignatureBytes() {
        Scanner input = new Scanner(System.in);
        String line = input.nextLine();
        try {
            FileInputStream sigfis = new FileInputStream(line);
            byte[] sigToVerify = new byte[7];
            sigfis.read(sigToVerify);
            sigfis.close();
            buffer = getHex(sigToVerify);

            //----------Delete this-----------//

            System.out.println(buffer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static final String HEXES = "0123456789ABCDEF";

    static String getHex(byte[] raw) {
        final StringBuilder hex = new StringBuilder(2 * raw.length);
        for (final byte b : raw) {
            hex.append(HEXES.charAt((b & 0xF0) >> 4)).append(HEXES.charAt((b & 0x0F)));
        }
        return hex.toString();
    }

    public static void main(String args[]) {
        inputSignatureBytes();
    }
}
