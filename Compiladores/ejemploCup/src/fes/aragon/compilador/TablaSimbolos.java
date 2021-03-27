/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fes.aragon.compilador;

import java.util.HashMap;
import java.util.Iterator;

/**
 *
 * @author Administrador
 */
public class TablaSimbolos {

    private HashMap t;

    public TablaSimbolos() {
        t = new HashMap();
    }

    public Simbolo insertar(String nombre) {
        Simbolo s = new Simbolo(nombre, new Integer(0));
        Datos ss=new Datos();
        t.put(nombre, ss);
        return s;
    }

    public Simbolo buscar(String nombre) {
        return (Simbolo) (t.get(nombre));
    }

    public void imprimir() {
        Iterator it = t.values().iterator();
        while (it.hasNext()) {
            Simbolo s = (Simbolo) it.next();
            System.out.println(s.getNombre() + ": " + s.getValor());
        }
    }

}
