import java.util.LinkedList;
import java.util.Queue;

public class Exercicio10 {
    static class FilaImpressao {
        private Queue<String> fila = new LinkedList<>();

        public synchronized void adicionarDocumento(String documento) {
            fila.add(documento);
            System.out.println("Adicionado à fila: " + documento);
            notify();
        }

        public synchronized void processarDocumentos() throws InterruptedException {
            while (true) {
                while (fila.isEmpty()) {
                    wait();
                }
                String doc = fila.poll();
                System.out.println("Imprimindo: " + doc);
                Thread.sleep(1000); // Simula tempo de impressão
            }
        }
    }

    public static void main(String[] args) {
        FilaImpressao fila = new FilaImpressao();

        // Thread para processar a fila (Impressora)
        Thread impressora = new Thread(() -> {
            try {
                fila.processarDocumentos();
            } catch (InterruptedException e) {}
        });
        impressora.setDaemon(true); // Termina quando o main terminar
        impressora.start();

        // Threads enviando documentos (Usuários)
        for (int i = 1; i <= 3; i++) {
            final int usuario = i;
            new Thread(() -> {
                for (int j = 1; j <= 2; j++) {
                    fila.adicionarDocumento("Doc " + j + " do Usuario " + usuario);
                    try { Thread.sleep(500); } catch (InterruptedException e) {}
                }
            }).start();
        }
    }
}
