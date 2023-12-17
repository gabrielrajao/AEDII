import java.io.*;
import java.util.ArrayList;
import java.util.*;
public class CountingSort{

    private int id;
    private String nome;
    private int altura;
    private int peso;
    private String uni;
    private int Nasc;
    private String cidade;
    private String estado;

    public CountingSort(){
        id = -1;
        nome = "";
        altura = -1;
        peso = -1;
        uni = "";
        Nasc = -1;
        cidade = "";
        estado = "";
    }
    public CountingSort(int id, String nome, int altura, int peso, String uni, int Nasc, String cidade, String estado){
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
    public CountingSort clone(){
        return new CountingSort(this.id,this.nome,this.altura,this.peso,this.uni,this.Nasc,this.cidade,this.estado);
    }
    public static ArrayList<CountingSort> ler(){
        Arq.openRead("/tmp/players.csv");
        ArrayList<CountingSort> listares = new ArrayList<CountingSort>();
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
            listares.add(new CountingSort(id,nome,altura, peso, uni, Nasc, cidade, estado));
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
    public static boolean ordenacao(int[] array, int pos1, int pos2,ArrayList<CountingSort> lista){
        CountingSort jogador1 = lista.get(array[pos1]);
        CountingSort jogador2 = lista.get(array[pos2]);
        boolean result = false;
            String nome1 = jogador1.getNome();
            String nome2 = jogador2.getNome();
            int i = 0;
            while(nome1.charAt(i) == nome2.charAt(i)){
                i++;
            }
            contadorc++;
            if(nome1.charAt(i) > nome2.charAt(i)){
                result = true;
            }
        return result;
    }
    public static void ordenarAlfabeto(int[] array, ArrayList<CountingSort> lista, int tam ){
        for(int i = 0; i < tam; i++){
            int menor = i;
            for(int j = i+1; j< tam; j++){
                if(ordenacao(array,menor,j,lista)){
                    menor = j;
                }
            }
            swap(array,i,menor);
        }
    }

    public static void swap (int[] array, int pos1, int pos2){
        contadorm++;
        int temp = array[pos1];
        contadorm++;
        array[pos1] = array[pos2];
        contadorm++;
        array[pos2] = temp;
    }


    public static void toZero(int[] array, int tam){
        for(int i = 0; i < tam; i++){
            array[i] = 0;
        }
    }

    public static int getNumber(int altura, String nome){
        int result = altura;
        return result;
    }

    public static int getMaior(int[] array, ArrayList<CountingSort> lista, int tam){
        int maior = getNumber(lista.get(0).getAltura(), lista.get(0).getNome());
        for(int i = 1; i < tam; i++){
            int index = getNumber(lista.get(i).getAltura(), lista.get(i).getNome());
            contadorc++;
            if(index > maior){
                maior = index;
            }
        }
        return maior;
    }

    public static int[] countingArray(int[] array, ArrayList<CountingSort> lista, int tam){

        int countingsize = getMaior(array,lista,tam);
        int[] counting = new int[countingsize];
        toZero(counting, countingsize);
        for(int i = 0; i < tam; i++){
            int altura = getNumber(lista.get(array[i]).getAltura(), lista.get(array[i]).getNome());
            counting[altura]++;
        }
        for(int i = 1; i < counting.length; i++){
            counting[i] += counting[i - 1];
        }
        return counting;
    }

    public static int[] countingSort (int[] array, ArrayList<CountingSort> lista, int tam ){
        int[] counting = countingArray(array,lista,tam);
        int[] result = new int[tam];
        for(int i = tam-1 ; i >= 0; i--){
            int altura = getNumber(lista.get(array[i]).getAltura(), lista.get(array[i]).getNome());
            contadorm++;
            int index = counting[altura];
            contadorm++;
            result[index - 1] = array[i];
            counting[altura]--;
        }
        return result;
    }


    public static void main (String[] args){
        ArrayList<CountingSort> lista = ler();
        String entrada = "";
        int[] pesq = new int[600];
        int tam =0;
        while(!isFIM(entrada = MyIO.readLine())){
                int num = toNumber(entrada);
                num++;
                pesq[tam] += num;
                tam++;
        }
        ordenarAlfabeto(pesq,lista,tam);
        double inicio = now();
        pesq = countingSort(pesq,lista,tam);
        double fim = now();
        Arq.openWriteClose("801792_countingsort.txt","801792\t"+contadorc+"\t"+contadorm+"\t"+(fim-inicio)/1000.0+"s");
        for(int i = 0; i < tam; i++){

           (lista.get(pesq[i])).imprimir();
        }

    }
}
