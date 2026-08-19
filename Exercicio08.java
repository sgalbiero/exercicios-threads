public class Exercicio08 {
    static class ContadorThread extends Thread {
        private volatile boolean pausada = false;

        public void pausar() {
            pausada = true;
        }

        public synchronized void retomar() {
            pausada = false;
            notify();
        }

        @Override
        public void run() {
            for (int i = 1; i <= 100; i++) {
                synchronized (this) {
                    while (pausada) {
                        try {
                            wait();
                        } catch (InterruptedException e) {
                            break;
                        }
                    }
                }
                System.out.println("Contagem: " + i);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    break;
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ContadorThread thread = new ContadorThread();
        thread.start();

        Thread.sleep(2000); // Deixa rodar até o 20
        System.out.println("--- Pausando ---");
        thread.pausar();

        Thread.sleep(3000); // Fica 3 segundos parada
        System.out.println("--- Retomando ---");
        thread.retomar();
    }
}
