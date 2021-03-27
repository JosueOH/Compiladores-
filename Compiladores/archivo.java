import java.io.IOException;
import java.util.ArrayList;
import javax.swing.*;

/**
 *
 * @author asus
 */
public class archivo {
    public static void main(String[] args) {    }

        
    public static ArrayList<String> Lectura (String ruta) throws FileNotFoundException, IOException{
        File f= new File(ruta);
        ArrayList<String> lineas = new ArrayList<>();
        
        if (f.exists()){
            FileReader fr= new FileReader(f);
            BufferedReader bf=new BufferedReader(fr);
            String cad="";
            
            while((cad=bf.readLine()) !=null){
                lineas.add(cad);
            }
            bf.close();
            fr.close();
        }else{
           JOptionPane.showMessageDialog(null, "El archivo no existe");
        }
        return lineas; 
    }
    
}
