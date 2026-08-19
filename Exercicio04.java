public class Exercicio04 {
    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                System.out.println("Mensagem da Thread 1");
                try { Thread.sleep(500); } catch (InterruptedException e) {}
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                System.out.println("Mensagem da Thread 2");
                try { Thread.sleep(500); } catch (InterruptedException e) {}
            }
        });

        thread1.start();
        thread2.start();
    }
}
