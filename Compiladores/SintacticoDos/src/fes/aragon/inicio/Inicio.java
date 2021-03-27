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

/**
 *
 * @author asus
 */
public class Inicio {
    private Lexico analizador=null;
    private Tokens tokens=null;
    private boolean linea= false;
    
    public static void main(String[] args)  {
        try{
            Inicio ap= new Inicio();
            BufferedReader buf;
            buf = new BufferedReader(new FileReader(System.getProperty("user.dir") + "/archivo2.txt"));
            ap.analizador= new Lexico(buf);
            ap.siguienteToken();
            while(ap.tokens.getLexema()!=Sym.EOF){
                //System.out.println("token:-> " + ap.tokens.getToken());
             
                    ap.sentencia();
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
            System.out.println(ex.getMessage());
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
    
    
    private void sentencia() throws IOException{
        asignacion();
        if(tokens.getLexema()!=Sym.PUNTOCOMA){
            //System.out.println("Error de sintaxis");
            throw new IOException("Error al compilar: " + tokens.getLinea()+1);
        }else{
            linea=true;   
        }       
    }
    
    private void asignacion() throws IOException{
        if(tokens.getLexema()==Sym.ID){
            siguienteToken();
            if(tokens.getLexema()==Sym.IGUAL){
                siguienteToken();
                expresion();
            }  
        }else{
            throw new IOException("Error al compilar: " + tokens.getLinea()+1 );
        }
    }
    
    private void expresion() throws IOException{
        if(tokens.getLexema()==Sym.ID || tokens.getLexema()==Sym.ENTERO){
            siguienteToken();
            if (tokens.getLexema()== Sym.MAS || tokens.getLexema()== Sym.MENOS){
                siguienteToken();
                expresion();
            }
        }else{
           throw new IOException("Error al compilar: " + tokens.getLinea()+1 );
        }
    }
    
}
