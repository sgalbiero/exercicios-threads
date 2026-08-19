// Source code is decompiled from a .class file using FernFlower decompiler (from Intellij IDEA).
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Exercicio11 {
   public Exercicio11() {
   }

   public static void main(String[] var0) throws Exception {
      short var1 = 1000;
      short var2 = 1000;
      int[][] var3 = new int[var1][var2];

      for(int var4 = 0; var4 < var1; ++var4) {
         for(int var5 = 0; var5 < var2; ++var5) {
            var3[var4][var5] = 1;
         }
      }

      byte var13 = 4;
      ExecutorService var14 = Executors.newFixedThreadPool(var13);
      long var6 = 0L;
      int var8 = var1 / var13;
      ArrayList var9 = new ArrayList();

      for(int var10 = 0; var10 < var13; ++var10) {
         int var11 = var10 * var8;
         int var12 = var10 == var13 - 1 ? var1 : var11 + var8;
         var9.add(var14.submit(() -> {
            long var4 = 0L;

            for(int var6 = var11; var6 < var12; ++var6) {
               for(int var7 = 0; var7 < var2; ++var7) {
                  var4 += (long)var3[var6][var7];
               }
            }

            return var4;
         }));
      }

      for(Future var16 : var9) {
         var6 += (Long)var16.get();
      }

      var14.shutdown();
      System.out.println("Soma total da matriz: " + var6);
   }
}
