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
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Stack;
import fes.aragon.utilerias.Archivo;

/**
 *
 * @author asus 
 */
public class Inicio {
    private Lexico analizador=null;
    private Tokens tokens=null;
    private boolean linea= false;
    private  ArrayList<String> lineas=new ArrayList<>();
    private String post="";
    private Stack<String> pila = new Stack ();
    private Stack<Boolean> pilaInterprete = new Stack ();
    
    File interprete=new File("postfija.txt");
    FileWriter escribir;
    PrintWriter lin;
    
    public static void main(String[] args) throws FileNotFoundException, IOException  {
        Inicio ap= new Inicio();
        BufferedReader buf;
        try{
        buf = new BufferedReader(new FileReader(System.getProperty("user.dir") + "/archivo3.txt"));
        ap.analizador= new Lexico(buf);
        ap.siguienteToken();
        while(ap.tokens.getLexema()!=Sym.EOF){
            //System.out.println("token:-> " + ap.tokens.getToken());

            ap.metodo_S();

            if(ap.linea){
                System.out.println("Coorrecto: " + (ap.tokens.getLinea()+1));
                ap.linea= false;
                for (String cont : ap.lineas) {
                    System.out.print(cont);
                    System.out.println("");

                }
                ap.post=ap.converPosfija(ap.lineas);
                System.out.println(ap.post);
                ap.lineas.clear();
            }else{
                System.out.println("Incorrecto: "+ (ap.tokens.getLinea()+1));
                ap.linea=false;
                ap.lineas.clear();
            }
            ap.siguienteToken();

            }

        } catch(FileNotFoundException ex) {
            ex.printStackTrace();    
        } catch (IOException ex){
            System.out.println(ex.getMessage());
        }
    
      try{
          ArrayList<String> lin= Archivo.lectura(System.getProperty("user.dir")+"/postfija.txt");         
         int cont=0;
          for (String ln : lin) {
                boolean resultado = ap.interprete(ln);
                cont++;
                System.out.println("Resultado de la operación booleana " + cont + ":"  + resultado);
            }
          
          //System.out.println(lin.size());
            
        }catch(FileNotFoundException ex) {
            ex.printStackTrace(); 
            
        }
    
}
    
    void siguienteToken() throws IOException{
        
            tokens=analizador.yylex();
            if(tokens==null){
                tokens= new Tokens("EOF", Sym.EOF, 0,0);
                //throw new IOException("FIn");
            }
        
            //System.out.println("Fin de archivo");
        }
    
    
    private void metodo_S() throws IOException{
        metodo_E(); 
        if(tokens.getLexema()!=Sym.PUNTOCOMA){
            //System.out.println("Error de sintaxis");
            linea=false;
            throw new IOException("Error al compilar: " + (tokens.getLinea()+1));
            
        }else{
            linea=true;   
        }       
    }
    
    private void metodo_E() throws IOException{
        metodo_T();
        if(tokens.getLexema()==Sym.OR ){
            this.lineas.add(tokens.getToken());
            siguienteToken();
            metodo_E();
        }

    }
    
    private void metodo_T() throws IOException{ 
        metodo_F();
      
        if(tokens.getLexema()==Sym.AND){
            this.lineas.add(tokens.getToken());
            siguienteToken();
            metodo_T();
        }            
    }
    
    private void metodo_F() throws IOException{
        if(tokens.getLexema()==Sym.NOT){
            this.lineas.add(tokens.getToken());
            siguienteToken();
            metodo_F();
        }
        
        else if(tokens.getLexema()==Sym.TRUE || tokens.getLexema()==Sym.FALSE ){
            this.lineas.add(tokens.getToken());
            siguienteToken();
            
        }
        else if(tokens.getLexema()==Sym.APERTURA){
            this.lineas.add(tokens.getToken());
            siguienteToken();
            metodo_E();
            if(tokens.getLexema()==Sym.CIERRE){
            this.lineas.add(tokens.getToken());
            siguienteToken();
            }
        } else {
            throw new IOException("Error al compilar: " + (tokens.getLinea()+1));
        }
    }  
    
    private String converPosfija (ArrayList<String> lista){
        String cadena= "";
        
        String token;
        int contador= 0;
        while (!lista.isEmpty()) {
            token = lista.get(contador);
            
            if (token.equals("true") || token.equals("false")) {
                cadena = cadena + token + " ";
            } else {
                while (!pila.empty() && precedencia(pila.peek(), token)) {
                    cadena = cadena + pila.pop() + " ";
                }
                if (token.equals(")")) {

                    while (!pila.peek().equals("(")) {
                        cadena = cadena + pila.pop() + " " ;
                    }
                    pila.pop();
                } else {
                    pila.push(token);
                }
            }

            lista.remove(contador);

        }
        while(!pila.empty()){
          cadena=cadena+pila.pop() + " ";  
        }
        escribirEnArchivo(interprete, cadena);
        return cadena;
       
    }
    
    private boolean precedencia(String pila , String token ){
        
        
        if(token.equals("or") && pila.equals("and")){
            return true;
        }
        if(token.equals("and") && pila.equals("not")){
            return true;
        }
        if(token.equals("or") && pila.equals("not")){
            return true;
        }
        
        if(token.equals(pila)){
            return true;
        }
        
       return false; 
    }
    
    private void escribirEnArchivo(File archivo, String cadena){
        if (!archivo.exists()) {
            try {
                archivo.createNewFile();
                this.escribir = new FileWriter(archivo, true);
                this.lin = new PrintWriter(escribir);
                this.lin.println(cadena);
                this.lin.close();
                this.escribir.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                
                this.escribir = new FileWriter(archivo, true);
                this.lin = new PrintWriter(this.escribir);
                this.lin.println(cadena);
                this.lin.close();
                this.escribir.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    private boolean interprete(String linea){
        
        String [] tokens = linea.split(" ");
        int contador=0;
        boolean resultado = false;
        
        for (int i = 0; i < tokens.length; i++) {   
            
            if (tokens[i].equals("true") || tokens[i].equals("false")){
                boolean tok= Boolean.parseBoolean(tokens[i]);
                pilaInterprete.push(tok);
                
            }if (tokens[i].equals("and")){
            boolean op1=pilaInterprete.pop();
            boolean op2=pilaInterprete.pop();
            resultado= op1 && op2;  
            pilaInterprete.push(resultado);
            
            }if (tokens[i].equals("or")){
            boolean op1=pilaInterprete.pop();
            boolean op2=pilaInterprete.pop();
            resultado= op1 || op2;  
            pilaInterprete.push(resultado);
            
            }if (tokens[i].equals("not")){
            boolean op=pilaInterprete.pop();
            resultado= !op;
            pilaInterprete.push(resultado);
            }
            
        }
         return pilaInterprete.pop();   
    }
}