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

class No2{
    private int elemento;
    private No raiz;
    private No2 esq;
    private No2 dir;
    
    public No2(int elemento){
        this.elemento = elemento;
        this.raiz = null;
        this.esq = null;
        this.dir = null;
    }
    
    public No getRaiz(){
        return raiz;
    }
    public No2 getEsq(){
        return esq;
    }
    public No2 getDir(){
        return dir;
    }
    public int getElemento(){
        return elemento;
    }
    
    public void setRaiz(No raiz){
        this.raiz = raiz;
    }
    public void setEsq (No2 esq){
        this.esq = esq;
    }
    public void setDir (No2 dir){
        this.dir = dir;
    }
    public void setElemento (int elemento){
        this.elemento = elemento;
    }
    
}


class ArvoreBinaria{
    
    private No2 raiz;
    private int comparacoes;
    
    public ArvoreBinaria() throws Exception{
        this.comparacoes = 0;
        int[] array = new int[]{7, 3, 11, 1, 5, 9, 13, 0, 2, 4, 6, 8, 10, 12, 14};
        for(int i = 0; i < array.length; i++){
            this.InsereNo2(array[i]);
        }
    }
    
    public int getComparacoes(){
        return this.comparacoes;
    }
    
    public No2 InsereNo2(int elemento, No2 x) throws Exception{
        comparacoes+=3;
        if( x == null){
            x = new No2(elemento);
            comparacoes-=2;
        }
        else if( x.getElemento() < elemento){
            x.setDir( InsereNo2(elemento, x.getDir()) );
            comparacoes-=1;
        }
        else if( x.getElemento() > elemento){
            x.setEsq( InsereNo2(elemento, x.getEsq()) );
        }
        else{
            throw new Exception("ERRO: item ja inserido (No2)");
        }
        return x;
    }
    
    public void InsereNo2(int elemento) throws Exception{
        this.raiz = InsereNo2(elemento, this.raiz);
    }
    
    
    public int HashNo2(int x){
        return x%15;
    }
    
    
    public No2 BuscaNo2(int hash, No2 x) throws Exception{
        comparacoes+=3;
        if(hash == x.getElemento()){
            comparacoes-=2;
            return x;
            
        }
        else if(hash < x.getElemento()){
            comparacoes-=1;
            return BuscaNo2(hash, x.getEsq());
            
            
        }
        else if(hash > x.getElemento()){
            return BuscaNo2(hash, x.getDir());
        }
        else{
            throw new Exception("ERRO durante pesquisa");
        }
    }
    
    public No2 BuscaNo2(int altura) throws Exception{
        int hash = HashNo2(altura);
        return BuscaNo2(hash, this.raiz);
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
    
    public No InsereNo(Jogador elemento, No x) throws Exception{
        comparacoes+=3;
        if( x == null ){
            x = new No(elemento);
            comparacoes-=2;
        }
        else if( ComparaStrings(elemento.getNome(), x.getElement().getNome()) == 1 ){
            x.setDir(InsereNo(elemento, x.getDir()));
            comparacoes-=1;
        }
        else if( ComparaStrings(elemento.getNome(), x.getElement().getNome()) == -1 ){
            x.setEsq(InsereNo(elemento, x.getEsq()));
        }
        else {
            throw new Exception("ERRO: Jogador ja se encontra na arvore!");
        }
        
        return x;
    }            
    
    public void InsereNo(Jogador elemento) throws Exception{
        No2 temp = BuscaNo2(elemento.getAltura());
        temp.setRaiz( InsereNo(elemento, temp.getRaiz()));
    }
    public boolean MostrarNo(String nome, No x){
        boolean result = false;
        comparacoes++;
        if(x!=null && result == false){
            comparacoes++;
            if(nome.equals(x.getElement().getNome())){
                result = true;
            }
            comparacoes++;
            if(result == false){
                System.out.print("ESQ ");
                result = MostrarNo(nome, x.getEsq());
            }
            comparacoes++;
            if(result == false){
                System.out.print("DIR ");
                result = MostrarNo(nome, x.getDir());
            }
        }
        return result;
    }
    
    
    
    public boolean MostrarNo2(String nome, No2 x){
        boolean result = false;
        comparacoes++;
        if(x!=null && result == false){
            result = MostrarNo(nome, x.getRaiz());
            comparacoes++;
            if(result == false){
                
                    System.out.print("esq ");
                    result = MostrarNo2(nome, x.getEsq());
              
            }
            comparacoes++;
            if(result == false){
                
              
                    System.out.print("dir ");
                    result = MostrarNo2(nome, x.getDir());
                
            }
        }
        return result;
    }

    
    public void Mostrar(String jogador){
        System.out.print(jogador + " raiz ");
        comparacoes++;
        if(MostrarNo2(jogador, this.raiz)){
            System.out.println("SIM");
        } else{
            System.out.println("NAO");
        }
    }
    
    
}

public class ArvoreBinariaQ02{
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
        try{
            ArrayList<Jogador> lista = Jogador.ler();
            String entrada = MyIO.readLine();
            ArvoreBinaria arvore = new ArvoreBinaria();

            while(!isFIM(entrada)){
                 int index = Integer.parseInt(entrada);
                 Jogador p = lista.get(index + 1);
                 arvore.InsereNo(p);
                 entrada = MyIO.readLine();


            }
            entrada = MyIO.readLine();
            double inicio = now();
            while(!isFIM(entrada)){
                arvore.Mostrar(entrada);
                entrada = MyIO.readLine();
            }
            double fim = now();
            String arquivo = "801792\t"+(fim-inicio)/1000.0+"s\t"+arvore.getComparacoes();
            Arq.openWriteClose("./801792_arvoreArvore.txt", arquivo); 
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}