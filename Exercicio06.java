public class Exercicio06 {
    private static final Object lock = new Object();
    private static int currentThread = 1;

    public static void main(String[] args) {
        Thread threadA = new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                synchronized (lock) {
                    while (currentThread != 1) {
                        try { lock.wait(); } catch (InterruptedException e) {}
                    }
                    System.out.println("Thread A imprime " + i);
                    currentThread = 2;
                    lock.notify();
                }
            }
        });

        Thread threadB = new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                synchronized (lock) {
                    while (currentThread != 2) {
                        try { lock.wait(); } catch (InterruptedException e) {}
                    }
                    System.out.println("Thread B imprime " + i);
                    currentThread = 1;
                    lock.notify();
                }
            }
        });

        threadA.start();
        threadB.start();
    }
}
