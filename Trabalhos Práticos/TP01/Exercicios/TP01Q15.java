class TP01Q15{
    public static boolean isLetter (char a){
        boolean result = false;
        if((a >= 'A' && a <= 'Z') || (a >= 'a' && a <= 'z')){ result = true;}
        return result;
    }
    public static boolean isVogal(char a){
        boolean result = false;
        if (a == 'A' || a == 'a' || a == 'E' || a=='e' || a == 'I' || a=='i' || a =='O' || a == 'o' || a== 'U' || a=='u'){
            result = true;
        }
        return result;
    }
    public static boolean isNumber(char a){
        boolean result = false;
        if(a >= '0' && a <= '9'){
            result = true;
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
    public static void Tester (String word, boolean vogais, boolean consoantes, boolean numero, boolean real, boolean virgula,  int index){
        index += 1;

        if(index == word.length()||(real == false && consoantes == false && vogais == false && numero == false)){
            String result = "";
            if(vogais){
                result += "SIM ";
            } else{
                result += "NAO ";
            }
            if(consoantes){
                result += "SIM ";
            } else{
                result += "NAO ";
            }
            if(numero){
                result += "SIM ";
            } else{
                result += "NAO ";
            }
            if(real){
                result += "SIM";
            } else{
                result += "NAO";
            }
            MyIO.println(result);
        }else{
            char i = word.charAt(index);
            if(isLetter(i) && (vogais || consoantes)){
                real = false;
                numero = false;
                if(isVogal(i)){
                    consoantes = false;
                } else{
                    vogais = false;
                }
            } else{
                vogais = false;
                consoantes = false;
                if(!isNumber(i)){
                    numero = false;
                    if(i == ',' || i == '.'){
                        if(virgula){
                            real = false;
                        } else{
                            virgula = true;
                        }
                    } else {
                        real = false;
                    }
                }
            }
            Tester (word, vogais, consoantes, numero, real, virgula, index);
        }

    }
    public static void Tester (String word){
        int index = 0;
        boolean real = true, virgula = false, numero = true, vogais = true, consoantes = true;

        if(index == word.length()||(real == false && consoantes == false && vogais == false && numero == false)){
            String result = "";
            if(vogais){
                result += "SIM ";
            } else{
                result += "NAO ";
            }
            if(consoantes){
                result += "SIM ";
            } else{
                result += "NAO ";
            }
            if(numero){
                result += "SIM ";
            } else{
                result += "NAO ";
            }
            if(real){
                result += "SIM";
            } else{
                result += "NAO";
            }
            MyIO.println(result);
        }else{
            char i = word.charAt(index);
            if(isLetter(i) && (vogais || consoantes)){
                real = false;
                numero = false;
                if(isVogal(i)){
                    consoantes = false;
                } else{
                    vogais = false;
                }
            } else{
                vogais = false;
                consoantes = false;
                if(!isNumber(i)){
                    numero = false;
                    if(i == ',' || i == '.'){
                        if(virgula){
                            real = false;
                        } else{
                            virgula = true;
                        }
                    } else {
                        real = false;
                    }
                }
            }
            Tester (word, vogais, consoantes, numero, real, virgula, index);

        }

    }
    public static void Recursiva (){
        String word = "";
        word = MyIO.readLine();
        if(!isFIM(word)){
            Tester(word);
            Recursiva();
        }
    }
    public static void main (String[] args){
        Recursiva();
    }
}
