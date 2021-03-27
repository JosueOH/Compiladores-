/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fes.aragon.inicio;

import fes.aragon.analizador.Lexico;

import fes.aragon.utilerias.Archivo;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author asus
 */
public class Inicio5 {
    public static void main(String[] args) {
        Lexico lx = new Lexico(); 
        try{
            ArrayList<String> leer=Archivo.lectura(System.getProperty("user.dir") + "/AC.txt");
           
            for ( String ln:leer ){
                
                lx.leerTabla(ln);
            }
            lx.getTabla();

        }catch (IOException e){
            e.printStackTrace();
        }
        try{
            ArrayList<String> validar=Archivo.lectura(System.getProperty("user.dir") + "/Fuente6.txt");
            boolean valido;
            for ( String ln:validar ){
                valido=lx.AlgoritmoCinco(ln);
                if(valido){
                    System.out.println(ln +" Es válido");
                }else{
                    
                System.out.println(ln +" Es inválido");
                }
            }

        }catch (IOException e){
            e.printStackTrace();
        }
    }   
       
}
