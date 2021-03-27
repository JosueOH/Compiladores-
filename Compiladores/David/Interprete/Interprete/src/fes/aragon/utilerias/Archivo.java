/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fes.aragon.utilerias;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author coctel117
 */
public class Archivo {
    public static ArrayList<String> lectura(String ruta) throws FileNotFoundException, IOException {

        File f = new File(ruta);
        ArrayList<String> lineas = new ArrayList<String>();
        
        if (f.exists()) {
            FileReader fr = new FileReader(f);
            BufferedReader bf = new BufferedReader(fr);
            String cadena = "";

            while ((cadena = bf.readLine()) != null) {
                lineas.add(cadena);
            }
            bf.close();
            fr.close();
        } else {
            JOptionPane.showMessageDialog(null, "El archivo no existe");
        }
        return lineas;
    }
}
