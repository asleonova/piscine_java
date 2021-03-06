import java.io.*;
import java.util.TreeMap;
import java.util.Map;
import java.util.Iterator;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class Program {

    static TreeMap<String, Integer> mapFileOne = new TreeMap<>();
    static TreeMap<String, Integer> mapFileTwo = new TreeMap<>();
    static TreeMap<String, Integer> dictionary = new TreeMap<>();
    static int[] vectorFileOne;
    static int[] vectorFileTwo;

    public static void readFromFile(BufferedReader fileToRead, TreeMap<String, Integer> map) {

        String buffer;
        try {
            while ((buffer = fileToRead.readLine()) != null) {

                String[] lineToSplit = buffer.split("\\s+");
                for (int i = 0; i < lineToSplit.length; ++i) {
                    if (dictionary.get(lineToSplit[i]) == null) {
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

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void createVectors() {
        vectorFileOne = new int[dictionary.size()];
        vectorFileTwo = new int[dictionary.size()];

        Iterator<Map.Entry<String, Integer>> it = dictionary.entrySet().iterator();
        for (int i = 0; i < vectorFileOne.length; ++i) {
            Map.Entry<String, Integer> entry = it.next();
            if (mapFileOne.get(entry.getKey()) != null) {
                int count = mapFileOne.get(entry.getKey());
                vectorFileOne[i] = count;
            } else {
                vectorFileOne[i] = 0;
            }
            if (mapFileTwo.get(entry.getKey()) != null) {
                int count = mapFileTwo.get(entry.getKey());
                vectorFileTwo[i] = count;
            } else {
                vectorFileTwo[i] = 0;
            }
        }
    }

    public static double calculations() {
        int numerator = 0, denominatorA = 0, demominatorB = 0;
        double similarity = 0;

        for (int i = 0; i < dictionary.size(); ++i) {
            numerator += vectorFileOne[i] * vectorFileTwo[i];
            denominatorA += vectorFileOne[i] * vectorFileOne[i];
            demominatorB += vectorFileTwo[i] * vectorFileTwo[i];
        }
        similarity = numerator / (Math.sqrt(denominatorA) * Math.sqrt(demominatorB));
        if (Double.isNaN(similarity)) {
            similarity = 0;
        }
        return similarity;
    }

    public static void main(String args[]) {

        File file1 = new File(args[0]);
        File file2 = new File(args[1]);
        if (file1.length() == 0 && file2.length() == 0)
            System.out.println("Similarity = 1");
        else if (file1.length() == 0 || file2.length() == 0)
            System.out.println("Similarity = 0");
        else {
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
            createVectors();

            double res = calculations();
            BigDecimal bdDown = new BigDecimal(res).setScale(2, RoundingMode.DOWN);
            System.out.println("Similarity = " + bdDown.doubleValue());
        }

    }
}
