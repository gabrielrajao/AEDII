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
    public Jogador clone(){
        return new Jogador(this.id,this.nome,this.altura,this.peso,this.uni,this.Nasc,this.cidade,this.estado);
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
            listares.add(new Jogador(id,nome,altura, peso, uni, Nasc, cidade, estado));
        }
        Arq.close();
        return listares;
    }
}

class No{
    private Jogador element;
    private No dir;
    private No esq;

    public No (){
        this.element = null;
        this.dir = null;
        this.esq = null;
    }

    public No(Jogador elemento){
        this.element = elemento;
        this.dir = null;
        this.esq = null;
    }

    public No getDir(){
        return this.dir;
    }
    public No getEsq(){
        return this.esq;
    }
    public Jogador getElement(){
        return this.element;
    }

    public void setDir(No prox){
        this.dir = prox;
    }
    public void setEsq(No ant){
        this.esq = ant;
    }
    public void setElement(Jogador element){
        this.element = element;
    }
}

class ArvoreBinaria{
    private No raiz;
    private int comparacoes;
    
    public ArvoreBinaria(){
        this.raiz = null;
        comparacoes = 0;
    }
    
    public int getComparacoes(){
        return this.comparacoes;
    }
    

    public static int ComparaStrings(String x, String x2){
        int result = -1;
        int i = 0;
        while(i < x.length() && i < x2.length() && x.charAt(i) == x2.charAt(i) ){
            i++;
        }
        if( i == x.length() && i == x2.length()){
            result = 0;
        }
        else if( i == x2.length() || ( x.length() > i && x2.charAt(i) < x.charAt(i)) ){
            result = 1;
        }

        return result;
    }

    public No Inserir(Jogador elemento, No x) throws Exception{
        comparacoes += 3;
        if( x == null ){
            x = new No(elemento);
            comparacoes-=2;
        }
        else if( ComparaStrings(elemento.getNome(), x.getElement().getNome()) == 1 ){
            x.setDir(Inserir(elemento, x.getDir()));
            comparacoes-=1;
        }
        else if( ComparaStrings(elemento.getNome(), x.getElement().getNome()) == -1 ){
            x.setEsq(Inserir(elemento, x.getEsq()));
        }
        else {
            throw new Exception("ERRO: Jogador ja se encontra na arvore!");
        }

        return x;
    }

    public void Inserir(Jogador elemento) throws Exception{
        this.raiz = Inserir(elemento, raiz);
    }
    public void PesquisaSequencial(String nome, No x){
        comparacoes+=3;
        if( x == null ){
            System.out.println("NAO");
            comparacoes-=2;
        }
        else if( ComparaStrings(nome, x.getElement().getNome()) == 1 ){
            System.out.print("dir ");
            PesquisaSequencial(nome, x.getDir());
            comparacoes-=1;
        }
        else if( ComparaStrings(nome, x.getElement().getNome()) == -1 ){
            System.out.print("esq ");
            PesquisaSequencial(nome, x.getEsq());
        }
        else{
            System.out.println("SIM");
        }
    }


    public void PesquisaSequencial(String nome){
        System.out.print(nome + " ");
        System.out.print("raiz ");
        PesquisaSequencial(nome, this.raiz);
    }
    
    private int CaminharArvore(int index, ArrayList<Jogador> array, No i){
        if(i!= null){
            index = CaminharArvore(index,array,i.getEsq());
            array.set(index, i.getElement());
            index++;
            index = CaminharArvore(index,array,i.getDir());
        }
        return index;
    }
    
    public static int TreeSort(ArrayList<Jogador> lista) throws Exception{
        ArvoreBinaria arv = new ArvoreBinaria();
        for(int i = 0; i < lista.size(); i++){
            arv.Inserir(lista.get(i));
        }
        
        arv.CaminharArvore(0,lista, arv.raiz);
        
        return arv.getComparacoes();
    }


}

public class TreeSort{
    public static boolean isFIM(String x){
        String fim = "FIM";
        boolean result = true;
        if( x.length() != fim.length()){
            result = false;
        } else{
            for(int i = 0; i < x.length(); i++){
                if(x.charAt(i) != fim.charAt(i)){
                    result = false;
                }
            }
        }
        return result;
    }
    public static long now(){
      return new Date().getTime();
   }

    public static void main (String[] args){
        ArrayList<Jogador> lista = Jogador.ler();
        String entrada = MyIO.readLine();
        ArrayList<Jogador> array = new ArrayList<Jogador>();
        while(!isFIM(entrada)){
                try{
                    int index = Integer.parseInt(entrada);
                    Jogador p = lista.get(index + 1);
                    array.add(p);
                    entrada = MyIO.readLine();
                } catch(Exception e){
                }
        }
        double inicio = now();
        int comp=0;
        try{
            comp  = ArvoreBinaria.TreeSort(array);
        } catch(Exception e){}
        double fim = now();
        String arquivo = "801792\t"+(fim-inicio)/1000.0+"s\t"+comp;
        Arq.openWriteClose("./801792_treesort.txt", arquivo);
        for(int i = 0; i < array.size(); i++){
            System.out.println(array.get(i).getNome());
        }
    }
}