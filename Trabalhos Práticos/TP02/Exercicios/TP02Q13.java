import java.io.*;
import java.util.ArrayList;
import java.util.*;
public class MergeSort{

    private int id;
    private String nome;
    private int altura;
    private int peso;
    private String uni;
    private int Nasc;
    private String cidade;
    private String estado;

    public MergeSort(){
        id = -1;
        nome = "";
        altura = -1;
        peso = -1;
        uni = "";
        Nasc = -1;
        cidade = "";
        estado = "";
    }
    public MergeSort(int id, String nome, int altura, int peso, String uni, int Nasc, String cidade, String estado){
        this.id = id;
        this.nome = nome;
        this.altura = altura;
        this.peso = peso;
        this.uni = uni;
        this.Nasc = Nasc;
        this.cidade = cidade;
        this.estado = estado;
    }
    public void setId(int id){
        this.id = id;
    }
    public void setNome(String nome){
        this.nome = nome;
    }
    public void setAltura(int altura){
        this.altura = altura;
    }
    public void setPeso(int peso){
        this.peso = peso;
    }
    public void setUni (String uni){
        this.uni = uni;
    }
    public void setNasc (int Nasc){
        this.Nasc = Nasc;
    }
    public void setCidade(String cidade){
        this.cidade = cidade;
    }
    public void setEstado (String estado){
        this.estado = estado;
    }
    public int getId (){
        return id;
    }
    public int getAltura (){
        return altura;
    }
    public int getPeso (){
        return peso;
    }
    public int getNasc (){
        return Nasc;
    }
    public String getNome(){
        return nome;
    }
    public String getUni(){
        return uni;
    }
    public String getCidade(){
        return cidade;
    }
    public String getEstado(){
        return estado;
    }
    public void imprimir(){
        String altura ="" +this.altura;
        String peso =""+ this.peso;
        String Nasc = ""+ this.Nasc;
        String nome = this.nome;
        String uni = this.uni;
        String cidade = this.cidade;
        String estado = this.estado;
        if(this.altura == 0){
           altura = "nao informado";
        }
        if(this.peso == 0){
           peso = "nao informado";
        }
        if(this.Nasc == 0){
           Nasc = "nao informado";
        }
        if(this.nome.length()==0){
            nome = "nao informado";
        }
        if(this.uni.length()==0){
            uni = "nao informado";
        }
        if(this.cidade.length()==0){
            cidade = "nao informado";
        }
        if(this.estado.length()==0){
            estado = "nao informado";
        }
        System.out.println("["+id+" ## "+nome+" ## "+altura+" ## "+peso+" ## "+Nasc+" ## "+uni+ " ## "+cidade + " ## "+estado+"]");
    }
    public MergeSort clone(){
        return new MergeSort(this.id,this.nome,this.altura,this.peso,this.uni,this.Nasc,this.cidade,this.estado);
    }
public static ArrayList<MergeSort> ler(){
        Arq.openRead("/tmp/players.csv");
        ArrayList<MergeSort> listares = new ArrayList<MergeSort>();
        while(Arq.hasNext()==true){
            String str = Arq.readLine();
            int j = 0;
            int id = 0;
            while(str.charAt(j) != ','){
                id *=10;
                id += ((int)str.charAt(j)) - 48;
                j++;
            }
            j++;
            String nome = "";
            while(str.charAt(j) != ','){
                nome += str.charAt(j);
                j++;
            }
            j++;
            int altura = 0;
            while(str.charAt(j) != ','){
                altura *=10;
                altura += ((int)str.charAt(j)) - 48;
                j++;
            }
            j++;
            int peso = 0;
            while(str.charAt(j) != ','){
                peso *=10;
                peso += ((int)str.charAt(j)) - 48;
                j++;
            }
            j++;
            String uni = "";
            while(str.charAt(j) != ','){
                if(str.charAt(j) == '"'){
                        j++;
                        while(str.charAt(j) != '"'){
                            if(str.charAt(j) == ','){
                                uni += " -";
                            } else{
                                uni += str.charAt(j);
                            }
                            j++;

                        }
                        j++;
                }
                else{
                    uni += str.charAt(j);
                    j++;
                }

            }
            j++;
            int Nasc = 0;
            while(str.charAt(j) != ','){
                Nasc *=10;
                Nasc += ((int)str.charAt(j)) - 48;
                j++;
            }
            j++;
            String cidade = "";
            while(str.charAt(j) != ','){
                cidade += str.charAt(j);
                j++;
            }
            j++;
            String estado = "";
            while(j < str.length()){
                estado += str.charAt(j);
                j++;
            }
            listares.add(new MergeSort(id,nome,altura, peso, uni, Nasc, cidade, estado));
        }
        Arq.close();
        return listares;
    }
    public static int toNumber(String num){
        int res = 0;
        int j = 0;
        while(j < num.length()){
            res*=10;
            res += ((int) num.charAt(j)) - 48;
            j++;
        }
        return res;
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
    public static int contadorc = 0;
    public static int contadorm = 0;
    public static long now(){
        return new Date().getTime();
    }
    public static boolean strtoInt(String str, String busca){
        int i = 0;
        while((int)busca.charAt(i) == (int) str.charAt(i)){
            i++;
        }
        if((int)busca.charAt(i) < str.charAt(i) ){
            return true;
        }
        return false;
    }
    public static boolean ordenacao( int pos1, int pos2,ArrayList<MergeSort> lista){
        if(pos1 == -1){
            return true;
        }
        if(pos2 == -1){
            return false;
        }
        MergeSort MergeSort1 = lista.get(pos1);
        MergeSort MergeSort2 = lista.get(pos2);
        boolean result = false;
            String nome1 = MergeSort1.getUni();
            String nome2 = MergeSort2.getUni();
            int i = 0;
            contadorc++;
            if(nome1.length() == 0 && nome2.length() == 0){
                            i = 0;
                            nome1 = MergeSort1.getNome();
                            nome2 = MergeSort2.getNome();
                            while(nome1.charAt(i) == nome2.charAt(i)){
                                i++;
                            }
                            contadorc++;
                            if(nome1.charAt(i) > nome2.charAt(i)){
                                result = true;
                            }
                return result;
            }
            contadorc++;
            if(nome1.length()==0){
                return true;
            }
            contadorc++;
            if(nome2.length()==0){
                return false;
            }
            while(i < nome1.length() && i < nome2.length() && nome1.charAt(i) == nome2.charAt(i)){
                i++;
            }
            contadorc++;
            if(i < nome1.length() && i < nome2.length() && nome1.charAt(i) > nome2.charAt(i)){
                result = true;
            }
            contadorc++;
            if(i >= nome1.length() || i >= nome2.length()){
                    contadorc++;
                    if(nome1.length() > nome2.length()){
                        result = true;
                    } else{
                        contadorc++;
                        if(nome2.length() == nome1.length()){
                            i = 0;
                            nome1 = MergeSort1.getNome();
                            nome2 = MergeSort2.getNome();
                            while(nome1.charAt(i) == nome2.charAt(i)){
                                i++;
                            }
                            contadorc++;
                            if(nome1.charAt(i) > nome2.charAt(i)){
                                result = true;
                            }
                        }
                    }
            }
        return result;
    }
    public static void swap (int[] array, int pos1, int pos2){
        contadorm++;
        int temp = array[pos1];
        contadorm++;
        array[pos1] = array[pos2];
        contadorm++;
        array[pos2] = temp;
    }

    public static void intercalar(int[] array, ArrayList<MergeSort> lista, int esq, int meio,int dir){
        int tamEsq = (meio+1) - esq;
        int tamDir = dir - meio;

        int[] vetEsq = new int[tamEsq+1];
        int[] vetDir = new int[tamDir+1];
        vetEsq[tamEsq] = -1;
        vetDir[tamDir] = -1;


        for(int i = 0; i < tamEsq; i++){
            vetEsq[i] = array[i+esq];
            contadorm++;
        }
        for(int i = 0; i < tamDir; i++){
            vetDir[i] = array[i+(meio+1)];
            contadorm++;
        }
        int inEsq, inDir;
        inEsq = 0;
        inDir = 0;
        for(int i = esq; i <= dir; i++){
                                if(ordenacao(vetEsq[inEsq],vetDir[inDir],lista) ){
                                    array[i] = vetDir[inDir] ;
                                    inDir++;
                                    contadorm++;
                                }else{

                                    array[i] = vetEsq[inEsq] ;
                                    inEsq++;
                                    contadorm++;
                                }
        }
    }

     public static void mergeSort(int[] array, ArrayList<MergeSort> lista, int esq, int dir){
        if(dir>esq){
            int meio = (esq+dir)/2;
            mergeSort(array,lista,meio + 1,dir);
            mergeSort(array,lista,esq,meio);
            intercalar(array,lista,esq,meio,dir);
        }
    }

    public static void mergeSort(int[] array, ArrayList<MergeSort> lista, int tam){
        int dir = tam - 1;
        int esq = 0;
        if(dir>esq){
            int meio = (esq+dir)/2;
            mergeSort(array,lista,meio + 1,dir);
            mergeSort(array,lista,esq,meio);
            intercalar(array,lista,esq,meio,dir);
        }
    }


    public static void main (String[] args){
        ArrayList<MergeSort> lista = ler();
        String entrada = "";
        int[] search = new int[600];
        int tam =0;
        while(!isFIM(entrada = MyIO.readLine())){
                int num = toNumber(entrada);
                num++;
                search[tam] = num;
                tam++;
        }
        int[] pesq = new int[tam];
        for(int i = 0; i < tam; i++){
            pesq[i] = search[i];
        }
        search = null;

        double inicio = now();
        mergeSort(pesq,lista,tam);
        double fim = now();
        Arq.openWriteClose("801792_megesort.txt","801792\t"+contadorc+"\t"+contadorm+"\t"+(fim-inicio)/1000.0+"s");
        for(int i = 0; i < tam; i++){
           (lista.get(pesq[i])).imprimir();
        }

        }

    }

