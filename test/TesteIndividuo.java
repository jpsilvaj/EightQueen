/*
 *Engenharia da Computa�ao - S8
 *@autor Joao Paulo da Silva J�nior
 *@autor Joao Cicero Ferreira Junior
 *@data 02/02/2012
 *@version 1.0
 * */

package test;
import src.Individuo;
import org.junit.*;

/**
 *
 * @author Paulo
 */
public class TesteIndividuo {
    
    private Individuo individuo;
    
    @Before public void criaIndividuo(){
        individuo = new Individuo();    
    }
    
    @Test public void testeCriacaoIndividuo(){
        
       boolean flagContem = false;
        
       for (int i =0;i < Individuo.NUMERO_RAINHAS;i++){
           if( Individuo.contains(i, individuo.getGenoma()) ){
                flagContem = true;
           }
       }
       Assert.assertTrue("Deu certo Mano!!", flagContem);
       
    }
    
    @Test public void testeContains(){
        
        int[] array = new int[10];
        array[0] = 1;
        boolean resultado = Individuo.contains(1, array);
        Assert.assertTrue("Deu certo",resultado);
        
    }
    
    @Test public void testeComparable(){
        
        Individuo individuoMenor = new Individuo();
        Individuo individuoMaior = new Individuo();
        individuoMaior.setAptidao(10);
        individuoMenor.setAptidao(9);

        int resultado = individuoMaior.compareTo(individuoMenor);
        Assert.assertTrue((resultado == 1));
     
        resultado = individuoMenor.compareTo(individuoMaior);
        Assert.assertTrue((resultado == -1));
        
        resultado = individuoMaior.compareTo(individuoMaior);
        Assert.assertTrue((resultado == 0));
        
    }
    
    
}
