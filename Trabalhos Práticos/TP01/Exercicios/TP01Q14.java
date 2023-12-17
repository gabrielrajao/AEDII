class TP01Q14b{
    //Fiquei com preguica de colocar todos os metodos recursivos, desculpa, mas é a mesma coisa sempre
    public static void fill(int[] vetor){
        for(int m = 0; m < vetor.length; m++){
            vetor[m] = -1;
        }
    }
    public static String Simpli (String x, int[] temp, boolean A, boolean B, boolean C, int opatual, int i, int indexops){
        String resultado = "";
            if(i >= x.length()){
                return resultado;
            }
                char index = x.charAt(i);
                if(index == 'A'){
                    if(A){
                        resultado += "1";
                    } else{
                        resultado += "0";
                    }
                }
                if(index == 'B'){
                    if(B){
                        resultado += "1";
                    } else{
                        resultado += "0";
                    }
                }
                if(index == 'C'){
                    if(C){
                        resultado += "1";
                    } else{
                        resultado += "0";
                    }
                }
                if(index == ')'){
                        resultado += ")";
                        opatual = -1;
                        if(indexops!= 0 && temp[indexops - 1] != -1){
                            indexops--;
                            opatual = temp[indexops];
                            temp[indexops] = -1;

                        }
                }
                if(index == ','){
                        if(opatual == 1){
                            resultado += "+";
                        }
                        if(opatual == 0){
                            resultado += "*";
                        }
                }
                if(index == 'n'){
                    i += 3;
                    resultado += "!(";
                    if(opatual == -1){
                            opatual = 2;
                    } else{
                        temp[indexops] = opatual;
                        opatual = 2;
                        indexops++;
                    }

                }
                if(index == 'a'){
                    i += 3;
                    resultado += "(";
                    if(opatual == -1){
                            opatual = 0;
                    } else{
                        temp[indexops] = opatual;
                        opatual = 0;
                        indexops++;
                    }

                }
                if(index == 'o'){
                    i += 2;
                    resultado += "(";
                    if(opatual == -1){
                            opatual = 1;
                    } else{
                        temp[indexops] = opatual;
                        opatual = 1;
                        indexops++;
                    }

                }


        return resultado + Simpli (x,temp,A,B,C,opatual,i + 1,indexops);
    }
    public static String Simpli (String x){
        boolean A = false;
        boolean B = false;
        boolean C = false;
        String resultado = "";
        int[] temp = new int[90];
        fill(temp);
        int opatual = -1;
        int i = 6;
        int indexops = 0;
        if(x.charAt(0)=='3'){
                if(x.charAt(6) == '1'){
                    C = true;
                    i+=2;
                }
            }
            if(x.charAt(2) == '1'){
                    A = true;
            }
            if(x.charAt(4) == '1'){
                    B = true;
            }
            if(i >= x.length()){
                return resultado;
            }
                char index = x.charAt(i);
                if(index == 'A'){
                    if(A){
                        resultado += "1";
                    } else{
                        resultado += "0";
                    }
                }
                if(index == 'B'){
                    if(B){
                        resultado += "1";
                    } else{
                        resultado += "0";
                    }
                }
                if(index == 'C'){
                    if(C){
                        resultado += "1";
                    } else{
                        resultado += "0";
                    }
                }
                if(index == ')'){
                        resultado += ")";
                        opatual = -1;
                        if(indexops!= 0 && temp[indexops - 1] != -1){
                            indexops--;
                            opatual = temp[indexops];
                            temp[indexops] = -1;

                        }
                }
                if(index == ','){
                        if(opatual == 1){
                            resultado += "+";
                        }
                        if(opatual == 0){
                            resultado += "*";
                        }
                }
                if(index == 'n'){
                    i += 3;
                    resultado += "!(";
                    if(opatual == -1){
                            opatual = 2;
                    } else{
                        temp[indexops] = opatual;
                        opatual = 2;
                        indexops++;
                    }

                }
                if(index == 'a'){
                    i += 3;
                    resultado += "(";
                    if(opatual == -1){
                            opatual = 0;
                    } else{
                        temp[indexops] = opatual;
                        opatual = 0;
                        indexops++;
                    }

                }
                if(index == 'o'){
                    i += 2;
                    resultado += "(";
                    if(opatual == -1){
                            opatual = 1;
                    } else{
                        temp[indexops] = opatual;
                        opatual = 1;
                        indexops++;
                    }

                }


        return resultado + Simpli (x,temp,A,B,C,opatual,i + 1,indexops);
    }
    public static String NOT(String x, int parenteses, int not, int i){
        String result = "";
        if(i >= x.length()){
            return result;
        }
                if(x.charAt(i)=='('){
                       result += "(";
                }
                if(x.charAt(i)=='!'){
                    not++;
                }
                if(x.charAt(i)=='1'){
                    if(not%2!=0){
                            result += "0";
                    }
                    else{

                           result += "1";
                    }
                }
                if(x.charAt(i)=='0'){
                    if(not%2!=0){
                            result += "1";
                    }
                    else{

                           result += "0";
                    }
                }
                if(x.charAt(i)=='*'){
                    if(not%2!=0){
                            result += "+";
                    }
                    else{

                           result += "*";
                    }
                }
                if(x.charAt(i)=='+'){
                    if(not%2!=0){
                            result += "*";
                    }
                    else{

                           result += "+";
                    }
                }
                if(x.charAt(i)==')'){
                    if(not>0){
                            not--;
                    }
                    if(i < x.length()- 1){
                        result += ")";
                    }
                }
        return result + NOT(x, parenteses, not, i + 1);
    }
    public static String NOT(String x){
        String result = "";
        int not = 0;
        int parenteses = 0;
        int i = 0;
        if(i >= x.length()){
            return result;
        }
                if(x.charAt(i)=='('){
                       result += "(";
                }
                if(x.charAt(i)=='!'){
                    not++;
                }
                if(x.charAt(i)=='1'){
                    if(not%2!=0){
                            result += "0";
                    }
                    else{

                           result += "1";
                    }
                }
                if(x.charAt(i)=='0'){
                    if(not%2!=0){
                            result += "1";
                    }
                    else{

                           result += "0";
                    }
                }
                if(x.charAt(i)=='*'){
                    if(not%2!=0){
                            result += "+";
                    }
                    else{

                           result += "*";
                    }
                }
                if(x.charAt(i)=='+'){
                    if(not%2!=0){
                            result += "*";
                    }
                    else{

                           result += "+";
                    }
                }
                if(x.charAt(i)==')'){
                    if(not>0){
                            not--;
                    }
                    if(i < x.length()- 1){
                        result += ")";
                    }
                }
        return result + NOT(x, parenteses, not, i + 1);
    }
    public static String Resolve (String x){
        String result = "";
        boolean parenteses = false;
        String temp = "";
        int soma = -1;
        char operador;
        for(int i = 0 ; i < x.length(); i++){
            char index = x.charAt(i);
            if(index == '(' ){
                temp   += index;
            }
            if(index == ')'){
                if(soma != -1){
                    temp += soma;
                }
                temp += index;
                soma = -1;
            }
            if(index == '1'){
                soma = 1;
            }
            if(index == '0'){
                soma = 0;
            }
            if(index == '+'){
                if(x.charAt(i-1) != ')'){
                    i++;
                    if(x.charAt(i) == '('){
                        if(soma != -1){
                        temp += soma;
                        }
                        temp += '+';
                        soma =-1;
                        i--;
                    } else{
                        if(x.charAt(i) == '1') {
                            soma += 1;
                        }
                        if(x.charAt(i) == '0') {
                            soma += 0;
                        }
                        if(soma>0){
                            soma = 1;
                        }

                    }
                } else{
                    temp+=index;
                }
            }
            if(index == '*'){
                if(x.charAt(i-1) != ')'){
                    i++;
                    if(x.charAt(i) == '('){
                        if(soma != -1){
                        temp += soma;
                        }
                        temp += '*';
                        soma =-1;
                        i--;
                    } else{
                        if(x.charAt(i) == '1') {
                            soma *= 1;
                        }
                        if(x.charAt(i) == '0') {
                            soma *= 0;
                        }
                        if(soma>0){
                            soma = 1;
                        }

                    }
                } else{
                    temp+=index;
                }
            }
            if(i + 1 == x.length() && soma != -1){
                temp+=soma;
            }

        }
        return temp;
    }
    public static String Remove (String x){
        String result = "";
        boolean one = false;
        do{
            one = false;
            for(int i = 0 ; i < x.length(); i++){
                if(i+2<x.length()){
                    if(x.charAt(i) == '(' && x.charAt(i+2)==')'){
                    result += x.charAt(i+1);
                    one = true;
                    i+=2;
                    }
                    else{
                        result += x.charAt(i);
                    }

                }
                else{
                        result += x.charAt(i);
                    }

            }
            x = result;
            result = "";
        }while(one);
        if(x.charAt(0) == '(' && x.length() == 2){
            x = "" + x.charAt(1);
        }
        return x;
    }
    public static boolean isFIM (String word){
        String end = "0";
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
        int r = 0;
        while(t){
            r++;
            String temp = "";
            String str = MyIO.readLine();
            if(isFIM(str)){
                break;
            }
            str = Simpli(str);
            str = NOT(str);

            while(str.length()!=1 && (str.charAt(0)!='0' && str.charAt(0)!='1' )){
                str = Resolve(str);
                str = Remove(str);


            }
            MyIO.println(str);

        }
    }

}
