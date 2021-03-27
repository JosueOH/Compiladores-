/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fes.aragon.inicio;

import fes.aragon.utilerias.Archivo;
import fes.aragon.utilerias.Interprete;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author coctel117
 */
public class Inicio {

    public static void main(String[] args) {
        Interprete it = new Interprete();
        Boolean resultado;

        try {
            ArrayList<String> lineas = Archivo.lectura(System.getProperty("user.dir") + "/postfija.txt");

            for (String linea : lineas) {
                resultado = it.evaluar(linea);
                System.out.println("Expresion " + (lineas.indexOf(linea)+1) + " " + linea);
                System.out.println("Resultado " + (lineas.indexOf(linea)+1) + " = " + resultado);
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }
}
