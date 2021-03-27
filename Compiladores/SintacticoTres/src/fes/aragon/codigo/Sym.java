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
public class Sym {
  public static final int APERTURA = 0;
  public static final int CIERRE = 1;  
  public static final int PUNTOCOMA = 2;
  public static final int SALTOLINEA = 3;  
  public static final int EOF = 4;
  public static final int OR = 5;
  public static final int AND = 6;
  public static final int NOT = 7;
  public static final int TRUE=8; 
  public static final int FALSE=9;
  public static final int VACIO=10;
  public static final String[] terminales = new String[] {
  "APERTURA",  
  "CIERRE",
  "PUNTOCOMA",
  "SALTOLINEA",
  "EOF",
  "OR",
  "AND",
  "NOT",
  "TRUE",
  "FALSE",
  " "
  };
    
    
}
