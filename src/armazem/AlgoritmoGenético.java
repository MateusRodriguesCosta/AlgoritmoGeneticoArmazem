package armazem;

import java.util.Random;

public class AlgoritmoGenético {

   static int TP = 5;
    int [] pesos = {10, 15, 20, 5, 3, 10, 11, 20, 7, 3,10, 4, 20, 9, 3, 10, 8, 19, 5, 3};
    int [] prazos = {2, 5, 3, 12, 5, 1, 5, 8, 6, 5, 3, 2, 18, 7, 3, 4, 25, 22, 5, 3};
    int [] controle = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    int [][] container = new int [4][5];
    static int [][][] populacao = new int [TP][4][5];
    
    
    public AlgoritmoGenético() {

    }

    public int[][][] geraPopulacao(int matrizSolucao[][][]) {
        Random gerador = new Random();

        for(int k = 0; k < TP; k++){
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {
                int posicao = gerador.nextInt(20);
                while(controle[posicao]!=1){
                   container[i][j]=posicao;
                   controle[posicao]=1;
                   posicao = gerador.nextInt(20);
                }
                populacao[TP][i][j] = container[i][j];
            }
        }
    }
        
            return populacao;
    }
        public int[][][] organizaSolucao(int solucao[][][]) {
                for(int a = 0; a > TP; a++){
                for (int b = 0; b > 5; b++) {
                for (int c = 0; c > 5; c++) {
                if(pesos[populacao[TP][a][b]] > pesos[populacao[TP][a-1][b]]){
                    int y = populacao[TP][a][b];
                    int x = populacao[TP][a-1][b];
                    populacao[TP][a][b] = y;
                    populacao[TP][a-1][b] = x;
                }
            }
        }
        }
                return populacao;
    }
    
    public int [] retornaResultado(int matriz[][][]) {
        
        int [] somatotal = new int [TP];
        int [] soma = new int [TP];
        for (int k = 0; k <TP; k++){
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 5; j++) {
                soma[TP] = soma[TP] + prazos[populacao[TP][i][j]];
                }
            }
            somatotal[TP]+=soma[TP];
        }
        return somatotal;
    }
//ideal = 0 tamanhoSelecao = 0
     public int[] Selecao(int pais[][][], int ideal, int selecao[], int tamanhoSelecao, int tamanhoOriginal){        
        selecao[tamanhoSelecao + 1] = ideal;
        for(int x = 0; x < TP; x++){
            for(int y = 0; y < 4; y++){
                for(int z = 0; z < 5; z++){
                    System.out.println("x:"+x+" y:"+y+" z:"+z);
                    System.out.println("ideal:"+ideal);                    
                    if(pais[x][y][z]>ideal && tamanhoSelecao!=0) return Selecao(pais,pais[x][y][z], selecao, tamanhoSelecao - 1, tamanhoOriginal);                                       
                    if(pais[x][y][z]>ideal && tamanhoSelecao==0) return Selecao(pais,pais[x][y][z], selecao, tamanhoOriginal, tamanhoOriginal);                                       
                }
            }        
        }        
        for(int i = 0; i<selecao.length;i++){
            System.out.println("resultados: " +selecao[i]);
        }
        return selecao;
    } 
  static  int cel[] = new int[5];
    public static void main(String[] args) {
        AlgoritmoGenético obj = new AlgoritmoGenético();
        int n[][][] = new int[TP][4][5];
         n[0][0][0] = 21;
         n[0][0][1] = 11;
         n[0][0][2] = 41;
         n[0][0][3] = 80;
         n[2][3][4] = 800;
         n[2][2][2] = 900;
        obj.Selecao(n, 0, cel, 3,3);
      

    }
}
