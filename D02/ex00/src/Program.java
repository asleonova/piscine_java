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
    static boolean wrongFile = false;

    public static void readSignatures() {
        try {
            Scanner scanned = new Scanner(input);
            String line, value;

            while (scanned.hasNextLine()) {
                line = scanned.nextLine();
                value = line.substring(line.indexOf(',') + 1);
                map.put(line.substring(0, line.indexOf(',')), value.replaceAll("\\s", ""));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void inputSignatureBytes() {
        Scanner input = new Scanner(System.in);
        String line = input.nextLine();
        if (line.equals("42"))
            System.exit(0);
        try {
            FileInputStream sigfis = new FileInputStream(line);
            byte[] sigToVerify = new byte[8];
            sigfis.read(sigToVerify);
            sigfis.close();
            buffer = getHex(sigToVerify);

        } catch (Exception e) {
            System.out.println("File not found, enter valid path: ");
            wrongFile = true;
        }
    }

    private static boolean findSignature() {
        for (Map.Entry<String, String> pair : map.entrySet()) {
            if (buffer.lastIndexOf(pair.getValue()) != -1) {
                buffer = pair.getKey();
                System.out.println("PROCESSED");
                return true;
            }
        }
        System.out.println("UNDEFINED");
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

    public static void result() {
        boolean res = findSignature();
        if (res == true) {

            try {
                output.write(buffer.getBytes());
                output.write('\n');
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String args[]) {

        try {
            output = new FileOutputStream("result.txt");
            input = new FileInputStream("signatures.txt");
        } catch (Exception e) {
            e.printStackTrace();
        }
        while (true) {
            inputSignatureBytes();
            if (wrongFile == false) {
                readSignatures();
                result();
            }
            wrongFile = false;
        }

    }
}
