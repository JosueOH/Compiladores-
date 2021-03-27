public class Tipo {
    public static boolean letra(char c){
        boolean letra= false;
        if((c >=65 && c<=90)|| (c>=97 && c<=122)){
            letra=true;
        }
        return letra;
    }
    
    public static boolean numero(char c){
        boolean numero= false;
        if((c >=48 && c<=57)){
            numero=true;
        }
        return numero;
    }
   
}
