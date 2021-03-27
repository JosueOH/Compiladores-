/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fes.aragon.compilador;

import java.io.FileInputStream;
import java.net.URISyntaxException;

/**
 *
 * @author asus
 */
public class Inicio {
     private String ruta;

    public Inicio() {
    }

    public String getRuta() throws URISyntaxException {
        ruta = this.getClass().getResource("/fes/aragon/compilador/fuente.txt").toURI().getPath();
        return ruta;
    }

    public static void main(String[] args) {
        try {
            Inicio app = new Inicio();
            //app.crear();
            app.correr();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    /*private void crear() throws Exception {
        String archivoJFlex = "FuenteJFlex.txt";
        String archivoCUP = "FuenteCup.txt";
        Crear crear = new Crear(archivoJFlex, archivoCUP);
    }*/
    private void correr() {
        try {
            analisis_sintactico inicio;
            Analizador_Lexico lex = null;
            lex = new Analizador_Lexico(new FileInputStream(this.getRuta()));
            inicio = new analisis_sintactico(lex);
            inicio.parse();
           // System.out.println("resultado = " + lex.yytext() + inicio.resultado);
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
}
