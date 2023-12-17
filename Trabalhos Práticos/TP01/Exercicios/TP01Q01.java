class TP01Q01 {
    public static boolean Pali (String word){
        boolean result = true;
        for(int i = 0; i < (word.length())/2; i++ ){
            int reverse = (word.length() - 1) - i;
            char palindro = word.charAt(reverse);
            if(palindro != word.charAt(i)){
                i = word.length();
                result = false;
            }
        }
        return result;
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
    public static void main(String[] args){
        MyIO.setCharset("UTF-8");
        String palavra = "";
        boolean t = true;
        while(t){
            palavra = MyIO.readLine();
            if(isFIM(palavra)){
                break;
            }
            if(Pali(palavra)){
                MyIO.println("SIM");
            } else{
                MyIO.println("NAO");
            }
        }

    }
}
