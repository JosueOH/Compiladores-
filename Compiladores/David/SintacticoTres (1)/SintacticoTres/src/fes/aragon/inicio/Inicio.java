/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fes.aragon.inicio;

import com.sun.org.apache.xalan.internal.xsltc.compiler.sym;
import fes.aragon.codigo.Lexico;
import fes.aragon.codigo.Sym;
import fes.aragon.codigo.Tokens;
import java.io.BufferedReader;
import java.io.File;//n
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;//
import java.io.IOException;
import java.io.PrintWriter;//
import java.util.ArrayList;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mash
 */
public class Inicio {

    private Lexico analizador = null;
    private Tokens tokens = null;
    private boolean linea = false;
    private String cadena = "";
    private Stack<String> operadores = new Stack<>();
    private ArrayList<String> lineas = new ArrayList<>();
    
    File arc=new File("postfija.txt");
    FileWriter escribir;
    PrintWriter lin;
    
    

    public static void main(String[] args) {
        try {
            Inicio ap = new Inicio();
            BufferedReader buf;
            buf = new BufferedReader(new FileReader(System.getProperty("user.dir")
                    + "/archivo.txt"));
            ap.analizador = new Lexico(buf);
            ap.siguienteToken();
            while (ap.tokens.getLexema() != Sym.EOF) {
                try {
                    ap.S();
                    if (ap.linea) {
                        System.out.println("Correcto: " + (ap.tokens.getLinea() + 1));
                        ap.linea = false;
                        ap.cadena = ap.coverPosfija(ap.lineas);
                        System.out.println(ap.cadena);
                        ap.escribirEnArchivo(ap.arc, ap.cadena);
                        ap.lineas.clear();
                    } else {
                        System.out.println("Incorrecto: " + (ap.tokens.getLinea() + 1));
                        ap.linea = false;
                        ap.lineas.clear();
                    }

                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
                ap.siguienteToken();
            }

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void siguienteToken() throws IOException {
        tokens = analizador.yylex();
        if (tokens == null) {
            tokens = new Tokens("EOF", Sym.EOF, 0, 0);

        }

    }
    //inicio del código analizador sintactico de expresiones booleanas

    private void S() throws IOException {
        E();
        if (tokens.getLexema() != Sym.PUNTOCOMA) {
            linea = false;
            throw new IOException("Error al compilar: " + (tokens.getLinea() + 1));
        } else {
            linea = true;
        }
    }

    private void E() throws IOException {
        T();
        if (tokens.getLexema() == Sym.OR) {
            this.lineas.add(tokens.getToken());
            siguienteToken();
            E();
        }
    }

    private void T() throws IOException {
        F();
        if (tokens.getLexema() == Sym.AND) {
            this.lineas.add(tokens.getToken());
            siguienteToken();
            T();
        }
    }

    private void F() throws IOException {
        if (tokens.getLexema() == Sym.NOT) {
            this.lineas.add(tokens.getToken());
            siguienteToken();
            F();
        } else if (tokens.getLexema() == Sym.TRUE || tokens.getLexema() == Sym.FALSE) {
            this.lineas.add(tokens.getToken());
            siguienteToken();
        } else if (tokens.getLexema() == Sym.INICIOP) {
            this.lineas.add(tokens.getToken());
            siguienteToken();
            E();
            if (tokens.getLexema() == Sym.FINP) {
                this.lineas.add(tokens.getToken());
                siguienteToken();
            }
        }
    }
//Metodo para convertir de notacion infija a postfija
    private String coverPosfija(ArrayList<String> lista) {
        String simbolo;
        String cad = "";

        while (!lista.isEmpty()) {
            simbolo = lista.get(0);
            
            while (!operadores.empty() && precedencia(simbolo, operadores.peek())) {
                    if(!operadores.peek().equals("(")){
                        cad = cad + operadores.pop() + ",";
                    }else{
                        operadores.pop();
                    }
                }

            if (simbolo.equals("true") || simbolo.equals("false")) {
                cad = cad + simbolo + ",";
            } else {
                
                if (simbolo.equals(")")) {
                    while (!operadores.empty() && !operadores.peek().equals("(")) {
                        cad = cad + operadores.pop() + ",";
                    }
                    
                } else {
                    operadores.push(simbolo);
                }
                
            }
            lista.remove(0);
        }

        while (!operadores.empty()) {
            if (!operadores.peek().equals("(")) {
                cad =cad + operadores.pop() + ",";
            } else {
                operadores.pop();
            }
        }

        return cad;
    }
//Metodo que evalua la precedencia entre el simbolo leido y la cima de la pila
    private boolean precedencia(String simbolo, String cima) {
        if (simbolo.equals(cima)) {
            return true;
        } else if (simbolo.equals("and") && cima.equals("not")) {
            return true;
        } else if (simbolo.equalsIgnoreCase("or")) {
            return true;
        }
        return false;
    }
//Escribe en un archivo la cadena ya convertida a notacion postfija
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

}
