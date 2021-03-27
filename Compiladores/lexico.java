import fes.aragon.utileria.Siguiente;
import fes.aragon.utilieria.Tipo;

/**
 *
 * @author asus
 */
public class Lexico {
    private Siguiente sig;
    
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
                    if(Tipo.letra(simbolo)){
                        estado=3;
                    }else if (Tipo.numero(simbolo)){
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
                    if(Tipo.letra(simbolo)){
                        estado=3;
                    }else if (Tipo.numero(simbolo)){
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
    
}
