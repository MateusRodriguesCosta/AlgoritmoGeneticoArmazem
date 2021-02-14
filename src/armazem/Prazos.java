package armazem;

import java.util.Arrays;
import java.util.Random;

public class Prazos {

    private static int[][] prazos = {{2, 5, 3, 12, 5},
    {1, 5, 8, 6, 5},
    {3, 2, 18, 7, 3},
    {4, 25, 22, 5, 3}};

    public Prazos() {

    }

    public int[][] geraPrazos(int matrizPrazos[][]) {
        Random generator1 = new Random(), generator2 = new Random();

        int[][] novaPrazos = new int[4][5];

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {
                int posicao01 = generator1.nextInt(4);
                int posicao02 = generator2.nextInt(5);
                while (novaPrazos[posicao01][posicao02] != 0) {
                    posicao01 = generator1.nextInt(4);
                    posicao02 = generator2.nextInt(5);
                }
                novaPrazos[posicao01][posicao02] = prazos[i][j];
            }
            System.out.println(Arrays.toString(novaPrazos[i]));
        }

        return novaPrazos;
    }

    public int retornaResultado(int matriz[][]) {
        int somatotal = 0;
        int soma = 0;

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {
                soma = soma + matriz[i][j];
            }

            somatotal += soma;

        }
        System.out.println(somatotal);
        for (int i = 0; i < 4; i++) {
            System.out.println(Arrays.toString(matriz[i]));
        }
        return somatotal;
    }

    public int[][] hillClimb(int prazo[][]) {
        int[][] matrizAtual = prazo;
        int[][] matrizNova = geraPrazos(prazo);
        
        int solucaoNova = retornaResultado(matrizNova);
        int solucaoAtual = retornaResultado(matrizAtual);
        while (solucaoAtual <= solucaoNova) {
            System.out.println("solução atual " + solucaoAtual + " solução nova " + solucaoNova);
            solucaoAtual = solucaoNova;
            matrizAtual = matrizNova;
            matrizNova = geraPrazos(matrizAtual);
            solucaoNova = retornaResultado(matrizNova);

        }
        System.out.println("Solução atual " + solucaoAtual + " Solução nova " + solucaoNova);
        System.out.println("Resultado " + solucaoAtual);
        for (int i = 0; i < 4; i++) {
            System.out.println(Arrays.toString(matrizAtual[i]));
        }
        return matrizAtual;
    }

    public static void main(String[] args) {
        Prazos obj = new Prazos();

        obj.hillClimb(prazos);

    }
}
