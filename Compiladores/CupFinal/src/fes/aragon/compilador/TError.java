/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fes.aragon.compilador;

/**
 *
 * @author asus
 */
public class TError {
    String lexema;
    int line;
    int column;
    String tipo;
    String descripcion;

    public TError(String lexema, int line, int column, String tipo, String descripcion) {
        this.lexema = lexema;
        this.line = line;
        this.column = column;
        this.tipo = tipo;
        this.descripcion = descripcion;
    }
    
}
