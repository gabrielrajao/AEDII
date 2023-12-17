class TP01Q10 {

    public static boolean Pali (String word, int tamanho, int index){
        if(tamanho <= index){
            return true;
        }
        else{
            if(word.charAt(tamanho) == word.charAt(index) ){
                    return Pali(word, tamanho - 1, index + 1);
            }
            else{
                return false;
            }
        }
    }
    public static boolean Pali (String word){
        int index = 0;
        int tamanho = word.length() - 1;
        if(tamanho <= index){
            return true;
        }
        else{
            if(word.charAt(tamanho) == word.charAt(index) ){
                    return Pali(word, tamanho - 1, index + 1);
            }
            else{
                return false;
            }
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

    public static void main (String[] args){
        boolean t = true;
        String word = "";
        while(t){
            word = MyIO.readLine();
            if(isFIM(word)){
                t = false;
            }else{
                if(Pali(word)){
                    MyIO.println("SIM");
                } else{
                    MyIO.println("NAO");
                }
            }
        }

    }
}
