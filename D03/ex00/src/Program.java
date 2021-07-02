class ThreadEgg extends Thread {

    private int count;

    public ThreadEgg(int count) {
        this.count = count;
    }

    public void run() {
        for (int i = 0; i < count; ++i) {
            System.out.println("Egg");
        }
    }
}

class ThreadHen extends Thread {

    private int count;

    public ThreadHen(int count) {
        this.count = count;
    }

    public void run() {
        for (int i = 10; i < count; ++i) {
            System.out.println("Hen");
        }
    }
}

public class Program {
    public static void main(String[] args) throws InterruptedException {

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
        ThreadHen threadHen = new ThreadHen(count);
        ThreadEgg threadEgg = new ThreadEgg(count);
        threadHen.start();
        threadEgg.start();
        threadEgg.join();
        threadHen.join();

        for (int i = 0; i < count; ++i) {
            System.out.println("Human");
        }

    }
}
