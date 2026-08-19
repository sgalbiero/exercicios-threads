public class Exercicio07 {
    private static int contador = 0;

    public static synchronized void incrementar() {
        contador++;
    }

    public static void main(String[] args) throws InterruptedException {
        Runnable tarefa = () -> {
            for (int i = 0; i < 1000; i++) {
                incrementar();
            }
        };

        Thread thread1 = new Thread(tarefa);
        Thread thread2 = new Thread(tarefa);

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println("Valor final do contador: " + contador);
    }
}
