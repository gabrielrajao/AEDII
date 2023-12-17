import java.util.Random;
class TP01Q13 {
    public static Random gerador = new Random();
    public static char Randomchar(){
        char result =  (char) (97 +  (Math.abs(gerador.nextInt()) % 26));
        return result;
    }
    public static String Troca(String palavra, int index, char letra1, char letra2){
        String trocada = "";
        char i = palavra.charAt(index);
        if((i) == letra1){
            i = letra2;
        }
        trocada += i;
        if(index + 1 < palavra.length()){
                return trocada + Troca(palavra, index+1, letra1,letra2);
        } else{
            return trocada;
        }
    }
    public static String Troca(String palavra){
        char letra1 = Randomchar();
        char letra2 = Randomchar();
        String trocada = "";
        int index = 0;
        char i = palavra.charAt(index);
        if((i) == letra1){
            i = letra2;
        }
        trocada += i;
        if(index + 1 < palavra.length()){
                return trocada + Troca(palavra, index+1,letra1,letra2);
        } else{
            return trocada;
        }
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
    public static void Recursiva(){
        String palavra = MyIO.readLine();
        if(!isFIM(palavra)){
            MyIO.println(Troca(palavra));
            Recursiva();
        }


    }
    public static void main (String[] args){
        gerador.setSeed(4);
        Recursiva();
    }
}
