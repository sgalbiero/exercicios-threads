import java.util.Arrays;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.ForkJoinPool;

public class Exercicio13 {
    static class MergeSortTask extends RecursiveAction {
        private int[] array;
        private int esquerda;
        private int direita;

        public MergeSortTask(int[] array, int esquerda, int direita) {
            this.array = array;
            this.esquerda = esquerda;
            this.direita = direita;
        }

        @Override
        protected void compute() {
            if (esquerda < direita) {
                int meio = (esquerda + direita) / 2;
                MergeSortTask tarefaEsq = new MergeSortTask(array, esquerda, meio);
                MergeSortTask tarefaDir = new MergeSortTask(array, meio + 1, direita);
                
                invokeAll(tarefaEsq, tarefaDir);
                
                merge(array, esquerda, meio, direita);
            }
        }

        private void merge(int[] array, int esq, int meio, int dir) {
            int[] temp = new int[dir - esq + 1];
            int i = esq, j = meio + 1, k = 0;
            
            while (i <= meio && j <= dir) {
                if (array[i] <= array[j]) {
                    temp[k++] = array[i++];
                } else {
                    temp[k++] = array[j++];
                }
            }
            while (i <= meio) temp[k++] = array[i++];
            while (j <= dir) temp[k++] = array[j++];
            
            System.arraycopy(temp, 0, array, esq, temp.length);
        }
    }

    @SuppressWarnings("resource")
    public static void main(String[] args) {
        int[] array = {38, 27, 43, 3, 9, 82, 10, 19, 50, 1};
        System.out.println("Original: " + Arrays.toString(array));
        
        ForkJoinPool pool = new ForkJoinPool();
        pool.invoke(new MergeSortTask(array, 0, array.length - 1));
        pool.shutdown();
        
        System.out.println("Ordenado: " + Arrays.toString(array));
    }
}
