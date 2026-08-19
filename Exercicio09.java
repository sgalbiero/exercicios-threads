import java.util.LinkedList;

public class Exercicio09 {
    static class Buffer {
        private LinkedList<Integer> lista = new LinkedList<>();
        private int capacidade = 5;

        public synchronized void produzir(int valor) throws InterruptedException {
            while (lista.size() == capacidade) {
                wait();
            }
            lista.add(valor);
            System.out.println("Produzido: " + valor);
            notify();
        }

        public synchronized int consumir() throws InterruptedException {
            while (lista.isEmpty()) {
                wait();
            }
            int valor = lista.removeFirst();
            System.out.println("Consumido: " + valor);
            notify();
            return valor;
        }
    }

    public static void main(String[] args) {
        Buffer buffer = new Buffer();

        Thread produtor = new Thread(() -> {
            try {
                for (int i = 1; i <= 10; i++) {
                    buffer.produzir(i);
                    Thread.sleep(500);
                }
            } catch (InterruptedException e) { }
        });

        Thread consumidor = new Thread(() -> {
            try {
                for (int i = 1; i <= 10; i++) {
                    buffer.consumir();
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) { }
        });

        produtor.start();
        consumidor.start();
    }
}
