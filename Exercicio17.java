public class Exercicio17 {
    static class ContaBancaria {
        private double saldo;

        public ContaBancaria(double saldoInicial) {
            this.saldo = saldoInicial;
        }

        public synchronized void depositar(double valor) {
            saldo += valor;
            System.out.println(Thread.currentThread().getName() + " depositou: " + valor + " | Saldo Atual: " + saldo);
        }

        public synchronized void sacar(double valor) {
            if (saldo >= valor) {
                saldo -= valor;
                System.out.println(Thread.currentThread().getName() + " sacou: " + valor + " | Saldo Atual: " + saldo);
            } else {
                System.out.println(Thread.currentThread().getName() + " tentou sacar " + valor + ", mas saldo é insuficiente! Saldo: " + saldo);
            }
        }

        public double getSaldo() {
            return saldo;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ContaBancaria conta = new ContaBancaria(1000); // Saldo inicial 1000

        Thread t1 = new Thread(() -> conta.depositar(500), "Thread-1 (Depósito)");
        Thread t2 = new Thread(() -> conta.sacar(200), "Thread-2 (Saque)");
        Thread t3 = new Thread(() -> conta.sacar(1500), "Thread-3 (Saque)");
        Thread t4 = new Thread(() -> conta.depositar(300), "Thread-4 (Depósito)");

        t1.start();
        t2.start();
        t3.start();
        t4.start();

        t1.join();
        t2.join();
        t3.join();
        t4.join();

        System.out.println("Saldo final da conta: " + conta.getSaldo());
    }
}
