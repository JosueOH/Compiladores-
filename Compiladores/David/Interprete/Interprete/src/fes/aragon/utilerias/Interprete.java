/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fes.aragon.utilerias;

import java.util.Stack;

/**
 *
 * @author coctel117
 */
public class Interprete {
    private Stack <Boolean> pila =new Stack();
    private Boolean op1,op2;

    public Interprete() {
    }
     
    public Boolean evaluar(String expresion){
        String [] temp = expresion.split(",");
        Boolean resultado=null;
        
        for (int i = 0; i < temp.length; i++) {
            if(temp[i].equals("true") || temp[i].equals("false")){
                pila.push(Boolean.parseBoolean(temp[i]));
            }else if(temp[i].equals("or")){
                op2=pila.pop();
                op1=pila.pop();
                resultado = op1 || op2;
                pila.push(resultado);
            }else if(temp[i].equals("and")){
                op2=pila.pop();
                op1=pila.pop();
                resultado = op1 && op2;
                pila.push(resultado);
            }else if(temp[i].equals("not")){
                op2=pila.pop();
                resultado = !op2;
                pila.push(resultado);
            }
        }
        resultado=pila.pop();
        return resultado;
    }
}
