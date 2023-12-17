class TP01Q06{

    //IS VOGAL

    public static boolean isVogal(char index){
        boolean result = false;
        if(index == 'A' || index == 'E' || index == 'I' || index == 'O' || index == 'U'){ result = true;}
        else{
            if(index == 'a' || index == 'e' || index == 'i' || index == 'o' || index == 'u' ){result = true;}
        }
        return result;
    }

    public static boolean isVogal(String word){
        boolean result = true;
        for(int i = 0; i < word.length(); i++){
            char index = word.charAt(i);
            if(!(isVogal(index))){
                result = false;
                i = word.length();
            }
        }
        return result;
    }

    //IS CONSONANT

    public static boolean isConso(String word){
        boolean result = true;
        for(int i = 0; i < word.length(); i++){
            char index = word.charAt(i);
            if((index < 'A' || index > 'Z')&&(index < 'a' || index > 'z')){
                result = false;
                i = word.length();
            } else{
                if(isVogal(index)){
                    result = false;
                    i = word.length();
                }
            }
        }
        return result;
    }

    //IS INT

    public static boolean isInteger(String word){
        boolean result = true;
        for(int i = 0; i < word.length(); i++){
            char index = word.charAt(i);
            if(index < '0' || index > '9'){
                result = false;
                i = word.length();
            }
        }
        return result;
    }

    //IS FLOAT

    public static boolean isFloat(String word){
        boolean result = true;
        boolean separator = false;
        for(int i = 0; i < word.length(); i++){
            char index = word.charAt(i);
            if(index < '0' || index > '9'){
                if(index == ',' || index == '.'){
                        if(separator){
                            result = false;
                            i = word.length();
                        }
                        separator = true;
                }
                else{
                    result = false;
                    i = word.length();
                }
            }
        }
        return result;
    }

    //IS FIM

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
        boolean t = true;
        String str = "";
        String result = "";
        while(t){
                str = "";
                result = "";
                str = MyIO.readLine();
                if(isFIM(str)){
                    break;
                }
                if(isVogal(str)){
                    result +="SIM ";
                } else{
                    result +="NAO ";
                }
                if(isConso(str)){
                    result +="SIM ";
                } else{
                    result +="NAO ";
                }
                if(isInteger(str)){
                    result +="SIM ";
                } else{
                    result +="NAO ";
                }
                if(isFloat(str)){
                    result +="SIM ";
                } else{
                    result +="NAO ";
                }
                MyIO.println(result);
        }
    }
}
