import javax.swing.*;
import java.io.*;
import java.util.TreeMap;
import java.util.Map;

public class Program {

    static TreeMap<String, Integer> mapFileOne = new TreeMap<>();
    static TreeMap<String, Integer> mapFileTwo = new TreeMap<>();
    static TreeMap<String, Integer> dictionary = new TreeMap<>();

    public static void readFromFile(BufferedReader fileToRead, TreeMap<String, Integer> map) {

        String buffer;
        try {
            while ((buffer = fileToRead.readLine()) != null) {

                String[] lineToSplit = buffer.split("\\s+");
                for (int i = 0; i < lineToSplit.length; ++i) {
                    if (dictionary.get(lineToSplit[i]) != null) {
                        dictionary.put(lineToSplit[i], 1);
                    }
                    if (map.get(lineToSplit[i]) != null) {
                        int count = map.get(lineToSplit[i]);
                        map.put(lineToSplit[i], ++count);
                    } else {
                        map.put(lineToSplit[i], 1);
                    }
                }
            }

        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String args[]) {
        try {
            FileReader readerFileOne = new FileReader(args[0]);
            BufferedReader inStreamOne = new BufferedReader(readerFileOne);
            FileReader readerFileTwo = new FileReader(args[1]);
            BufferedReader inStreamTwo = new BufferedReader(readerFileTwo);
            readFromFile(inStreamOne, mapFileOne);
            readFromFile(inStreamTwo, mapFileTwo);

        } catch (FileNotFoundException fe) {
            System.out.println("File not found");
        }

        for (Map.Entry<String, Integer> entry : mapFileOne.entrySet()) {
            System.out.println("Key: " + entry.getKey() + " , Value: " + entry.getValue());
        }
        System.out.println("------------//------------");
        for (Map.Entry<String, Integer> entry1 : mapFileTwo.entrySet()) {
            System.out.println("Key: " + entry1.getKey() + " , Value: " + entry1.getValue());
        }

    }
}
