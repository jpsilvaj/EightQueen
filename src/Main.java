/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

/**
 *
 * @author Paulo
 */
public class Main {

    public static void main(String[] args){
        
       //Individuo solucao;
        
       AG8Rainhas eightQueen = new AG8Rainhas();
        
       eightQueen.resolveAG8Rainhas(eightQueen.getPopulacao());
        
        
    }
    
    
}
