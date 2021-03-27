/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fes.aragon.analizador;
import fes.aragon.utilerias.Siguiente;
import fes.aragon.utilerias.Tipos;
import java.util.ArrayList;

/**
 *
 * @author asus
 */
public class Lexico {
    
    private Siguiente sig;
    private int[][] tabla={{2,1,-1}, {-1,-1,-1}, {2,2,1}};
    private int[][] tabla2={{2,1,-1}, {2,1,-1}, {3,1,-1}, {3,1,1}};
    private int[][] tabla3={{1,7,7,7,7,-1}, {1,7,7,2,4,-1}, {3,7,7,7,7,-1}, {3,7,7,7,4,1}, {6,5,5,7,7,-1}, {6,7,7,7,7,-1}, {6,7,7,7,7,1}, {7,7,7,7,7,-1}};
    private int[][] tablaD ;
    private ArrayList<String> alfabeto = new ArrayList<>();
    private char[] caracteres;
     int fila=0;
        int filas=0;
    public Lexico(){
    }
    
    public boolean algoritmo(String token){
        int estado=1;
        sig= new Siguiente(token);
        char simbolo=sig.siguienteCaracter();
        boolean error=false;
        while (simbolo!=' ' && error !=true){
            switch (estado) {
                case 1:
                    if(Tipos.letras(simbolo)){
                        estado=3;
                    }else if (Tipos.numeros(simbolo)){
                        estado=2;
                    }else{
                        error =true;
                        estado=2;
                    }
                    break;
                case 2:
                    error=true;
                    estado=2;
                    break;
                case 3:
                    if(Tipos.letras(simbolo)){
                        estado=3;
                    }else if (Tipos.numeros(simbolo)){
                        estado=3;
                    }else{
                        error =true;
                        estado=2;
                    }
                    break;
            }
            
            simbolo=sig.siguienteCaracter();
        }
        
        if (estado==3){
            return true; 
        }else{
            return false;
        }
}
    
    public boolean algoritmoDos(String token){
        int estado=1;
        sig= new Siguiente(token);
        char simbolo=sig.siguienteCaracter();
        boolean error=false;
        while (simbolo!=' ' && error !=true){
            switch(estado) {
                case 1:
                    if(Tipos.Cero(simbolo)){
                        estado=3;
                    }else if(Tipos.Uno(simbolo)){
                        estado=2;
                    }else{
                        estado=2;
                        error=true;
                    }
                    break;
                case 2:
                    if(Tipos.Cero(simbolo)){
                        estado=3;
                    }else if(Tipos.Uno(simbolo) ){
                        estado=2;
                    }else{
                        estado=2;
                        error=true;
                    }
                    break;
                case 3:
                    if(Tipos.Cero(simbolo)){
                        estado=4;
                    }else if(Tipos.Uno(simbolo)){
                        estado=2;
                    }
                    break;
                
                case 4:
                    if(Tipos.Cero(simbolo)){
                        estado=4;
                    }else if(Tipos.Uno(simbolo)){                        
                        estado=2;
                    }else{
                        estado=2;
                        error=true;
                    }            
                    break;
            }
            simbolo=sig.siguienteCaracter();
        }
        if (estado==4){
            return true; 
        }else{
            return false;
        }
    }
    
    public boolean algoritmoDosT(String token){
        int entrada=0;
        int estado=0;
        sig= new Siguiente(token);
        char simbolo=' ';
        boolean error=false;
        do{
            
            simbolo=sig.siguienteCaracter();
            if(Tipos.Cero(simbolo)){
                entrada=0;
            }else if(Tipos.Uno(simbolo)){
                entrada=1;
            }else if(Tipos.finCadena(simbolo)){
                entrada=2;
                error=true;
            }else{
                entrada=2;
                error=true;
            }
            estado=tabla2[estado][entrada];
        }while(estado!=-1 && error == false);
        if(estado==1){
            return true;
        }else{
            return false;
        }
    }
    
