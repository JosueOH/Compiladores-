import fes.aragon.analizador.Lexico;
import fes.aragon.utileria.archivo;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author asus
 */
public class inicio {
    public static void main(String[] args) {
        Lexico lx = new Lexico(); 
        try{
            ArrayList<String> lin=archivo.Lectura(System.getProperty(("user.dit") + "/Fuente.txt"));
            boolean valido;
            for ( String ln:lin ){
                valido=lx.algoritmo(ln);
                if(valido){
                    System.out.println(ln +" Es válido");
                }else{
                System.out.println(ln +" Es inválido");
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    
}

