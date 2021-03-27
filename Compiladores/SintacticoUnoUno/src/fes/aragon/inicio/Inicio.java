/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fes.aragon.inicio;

import fes.aragon.codigo.Lexico;
import fes.aragon.codigo.Sym;
import fes.aragon.codigo.Tokens;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import fes.aragon.utilerias.Archivo;

/**
 *
 * @author asus
 */
public class Inicio {
    private Lexico analizador=null;
    private Tokens tokens=null;
    private boolean linea= false;
    
    public static void main(String[] args) throws IOException  {
        try{
            Inicio ap= new Inicio();
            BufferedReader buf;
            buf = new BufferedReader(new FileReader(System.getProperty("user.dir") + "/archivo.txt"));
            ap.analizador= new Lexico(buf);
            ap.siguienteToken();
            while(ap.tokens.getLexema()!=Sym.EOF){
                System.out.println("token:-> " + ap.tokens.getToken());
                ap.instrucciones();
                if(ap.linea){
                    System.out.println("Coorrecto: " + (ap.tokens.getLinea()+1));
                    ap.linea= false;
                }else{
                    System.out.println("Incorrecto: "+ (ap.tokens.getLinea()+1));
                    ap.linea=false;
                }
                ap.siguienteToken();
            }
        } catch(FileNotFoundException ex) {
          ex.printStackTrace();    
        } catch (IOException ex){
            ex.printStackTrace();
        }
        
        
    }
    
    private void siguienteToken() throws IOException{
        
            tokens=analizador.yylex();
            if(tokens==null){
                tokens= new Tokens("EOF", Sym.EOF, 0,0);
                //throw new IOException("FIn");
            }
        
            //System.out.println("Fin de archivo");
        }
    
    
    private void instrucciones() throws IOException{
        secuencia();
        if(tokens.getLexema()!=Sym.PUNTOCOMA){
            //System.out.println("Error de sintaxis");
            throw new IOException("Error al compilar: " + tokens.getLinea());
        }else{
            linea=true;
            
        }
        
    }
    
    private void secuencia() throws IOException{
        if(tokens.getLexema()==Sym.TRUE || tokens.getLexema()==Sym.FALSE){
            siguienteToken();
        }else{
            throw new IOException("Error al compilar: " + tokens.getLinea() );
        }
    }
}
