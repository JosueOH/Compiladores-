/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fes.aragon.compilador;

import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author asus
 */
public class Principal {
    private String ruta; 
    
    public  Principal(){
        
    }
    
    public String getRuta() throws URISyntaxException {
        ruta= this.getClass().getResource("/fes/aragon/compilador/Fuente.txt").toURI().getPath();
        return ruta; 
    }
    public static void main(String[] args) throws Exception {
        TablaSimbolos tabla= new TablaSimbolos();
        Principal app= new Principal();
        parser inicio=new parser();
        try {
            inicio.cargar(app.getRuta(), tabla);
            tabla.imprimir();
        } catch (URISyntaxException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
