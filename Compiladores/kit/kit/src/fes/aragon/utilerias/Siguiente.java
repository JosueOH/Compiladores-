/*
 * Lee el siguiente caracter
 */
package fes.aragon.utilerias;

/**
 *
 * @author asus
 */
public class Siguiente {
    private int contador=0;
    private int indice =-1;
    private String token;

    public Siguiente(String token) {
        this.contador=token.length();
        this.token=token;
    }
    public char siguienteCaracter(){
        char c=' ';
        if(indice<contador-1){
            indice++;
            c=this.token.charAt(indice);
        }
        return c;
    }
        
}
