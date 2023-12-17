class TP01Q03 {
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
    public static boolean isLetra (char c){
        boolean result = false;
        if((int)c >= (int)'A' && (int)c <= (int)'Z') { result = true; }
        if((int)c >= (int)'a' && (int) c <= (int)'z') { result = true; }
        return result;
    }
    public static boolean isXYZ (char c){
        boolean result = false;
        if((int)c >= (int)'x' && (int)c <= (int)'z') { result = true; }
        if((int)c >= (int)'X' && (int) c <= (int)'Z') { result = true; }
        return result;
    }
    public static String Cifra (String palavra){
        String cifrada = "";
        for(int i = 0; i < palavra.length(); i++){
            char index = palavra.charAt(i);
            index = (char) ( (int)index + 3);
            cifrada += index;
        }
        return cifrada;
    }
     public static void main(String[] args){
        String palavra = "";
        boolean t = true;
        while(t){
            palavra = MyIO.readLine();
            if(isFIM(palavra)){
                break;
            }
            MyIO.println(Cifra(palavra));
        }

    }
}
