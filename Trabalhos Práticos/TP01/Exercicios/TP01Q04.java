import java.util.Random;
class TP01Q04 {
    public static Random gerador = new Random();
    public static char Randomchar(){
        char result =  (char) (97 +  (Math.abs(gerador.nextInt()) % 26));
        return result;
    }
    public static String Troca(String palavra){
        char letra1 = Randomchar();
        char letra2 = Randomchar();
        String trocada = "";
        for(int i = 0; i < palavra.length(); i++){
            char index = palavra.charAt(i);
            if(letra1 == index){
                index = letra2;
            }
            trocada += index;
        }
        return trocada;
    }
    public static boolean isFIM (String word){
        String end = "FIM";
        boolean result = true;
        int len = end.length();
        if(word.length() == len){
            for(int z = 0; z < len; z++){
                if(end.charAt(z) != word.charAt(z)){
                    result = false;
                    z = len;
                }
            }
        } else{
            result = false;
        }
        return result;
    }
    public static void main (String[] args){
        gerador.setSeed(4);
        boolean t = true;
        while(t){
            String palavra = MyIO.readLine();
            if(isFIM(palavra)){
                break;
            }
            MyIO.println(Troca(palavra));
        }
    }
}
