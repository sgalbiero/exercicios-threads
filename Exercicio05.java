public class Exercicio05 {

    static class MinhaThread extends Thread {
        @Override
        public void run() {
            System.out.println("Thread iniciada estendendo a classe Thread");
        }
    }

    static class MeuRunnable implements Runnable {
        @Override
        public void run() {
            System.out.println("Thread iniciada usando a interface Runnable");
        }
    }

    public static void main(String[] args) {
        // Iniciando com classe Thread
        MinhaThread thread1 = new MinhaThread();
        thread1.start();

        // Iniciando com interface Runnable
        Thread thread2 = new Thread(new MeuRunnable());
        thread2.start();
    }
}
