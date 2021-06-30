import java.io.*;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.HashMap;

public class Program {


    static String buffer;
    static HashMap<String, String> map = new HashMap<>();
    static OutputStream output;
    static FileInputStream input;

    // записали в мапу значения из файла PNG - key, value - коды без пробелов
    public static void readSignatures() {
        try {
            Scanner scanned = new Scanner(input);
            String line, value;

            while (scanned.hasNextLine()) {
                line = scanned.nextLine();
                value = line.substring(line.indexOf(',') + 1);
                map.put(line.substring(0, line.indexOf(',')), value.replaceAll("\\s", "")); // \\s for whitespace character
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void inputSignatureBytes() {
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

    private static boolean findSignature() {
        for (Map.Entry<String, String> pair : map.entrySet()) {
            if (buffer.lastIndexOf(pair.getValue()) != -1) {
                buffer = pair.getKey();
                return true;
            }
        }
        return false;
    }

    private static final String HEXES = "0123456789ABCDEF";

    public static String getHex(byte[] raw) {
        final StringBuilder hex = new StringBuilder(2 * raw.length);
        for (final byte b : raw) {
            hex.append(HEXES.charAt((b & 0xF0) >> 4)).append(HEXES.charAt((b & 0x0F)));
        }
        return hex.toString();
    }

    public static void main(String args[]) {
        inputSignatureBytes();
        try {
            output = new FileOutputStream("result.txt");
            input = new FileInputStream("/Users/dbliss/school21/piscine_java/D02/ex00/src/signatures.txt");
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("PROCESSED");
        readSignatures();
        boolean res = findSignature();
        System.out.println("res: " + res);
    }
}
