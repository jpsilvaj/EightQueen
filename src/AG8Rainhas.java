package src;

/*
 *Engenharia da Computacao - S8
 *@autor Joao Paulo da Silva Junior
 *@autor Joao Cicero Ferreira Junior
 *@data 02/02/2012
 *@version 1.0
 * */
import java.util.Random;
import java.util.Arrays;
import java.util.ArrayList;

public class AG8Rainhas{

    private static final int TAMANHO_POPULACAO = 50;
    private static final int APTIDAO_MAX = 99;
    private static final double PORCENTAGEM_REPRODUTORES =  0.6;
    private static final int NUMERO_REPRODUTORES = (int)(TAMANHO_POPULACAO*PORCENTAGEM_REPRODUTORES);
    private Individuo[] populacao = new Individuo[TAMANHO_POPULACAO];
    private int numeroGeracao = 1;

    public AG8Rainhas(){
    
        this.initPopulacao();
        
    }
    
    public boolean resolveAG8Rainhas(Individuo[] populacao){ 
        boolean resultado = false;
        int numeroReinicio = 0;
        
        Individuo[] reprodutores = selecao();

        Individuo[] descendencia = recombinacao(reprodutores);
        descendencia = mutacao(descendencia);
        
        Individuo[] novaGeracao = substituicao(populacao, reprodutores, descendencia);
        Arrays.sort(novaGeracao);
        
        for(Individuo i : novaGeracao) {
            if(i.getAptidao() == APTIDAO_MAX ) { 
                System.out.println(i);
                System.out.println("Aptidao = "+i.getAptidao());
                System.out.println("Geração Nº: " + numeroGeracao);
                resultado = true;
                return resultado;
            }
        }
        numeroGeracao++;
       
        if(numeroGeracao > 1000){
            this.initPopulacao();
            numeroGeracao = 0;
            numeroReinicio++;
            resolveAG8Rainhas(populacao);
            
        }
        else if(numeroReinicio > 20){
            System.out.println("Individuo perfeito não encontrado");
            System.out.println("Individuo mais adaptado foi: " + novaGeracao[TAMANHO_POPULACAO-1] );
            resultado = false;
            return resultado;
        }
                
        else resolveAG8Rainhas(novaGeracao);
        return resultado;
        
    }

    /*
     *@fn initPopulacao
     *@param void
     *@brief Funcao para criação da população inicial
     */
    public void initPopulacao(){
        
        int adapt = 0;
        
        for(int i = 0; i < AG8Rainhas.TAMANHO_POPULACAO;i++){
            this.populacao[i] = new Individuo();
            adapt = aptidao(this.populacao[i]);
            this.populacao[i].setAptidao(adapt);
        }
        
    }

    /*
     *@fn aptidao
     *@param Um objeto do tipo Individuo
     *@brief Funcao de avaliacao usada para determinar 
     *
     */

    private static int aptidao(Individuo individuo){
        
       int aptidao = 99;
       int[] genoma;
       genoma = Arrays.copyOf(individuo.getGenoma(), Individuo.NUMERO_RAINHAS);
       
       for(int i=0;i<genoma.length;++i) {
            int x0 = i, y0 = genoma[i];

            int xminus = x0-1, xplus = x0+1;
            int yminus = y0-1, yplus = y0+1;
            while(xminus>=0) {
                if((yminus>=0 && genoma[xminus]==yminus) || (yplus<genoma.length && genoma[xminus]==yminus))	
                        --aptidao;

                --xminus;
                --yminus;
                ++yplus;
            }

            yminus = y0-1; yplus = y0+1;
            while(xplus<genoma.length) {
                if((yminus>=0 && genoma[xplus]==yminus) || (yplus<genoma.length && genoma[xplus]==yplus))
                        --aptidao;

                ++xplus;
                --yminus;
                ++yplus;
            }
        }
        
        for(int i = 0; i < genoma.length;i++){
            for(int j=0; j < genoma.length;j++){
                if(i!=j && genoma[i]==genoma[j]) { 
                    --aptidao;
                }
            }
        }
       
        return aptidao;
    }
    
