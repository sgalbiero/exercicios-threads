import java.util.concurrent.*;
import java.util.List;
import java.util.Arrays;

public class Exercicio16 {
    public static void main(String[] args) {
        List<String> sites = Arrays.asList(
            "https://www.google.com",
            "https://www.github.com",
            "https://www.stackoverflow.com",
            "https://www.wikipedia.org"
        );

        ExecutorService executor = Executors.newFixedThreadPool(sites.size());

        for (String url : sites) {
            executor.submit(() -> {
                System.out.println("Iniciando rastreamento de: " + url);
                try {
                    // Simula tempo de resposta do servidor
                    Thread.sleep((long) (Math.random() * 3000));
                    System.out.println("Rastreamento concluído com sucesso para: " + url);
                } catch (InterruptedException e) {
                    System.out.println("Rastreamento interrompido para: " + url);
                }
            });
        }

        executor.shutdown();
    }
}
