import java.util.LinkedList;

class Producer {

    int size = 0;

    public void egg(int count) throws InterruptedException {
        while (count > 0) {
            synchronized (this) {
                if (size == 1)
                    wait();
                System.out.println("Egg");
                size = 1;
                notify();
                count--;
                Thread.sleep(300);
            }
        }
    }

    public void hen(int count) throws InterruptedException {
        while (count > 0) {
            synchronized (this) {
                if ( size == 0)
                    wait();
                System.out.println("Hen");
                notify();
                size = 0;
                count--;
                Thread.sleep(300);
            }
        }
    }
}

public class Program {
    public static void main(String[] args)
            throws InterruptedException {

        int count = 0;
        if (args[0].startsWith("--count=")) {
            try {
                count = Integer.parseInt(args[0].substring(8, args[0].length()));
                if (count < 1) {
                    System.out.println("error: number should be positive");
                    System.exit(-1);
                }
            } catch (NumberFormatException e) {
                e.getLocalizedMessage();
            }
        } else {
            System.out.println("Error. Usage: [--count=number, where 'number' is the int > 0]");
            System.exit(-1);
        }

        final Producer pc = new Producer();

        int finalCount = count;
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    pc.egg(finalCount);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    pc.hen(finalCount);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();
    }
}