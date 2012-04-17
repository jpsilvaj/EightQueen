/*
 *Engenharia da Computa�ao - S8
 *@autor Joao Paulo da Silva J�nior
 *@autor Joao Cicero Ferreira Junior
 *@data 02/02/2012
 *@version 1.0
 * */

package test;
import org.junit.*;
import src.AG8Rainhas;
import src.Individuo;

/**
 *
 * @author Paulo
 */
public class Teste8Rainhas {
    
    @Test public void testResolve8Rainhas(){
    
        AG8Rainhas rainhas = new AG8Rainhas();
        boolean resultado = true;
        for(int i = 0; i < 1000; i++){
            resultado = rainhas.resolveAG8Rainhas(rainhas.getPopulacao());
            rainhas.initPopulacao();
            System.out.println("Teste nº: " + (i+1));
            //Assert.assertTrue(resultado);
        }
    }
    
    
    @Test public void verificaInsercaoGenoma(){
        
        
        
    }
    
    @Test public void verificaSelecao(){
        
    }
    
}
