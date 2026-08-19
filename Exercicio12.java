import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Exercicio12 {
    public static void main(String[] args) {
        // Pool com 3 threads ativas simultaneamente
        ExecutorService executor = Executors.newFixedThreadPool(3);

        for (int i = 1; i <= 10; i++) {
            final int tarefaId = i;
            executor.submit(() -> {
                System.out.println("Tarefa " + tarefaId + " executando em " + Thread.currentThread().getName());
                try {
                    Thread.sleep(1000); // Simula trabalho de 1s
                } catch (InterruptedException e) {}
                System.out.println("Tarefa " + tarefaId + " finalizada.");
            });
        }

        executor.shutdown();
    }
}
