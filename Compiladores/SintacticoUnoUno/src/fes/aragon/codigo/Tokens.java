/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fes.aragon.codigo;

/**
 *
 * @author asus
 */
public class Tokens {
    private String Token;
    private int lexema; 
    private int linea;

    public Tokens(String Token, int lexema, int linea, int columna) {
        this.Token = Token;
        this.lexema = lexema;
        this.linea = linea;
        this.columna = columna;
    }
    private int columna;

    public String getToken() {
        return Token;
    }

    public void setToken(String Token) {
        this.Token = Token;
    }

    public int getLexema() {
        return lexema;
    }

    public void setLexema(int lexema) {
        this.lexema = lexema;
    }

    public int getLinea() {
        return linea;
    }

    public void setLinea(int linea) {
        this.linea = linea;
    }

    public int getColumna() {
        return columna;
    }

    public void setColumna(int columna) {
        this.columna = columna;
    }
    
}
