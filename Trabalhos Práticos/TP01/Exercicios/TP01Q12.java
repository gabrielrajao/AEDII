class TP01Q12 {
    public static String cifrar(String word, int index){
        String result = "";
        index+=1;
        result += (char)(((int) word.charAt(index)) + 3);
        if(index + 1 < word.length()){
            return result + cifrar(word, index);
        }
        else{
            return result;
        }
    }
    public static String cifrar(String word){
        String result = "";
        int index = 0;
        result += (char)(((int) word.charAt(index)) + 3);
        if(index + 1 < word.length()){
            return result + cifrar(word, index);
        }
        else{
            return result;
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
    public static void Recursiva (){
        String word = "";
        word = MyIO.readLine();
        if(!isFIM(word)){
                MyIO.println(cifrar(word));
                Recursiva();
        }
    }
    public static void main (String[] args){
        Recursiva();
    }
}
