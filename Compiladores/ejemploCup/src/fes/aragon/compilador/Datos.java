/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fes.aragon.compilador;

/**
 *
 * @author MASH
 */
public class Datos {
    private String lexema;
    private int nombreVariable;
    private String valor;
    private boolean whl=false;
    private int whlt=0;

    public Datos() {
    }
    

    public Datos(String lexema, int nombreVariable, String valor) {
        this.lexema = lexema;
        this.nombreVariable = nombreVariable;
        this.valor = valor;
    }

    public String getLexema() {
        return lexema;
    }

    public void setLexema(String lexema) {
        this.lexema = lexema;
    }

    public int getNombreVariable() {
        return nombreVariable;
    }

    public void setNombreVariable(int nombreVariable) {
        this.nombreVariable = nombreVariable;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public boolean isWhl() {
        return whl;
    }

    public void setWhl(boolean whl) {
        this.whl = whl;
    }

    public int getWhlt() {
        return whlt;
    }

    public void setWhlt(int whlt) {
        this.whlt = whlt;
    }
    
    
}
