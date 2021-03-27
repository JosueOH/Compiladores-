/*
Leer un archivo .txt
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
 * @author Rollongo
 */
public class Archivo {
    public static ArrayList<String> lectura (String ruta) throws FileNotFoundException, IOException{
        File f= new File(ruta);
        ArrayList<String> lineas = new ArrayList<String>();
        if (f.exists()){
            FileReader fr = new FileReader(f);
            BufferedReader bf = new BufferedReader (fr);
            String cad="";
            while((cad=bf.readLine())!=null){
                lineas.add(cad);
            }
            bf.close();
            fr.close();
        }else{
            JOptionPane.showMessageDialog(null, "El archivo no existe");
        }
        return lineas;        
    } 
    
}