    public boolean algoritmoTres(String token){
        int entrada=0;
        int estado=0;
        sig= new Siguiente(token);
        char simbolo=' ';
        boolean error=false;
        do{
            
            simbolo=sig.siguienteCaracter();
            if(Tipos.letras(simbolo)){
                entrada=0;
            }else if(Tipos.numeros(simbolo)){
                entrada=1;
            }else if(Tipos.finCadena(simbolo)){
                entrada=2;
                error=true;
            }else{
                
                error=true;
            }
            estado=tabla[estado][entrada];
        }while(estado!=-1 && error == false);
        if(estado==1){
            return true;
        }else{
            return false;
        }
    }
    
    public boolean algoritmoCuatro(String token){
        int entrada=0;
        int estado=0;
        sig= new Siguiente(token);
        char simbolo=' ';
        boolean error=false;
        
        do{
            simbolo=sig.siguienteCaracter();
            if(Tipos.numeros(simbolo)){
                entrada=0;
            }else if(Tipos.mas(simbolo)){
                entrada=1;
            }else if(Tipos.menos(simbolo)){
                entrada=2;
            }else if(Tipos.punto(simbolo)){
                entrada=3;
            }else if(Tipos.e(simbolo)){
                entrada=4;
            }else if(Tipos.finCadena(simbolo)){
                entrada=5;
                error=true;
            }else{
                entrada=5;
                error=true;
            }
            estado=tabla3[estado][entrada];
        }while(estado!=-1 && error == false);
        if(estado==1){
            return true;
        }else{
            return false;
        }
    }
    
    public void leerTabla(String lineas){
            String[] datos;
             
                if (fila==0){
                    datos=lineas.split(",");
                    tablaD = new int[Integer.parseInt(datos[0])][Integer.parseInt(datos[1])];
                    caracteres= new char[Integer.parseInt(datos[1])-1];
                    //tablaD= new int[8][6];
                                      
                }else if(fila==1){
                    datos=lineas.split(",");
                    
                    for(int i=0; i<caracteres.length; i++){
                caracteres [i] = datos[i].charAt(0);}
                      
                }else if(fila>=2){
                    datos=lineas.split(",");
                    for (int i=0; i<tablaD[0].length; i++){
                        if(i==tablaD[0].length-1){
                            tablaD[filas][i]=Integer.parseInt(datos[i]);
                        }else if(i!=tablaD[0].length-1){
                            tablaD[filas][i]=Integer.parseInt(datos[i])-1;
                        }
                        
                    }filas++;
                }fila++;   
}
    
    
    public void getTabla(){
        
        for (int i = 0; i <tablaD.length; i++) {
            for (int j = 0; j < tablaD[i].length; j++) {
                System.out.print(tablaD[i][j]);
                
            }System.out.println("");
            
        }
        
        for (int i = 0; i < caracteres.length; i++) {
            System.out.print(caracteres[i]);
            
        }System.out.println("");
    }
    
    public boolean AlgoritmoCinco( String token){
        int estado =0;
        int entrada=0;
        sig = new Siguiente(token);
        char simbolo = ' ';
        boolean error =false;
        
        do{
          simbolo=sig.siguienteCaracter();
         
          if(Tipos.numeros(simbolo)){
                    char sim='D';
                    
                    for (int i = 0; i <caracteres.length; i++) {

                    if(sim==caracteres[i]){
                    entrada=i; 
                      break;
                   }else{
                    entrada=caracteres.length-1;
                    error=true;} 
                }
                }else if (Tipos.finCadena(simbolo)){
                entrada=caracteres.length-1;
                error= true;

                }else{
  
                for (int i = 0; i <caracteres.length; i++) {
              
                if(simbolo==caracteres[i]){
                    entrada=i; 
                    break;
                   }else{
                    entrada=caracteres.length-1;
                    error=true; 
                }
                }
        }
          
         estado=tablaD[estado][entrada]; 
         
      }while(estado==-1 && error== false);
        if (estado == 1) {
              return true ;
          } else {
               return false ;
          }
    
}}
    
        
    
    
    
    
    

