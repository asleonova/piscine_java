import java.util.Random;

public class Program {

    private static int sumOfThreads;


    static class Multithreading {
        int threadsCount;
        int sum = 0;
        int[] arr;

        public Multithreading(int threadsCount) {
            this.threadsCount = threadsCount;
        }


        public void generateArray(int arrSize) {
            Random rd = new Random();
            arr = new int[arrSize];
            for (int i = 0; i < arr.length; i++) {
                arr[i] = rd.nextInt(1000);
                sum += arr[i];
            }
        }

        public int getSum() {
            return sum;
        }

        public int[] getArr() {
            return arr;
        }
    }

    static class RealMultuthreading extends Thread {

        int id;
        int first;
        int last;
        int[] arr;

        public RealMultuthreading(int id, int first, int last, int[] arr) {
            this.id = id;
            this.first = first;
            this.last = last;
            this.arr = arr;
        }

        public void run() {
            int i = first, j = last;
            int sum = 0;
            for (; i < j; ++i) {
                sum += arr[i];
            }
            sumOfThreads += sum;
            System.out.println(
                    "Thread " + ++id + ": from " + first + " to " + (last - 1) + " sum is " + sum);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        int arrSize = 0;
        if (args[0].startsWith("--arraySize=")) {
            try {
                arrSize = Integer.parseInt(args[0].substring(12, args[0].length()));
                if (arrSize < 1) {
                    System.out.println("error: number should be positive");
                    System.exit(-1);
                }
            } catch (NumberFormatException e) {
                e.getLocalizedMessage();
            }
        } else {
            System.out.println("Error. Usage: [--arraySize=13 --threadsCount=3]");
            System.exit(-1);
        }
        int threadsCount = 0;
        if (args[1].startsWith("--threadsCount=")) {
            try {
                threadsCount = Integer.parseInt(args[1].substring(15, args[1].length()));
                if (threadsCount < 1) {
                    System.out.println("error: number should be positive");
                    System.exit(-1);
                }
            } catch (NumberFormatException e) {
                e.getLocalizedMessage();
            }
        } else {
            System.out.println("Error. Usage: [--arraySize=13 --threadsCount=3]");
            System.exit(-1);
        }
        Multithreading object = new Multithreading(threadsCount);
        object.generateArray(arrSize);
        System.out.println("Sum: " + object.getSum());

        int arr[] = object.getArr();

        int modulo = arrSize % threadsCount;
        int delimiter = arrSize / threadsCount;
        if (modulo != 0) {
            int i = 0, j = 0;
            for (; j < threadsCount - 1; i += delimiter, ++j) {
                RealMultuthreading mt = new RealMultuthreading(j, i, i + delimiter, arr);
                mt.start();
                mt.join();
            }
            RealMultuthreading mt = new RealMultuthreading(j, i, arrSize, arr);
            mt.start();
            mt.join();
        } else {

            for (int i = 0, j = 0; j < threadsCount; i += delimiter, ++j) {
                RealMultuthreading mt = new RealMultuthreading(j, i, i + delimiter, arr);
                mt.start();
                mt.join();
            }
        }
        System.out.println("Sum by threads: " + sumOfThreads);
    }

}
