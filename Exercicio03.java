public class Exercicio03 implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i <= 20; i += 2) {
            System.out.println("Par: " + i);
        }
    }

    public static void main(String[] args) {
        Thread thread = new Thread(new Exercicio03());
        thread.start();
    }
}
