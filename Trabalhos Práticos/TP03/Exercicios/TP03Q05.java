import java.io.*;
import java.util.ArrayList;
import java.util.*;

class Jogador{

    private int id;
    private String nome;
    private int altura;
    private int peso;
    private String uni;
    private int Nasc;
    private String cidade;
    private String estado;

    public Jogador(){
        id = -1;
        nome = "";
        altura = -1;
        peso = -1;
        uni = "";
        Nasc = -1;
        cidade = "";
        estado = "";
    }
    public Jogador(int id, String nome, int altura, int peso, String uni, int Nasc, String cidade, String estado){
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
        System.out.println(" ## "+nome+" ## "+altura+" ## "+peso+" ## "+Nasc+" ## "+uni+ " ## "+cidade + " ## "+estado+" ##");
    }
    public static ArrayList<Jogador> ler(){
        Arq.openRead("/tmp/players.csv");
        ArrayList<Jogador> listares = new ArrayList<Jogador>();
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
                uni += str.charAt(j);
                j++;
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
            listares.add(new Jogador(id,nome,altura, peso, uni, Nasc, cidade, estado));
        }
        Arq.close();
        return listares;
    }
}

class Celula{
    private Jogador elemento;
    private Celula prox;

    //Construtor
    public Celula(){
        this(null);
    }

    public Celula(Jogador p){
        this.elemento = p;
        this.prox = null;
    }

    //gets
    public Jogador getElemento(){
        return this.elemento;
    }
    public Celula getProx(){
        return this.prox;
    }
    //sets
    public void setElemento(Jogador p){
        this.elemento = p;
    }
    public void setProx(Celula prox){
        this.prox = prox;
    }

}

class Lista{
    private Celula inicio;
    private Celula fim;
    private int tamanho;

    public Lista(){
     inicio = new Celula();
     fim = inicio;
     tamanho = 0;
    }

    //insert

    public void InsertInicio(Jogador x){
        Celula temp = new Celula(x);
        temp.setProx(inicio.getProx());
        inicio.setProx(temp);
        if(inicio == fim){
            fim = inicio.getProx();
        }
        tamanho++;
    }

    public void InsertFinal(Jogador x){
        Celula temp = new Celula(x);
        fim.setProx(temp);
        fim = temp;
        tamanho++;

    }
    public void Insert(int pos, Jogador x){
        if(pos == 0){
            InsertInicio(x);
        } else if (pos == tamanho){
            InsertFinal(x);
        } else if ( pos < tamanho ){
            Celula temp = new Celula (x);
            Celula index = inicio;
            for(int i = 0; i < pos; i++){
                index = index.getProx();
            }
            Celula temp2 = index.getProx();
            index.setProx(temp);
            temp.setProx(temp2);
            tamanho++;
        }
    }

    //remove

    public void RemoveInicio (){
        Celula temp = (inicio.getProx());
        System.out.println("(R) " +(temp.getElemento()).getNome());
        temp = temp.getProx();

        (inicio.getProx()).setProx(null);
        inicio.setProx(temp);
        tamanho--;
    }

    public void RemoveFinal (){
        Celula temp = inicio;
        while(temp.getProx() != fim){
            temp = temp.getProx();
        }
        System.out.println("(R) " +(fim.getElemento()).getNome());
        fim = temp;
        temp.setProx(null);
        tamanho--;
    }

    public void Remove(int pos){
        if(pos == 0){
            RemoveInicio();
        } else if(pos == tamanho - 1){
            RemoveFinal();
        } else if(pos < tamanho - 1){
            Celula index = inicio;
            for(int i = 0; i < pos; i++){
                index = index.getProx();
            }
            Celula temp = index.getProx();
            System.out.println("(R) " +(temp.getElemento()).getNome());
            index.setProx(temp.getProx());
            temp.setProx(null);
            temp = null;
            index = null;
            tamanho--;
        }
    }

    //gets
    public Celula getInicio(){
        return this.inicio;
    }
    public int getTamanho(){
        return this.tamanho;
    }

}

public class ListaFlexivel{

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
    public static void main (String[] args){
        ArrayList<Jogador> lista = Jogador.ler();
        Lista list = new Lista();
        String entrada;
        while(!isFIM(entrada = MyIO.readLine())){
            int num = toNumber(entrada);
            list.InsertFinal(lista.get(num + 1));

        }


        int IorR = toNumber(MyIO.readLine());
        for(int i = 0; i < IorR; i++){
            String metodo = MyIO.readString();
            if(metodo.charAt(0) == 'R'){
                if(metodo.charAt(1) == '*'){
                    int pos = MyIO.readInt();
                    list.Remove(pos);
                }

                if(metodo.charAt(1) == 'I'){
                    list.RemoveInicio();
                }

                if(metodo.charAt(1) == 'F'){
                    list.RemoveFinal();
                }


            } else{
                if(metodo.charAt(1) == '*'){
                    int pos = MyIO.readInt();
                    Jogador x = lista.get(MyIO.readInt() +1);
                    list.Insert(pos, x);
                }

                if(metodo.charAt(1) == 'I'){
                    Jogador x = lista.get(MyIO.readInt() +1);
                    list.InsertInicio(x);
                }

                if(metodo.charAt(1) == 'F'){
                    Jogador x = lista.get(MyIO.readInt() +1);
                    list.InsertFinal(x);
                }
            }

        }
        Celula index = list.getInicio();
        for(int i = 0; i < list.getTamanho(); i++){
            System.out.print("["+i+"]");
            index = index.getProx();
            (index.getElemento()).imprimir();
        }

    }
}
