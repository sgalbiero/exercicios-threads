import java.util.concurrent.*;

public class Exercicio15 {
    public static boolean isPrimo(int numero) {
        if (numero <= 1) return false;
        for (int i = 2; i <= Math.sqrt(numero); i++) {
            if (numero % i == 0) return false;
        }
        return true;
    }

    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws Exception {
        int limite = 10000;
        int numThreads = 4;
        ExecutorService executor = Executors.newFixedThreadPool(numThreads);
        long somaPrimos = 0;
        
        int range = limite / numThreads;
        Future<Long>[] resultados = new Future[numThreads];

        for (int t = 0; t < numThreads; t++) {
            final int inicio = t * range + 1;
            final int fim = (t == numThreads - 1) ? limite : (t + 1) * range;

            resultados[t] = executor.submit(() -> {
                long somaLocal = 0;
                for (int i = inicio; i <= fim; i++) {
                    if (isPrimo(i)) {
                        somaLocal += i;
                    }
                }
                return somaLocal;
            });
        }

        for (int t = 0; t < numThreads; t++) {
            somaPrimos += resultados[t].get();
        }

        executor.shutdown();
        System.out.println("Soma dos primos até " + limite + ": " + somaPrimos);
    }
}
