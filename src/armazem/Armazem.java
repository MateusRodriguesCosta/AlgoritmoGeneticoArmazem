package armazem;

import java.util.Arrays;
import java.util.Random;
public class Armazem {

    private static int[][] armazem ={{2, 5, 3, 12, 5},
    {1, 5, 8, 6, 5},
    {3, 2, 18, 7, 3},
    {4, 25, 22, 5, 3},
    {10, 15, 20, 5, 3},
    {10, 11, 20, 7, 3},
    {10, 4, 20, 9, 3},
    {10, 8, 19, 5, 3}};

    public Armazem() {

    }

    public int[][] geraSolucao(int matrizPrazos[][]) {
        Random generator1 = new Random(), generator2 = new Random();

        int[][] novaPrazos = new int[8][5];

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {
                int posicao01 = generator1.nextInt(4);
                int posicao02 = generator2.nextInt(5);
                while (novaPrazos[posicao01][posicao02] != 0) {
                    posicao01 = generator1.nextInt(4);
                    posicao02 = generator2.nextInt(5);
                }
                novaPrazos[posicao01][posicao02] = armazem[i][j];
                novaPrazos[posicao01 + 4][posicao02] = armazem[i + 4][j];
            }
        }

        return novaPrazos;
    }

    public int retornaResultado(int matriz[][]) {
        int somatotal = 0;
        int soma1 = 0;
        int soma2 = 0;

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {
                soma1 = soma1 + matriz[i][j];
                soma2 = soma2 + matriz[i + 4][j];
            }

            somatotal += soma1 + soma2;

        }

        /* System.out.println(somatotal);
         for (int i = 4; i < 8; i++) {
         System.out.println(Arrays.toString(matriz[i]));
         }*/
        return somatotal;
    }

    public int[][] hillClimb(int matrizArmazem[][]) {
        int[][] matrizAtual = matrizArmazem;
        int[][] matrizNova = geraSolucao(matrizArmazem);
        int solucaoNova = retornaResultado(matrizNova);
        int solucaoAtual = retornaResultado(matrizAtual);
        while (solucaoAtual <= solucaoNova) {
            System.out.println("solução atual " + solucaoAtual + " solução nova " + solucaoNova);
            solucaoAtual = solucaoNova;
            matrizAtual = matrizNova;
            matrizNova = geraSolucao(matrizAtual);
            solucaoNova = retornaResultado(matrizNova);

        }
        System.out.println("Solução atual " + solucaoAtual + " Solução nova " + solucaoNova);
        System.out.println("Resultado " + solucaoAtual);
        for (int i = 4; i < 8; i++) {
            System.out.println(Arrays.toString(matrizAtual[i]));
        }
        return matrizAtual;
    }

    public int[][] hillClimb2(int matrizArmazem[][]) {
        int teste = 0;
        int testeMax = 5;
        int[][] matrizAtual = matrizArmazem;
        int[][] matrizNova = geraSolucao(matrizArmazem);
        int solucaoNova = retornaResultado(matrizNova);
        int solucaoAtual = retornaResultado(matrizAtual);
        while (teste <= testeMax) {
            if (solucaoAtual <= solucaoNova) {
                System.out.println("solução atual " + solucaoAtual + " solução nova " + solucaoNova);
                solucaoAtual = solucaoNova;
                matrizAtual = matrizNova;
                matrizNova = geraSolucao(matrizAtual);
                solucaoNova = retornaResultado(matrizNova);
                teste = 0;
            } else {
                if (solucaoAtual > solucaoNova) {
                    System.out.println("solução atual " + solucaoAtual + " solução nova " + solucaoNova);
                    matrizNova = geraSolucao(matrizAtual);
                    solucaoNova = retornaResultado(matrizNova);   
                    teste++;
                }
            }
        }
        System.out.println("Solução atual " + solucaoAtual + " Solução nova " + solucaoNova);
        System.out.println("Resultado " + solucaoAtual);
        for (int i = 4; i < 8; i++) {
            System.out.println(Arrays.toString(matrizAtual[i]));
        }
        return matrizAtual;
    }
    public int [][] temperaSimulada(int matrizArmazem[][]){
        int[][] matrizAtual = matrizArmazem;
        int solucaoAtual = retornaResultado(matrizAtual);
        int[][] matrizNova = geraSolucao(matrizArmazem);
        int solucaoNova = retornaResultado(matrizNova);
        int [] escalonamento = new int [10000];
        for(int i =0; i<10000; i++){
        escalonamento[i]=9999-i;}
        int t = 0;
        while(escalonamento[t]!=0){
        int diferenca = solucaoAtual - solucaoNova;
        if(diferenca<=0){
            System.out.println("Solução atual: " + solucaoAtual + " solução nova: " + solucaoNova);
            solucaoAtual = solucaoNova;
            matrizAtual = matrizNova;
            matrizNova = geraSolucao(matrizAtual);
            solucaoNova = retornaResultado(matrizNova);     
        }
        else{
       int temp = escalonamento[t];
       Random generator3 = new Random();
       int Ale = generator3.nextInt(10000);
       //System.out.println(Ale*0.01);
       double Aux = Math.exp(-diferenca/temp);
       //System.out.println(Aux);
        if((Ale*.0001)<Aux){
            System.out.println("trocou solução: " + solucaoAtual + " por solução: " + solucaoNova);
            solucaoAtual = solucaoNova;
            matrizAtual = matrizNova;
            matrizNova = geraSolucao(matrizAtual);
            solucaoNova = retornaResultado(matrizNova);          
        }
        else{
            matrizNova = geraSolucao(matrizAtual);
            solucaoNova = retornaResultado(matrizNova);}
        }
        t++;        
        }
        System.out.println("Solução atual: " + solucaoAtual + " Solução nova: " + solucaoNova);
        System.out.println("Resultado " + solucaoAtual);
        for (int i = 4; i < 8; i++) {
            System.out.println(Arrays.toString(matrizAtual[i]));}
    return matrizAtual;
    }

    public static void main(String[] args) {
        Armazem obj = new Armazem();

        obj.temperaSimulada(armazem);

    }
}
