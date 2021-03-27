/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fes.aragon.creador;

import java.io.File;

/**
 *
 * @author eliot
 */
public class CrearJFlex {

    private final static String rutaArchivos = System.getProperty("user.dir") + "/src/fes/aragon/creador/";
    private final static String rutaSalida = System.getProperty("user.dir") + "/src/fes/aragon/compilador/";

    public static void crearJFlex() {
        File file = new File(rutaArchivos + "A_Lexico.jflex");
        jflex.Options.setDir(rutaSalida);
        jflex.Main.generate(file);
    }

    public static void main(String[] args) {
        CrearJFlex.crearJFlex();
    }
}
