public class Exercicio14 {
    public static void main(String[] args) throws InterruptedException {
        int[][] matrizA = { {1, 2}, {3, 4} };
        int[][] matrizB = { {2, 0}, {1, 2} };
        int linhasA = matrizA.length;
        int colunasB = matrizB[0].length;
        int[][] resultado = new int[linhasA][colunasB];

        Thread[] threads = new Thread[linhasA * colunasB];
        int count = 0;

        for (int i = 0; i < linhasA; i++) {
            for (int j = 0; j < colunasB; j++) {
                final int linha = i;
                final int coluna = j;
                threads[count] = new Thread(() -> {
                    int soma = 0;
                    for (int k = 0; k < matrizA[0].length; k++) {
                        soma += matrizA[linha][k] * matrizB[k][coluna];
                    }
                    resultado[linha][coluna] = soma;
                });
                threads[count].start();
                count++;
            }
        }

        for (Thread t : threads) {
            t.join();
        }

        System.out.println("Resultado da multiplicação:");
        for (int i = 0; i < linhasA; i++) {
            for (int j = 0; j < colunasB; j++) {
                System.out.print(resultado[i][j] + " ");
            }
            System.out.println();
        }
    }
}
