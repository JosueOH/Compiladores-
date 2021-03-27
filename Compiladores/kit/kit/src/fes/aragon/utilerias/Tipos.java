/*
 * Verifica que un tipo de dato sea valido de acuerdo al codigo ASCII
 */
package fes.aragon.utilerias;

/**
 *
 * @author asus
 */
public class Tipos {
    public static boolean letras (char c){
        boolean letra=false;        
        if ((c>=65 && c<=90) || (c>=97 && c<=122)){
            letra = true;
        }
        return letra;
    }
    
    public static boolean numeros (char a){
        boolean numero=false;        
        if ((a>=48 && a<=57)){
            numero = true;
        }
        return numero;
    }
    public static boolean Cero (char a){
        boolean binarioCero=false;        
        if ((a==48)){
            binarioCero = true;
        }
        return binarioCero;
    }
    public static boolean Uno (char a){
        boolean binarioUno=false;        
        if ((a==49)){
            binarioUno = true;
        }
        return binarioUno;
    }
    
    public static boolean finCadena(char c){
        if(c==';'){
            return true;
        }else{
            return false;
        }
    }
    
    public static boolean mas(char c){
        if(c=='+'){
            return true;
        }else{
            return false;
        }
    }
    
    public static boolean menos(char c){
        if(c=='-'){
            return true;
        }else{
            return false;
        }
    }
    
    public static boolean punto(char c){
        if(c=='.'){
            return true;
        }else{
            return false;
        }
    }
    
    public static boolean e(char c){
        if(c=='e'){
            return true;
        }else{
            return false;
        }
    }
    
    
    
}