    /*
     *@fn seleção
     *@param void
     *@brief Funcao para seleção dos indivíduos mais adaptados
     */
    private Individuo[] selecao(){
        
        Individuo tempProgenitores[] = new Individuo[NUMERO_REPRODUTORES];
        Arrays.sort(populacao);
        tempProgenitores = Arrays.copyOfRange(populacao, (int)(TAMANHO_POPULACAO*0.4), TAMANHO_POPULACAO);
        
        return tempProgenitores;
        
    }
    

    /*
     *@fn mutação
     *@param Individuos[] descendetes
     *@brief Funcao para mutação de 3 indivíduos
     */
    private Individuo[] mutacao(Individuo[] descendentes){
        
        Random rand = new Random();
        int g[] = new int[Individuo.NUMERO_RAINHAS];
        
        for(int i=0;i<3;++i) {
            int k = rand.nextInt(descendentes.length);
            g = Arrays.copyOf(descendentes[k].getGenoma(), Individuo.NUMERO_RAINHAS);
            int m = g[0];
            for(int j=0;j<g.length-1;++j) {
                    g[j] = g[j+1];
            }
            g[g.length-1] = m;
            descendentes[k].setGenoma(g);
        }
        
        return descendentes;
        
    }

    
    /*
     *@fn recombinação
     *@param Individuos[] ascendetes
     *@brief Funcao para geração dos descendetes
     */
    private Individuo[] recombinacao(Individuo[] ascendentes){
        int indicePai = 0;
        int indiceMae = 0;
        int indiceLocus;
        Random rand = new Random();
        ArrayList<Integer> indiceSorteado = new ArrayList<Integer>();
        Individuo[] tempDescendentes = new Individuo[NUMERO_REPRODUTORES];
        int[] genomaDescendente1 = new int[Individuo.NUMERO_RAINHAS];
        int[] genomaDescendente2 = new int[Individuo.NUMERO_RAINHAS];
        
        for (int i = 0; i < NUMERO_REPRODUTORES;i=i+2){
            indicePai = rand.nextInt(NUMERO_REPRODUTORES);
            indiceMae = rand.nextInt(NUMERO_REPRODUTORES);
            indiceLocus = rand.nextInt(Individuo.NUMERO_RAINHAS);
            
            if( (indiceSorteado.contains(indicePai)) || (indiceSorteado.contains(indiceMae)) )
                i-=2;          
            else{
                for (int j = 0;j < indiceLocus;j++){
                    genomaDescendente1[j] = ascendentes[indicePai].getGenoma()[j];
                    genomaDescendente2[j] = ascendentes[indiceMae].getGenoma()[j];
                }
                for (int j = indiceLocus;j < Individuo.NUMERO_RAINHAS;j++){
                    genomaDescendente1[j] = ascendentes[indiceMae].getGenoma()[j];
                    genomaDescendente2[j] = ascendentes[indicePai].getGenoma()[j];
                }
                tempDescendentes[i] = new Individuo(genomaDescendente1);
                tempDescendentes[i+1] = new Individuo(genomaDescendente2);
                tempDescendentes[i].setAptidao(aptidao(tempDescendentes[i]));
                tempDescendentes[i+1].setAptidao(aptidao(tempDescendentes[i+1]));
                indiceSorteado.add((Integer)indicePai);
                indiceSorteado.add((Integer)indiceMae);
            }
        }        
        return tempDescendentes;
    }
    
    /*
     *@fn substituição
     *@param Individuos[] populacao, Individuos[] reprodutores, Individuos[] descendetes
     *@brief Funcao para substituição da população anterior pela nova população
     */
    private Individuo[] substituicao(Individuo[] populacao, Individuo[] reprodutores, Individuo[] descendentes) {
        
        Individuo []nova_geracao = new Individuo[TAMANHO_POPULACAO];
        
        Arrays.sort(descendentes);
        for(int i=descendentes.length-1;i>=0;--i) {
            nova_geracao[descendentes.length-i-1] = descendentes[i];
        }

        // promoção directa dos melhores 40% da geração antiga
        Arrays.sort(populacao);		// ordena de forma crescente
        for(int i=descendentes.length; i<TAMANHO_POPULACAO; ++i) {
            nova_geracao[i] = populacao[i];
        }
        
        for(int i=0; i < nova_geracao.length; i++){
            nova_geracao[i].setAptidao(aptidao(nova_geracao[i]));
        
        }

        return nova_geracao;
        
    }
    
    public Individuo[] getPopulacao(){
        return this.populacao;
    }
    
}
