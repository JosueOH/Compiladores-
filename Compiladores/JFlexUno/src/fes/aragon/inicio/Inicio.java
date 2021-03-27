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
    
    public static void main(String[] args)  {
        try{
            Inicio ap= new Inicio();
            BufferedReader buf;
            buf = new BufferedReader(new FileReader(System.getProperty("user.dir") + "/archivo.txt"));
            ap.analizador= new Lexico(buf);
            ap.siguienteToken();
            while(ap.tokens.getLexema()!=Sym.EOF){
                System.out.println(ap.tokens.getToken());
                ap.siguienteToken();
            }
        } catch(FileNotFoundException ex) {
          ex.printStackTrace();    
        }
    }
    
    private void siguienteToken(){
        try{
            tokens=analizador.yylex();
            if(tokens==null){
                tokens= new Tokens("EOF", Sym.EOF, 0,0);
                throw new IOException("FIn");
            }
        }catch (IOException ex){
            System.out.println("Fin de archivo");
        }
    }
    
}
