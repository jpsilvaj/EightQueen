package src;

/*
 *Engenharia da Computa�ao - S8
 *@autor Joao Paulo da Silva J�nior
 *@autor Joao Cicero Ferreira Junior
 *@data 02/02/2012
 *@version 1.0
 * */
import java.util.Random;

public class Individuo implements Comparable {

    public static final int NUMERO_RAINHAS = 8;
    private int[] genoma = new int[NUMERO_RAINHAS];
    private int aptidao;
    
    public Individuo(int[] genoma){
        this.genoma = genoma;
    }
    
    public Individuo(){
       Random rand = new Random(); 
       
       for(int j=0;j<NUMERO_RAINHAS;++j) { 
            int k = rand.nextInt(NUMERO_RAINHAS) + 1;              
            if(contains(k,this.genoma))
               --j; 
            else { 
                this.genoma[j] = k; 
            }
       }
       
    }
    
    public int[] getGenoma(){
            return this.genoma;
    }

    public void setGenoma(int[] genoma ) {
            this.genoma = genoma;
    }

    public int getAptidao(){
        return this.aptidao;
    }
    
    public void setAptidao(int aptidao){
        this.aptidao = aptidao;
    }
    
    public static boolean contains(int i, int a[]) {
        for(int k=0;k<a.length;++k) {
            if(a[k]==i) 
                return true;
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer("Genoma Vencedor: ");
        for(int i : this.getGenoma()) { 
            sb.append(i); }
        return sb.toString();
    }
    
    @Override
    public int compareTo(Object arg0) {
        
        Individuo e = (Individuo)arg0;
        if(this.aptidao == e.getAptidao())
            return 0;
        else if(this.aptidao < e.getAptidao())
            return -1;
        else 
            return 1;//if(>)
        
    }
    
}



