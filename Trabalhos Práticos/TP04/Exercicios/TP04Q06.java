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
    public char letra;
    public boolean folha;
    public Jogador elemento;
    public No[] prox;
    
    public No(char letra, boolean folha, Jogador elemento){
        this.prox = new No[255];
        for(int i = 0; i < 255; i++){
            this.prox[i] = null;
        }
        this.letra = letra;
        this.folha = folha;
        this.elemento = elemento;
        
    }
    public No(char letra){
        this(letra,false,null);
    }
    public No(){
        this(' ',false,null);
    }
    
}

class Arvore{
    public No raiz;
    public int comparacoes;
    
    public Arvore(){
        raiz = new No();
    }
    
    public void Inserir(int index, Jogador elemento, No i) throws Exception{
        if(i.prox[elemento.getNome().charAt(index)] == null){
            if(elemento.getNome().length() <= index + 1){
                i.prox[elemento.getNome().charAt(index)] = new No(elemento.getNome().charAt(index), true, elemento);
            } else{
                i.prox[elemento.getNome().charAt(index)] = new No(elemento.getNome().charAt(index));
                Inserir( (index + 1), elemento, i.prox[(int) elemento.getNome().charAt(index)]);
            }
        } 
        else if(i.prox[elemento.getNome().charAt(index)].folha == false && elemento.getNome().length() > index + 1){
                Inserir((index + 1), elemento, i.prox[(int) elemento.getNome().charAt(index)]);
        } else{
            throw new Exception("ELEMENTO JA INSERIDO!");
        }
        
    }
    
    public void Inserir(Jogador elemento) throws Exception{
        Inserir(0, elemento, this.raiz);
    }
    
   private void Pesquisa(int index, String nome, No i){
       comparacoes++;
       if(i == null){
           System.out.println("NAO");
       } else{
           comparacoes++;
           if(index + 1 == nome.length()){
               System.out.println("SIM");
           } else {
               Pesquisa(index+1,nome,i.prox[(int) nome.charAt(index)]);
           }
       }
   }
    
    public void PesquisaSequencial(String nome){
        System.out.print(nome + " ");
        Pesquisa(0, nome,this.raiz);
    }
    
    
    public static No Juntar(No i, No j){
        if(j!=null){
            if(i == null){
                i = new No(j.letra,j.folha,j.elemento);
            }
            for(int x = 0; x < 255; x++){
                i.prox[x] = Juntar(i.prox[x], j.prox[x]);
            }
        }
        return i;
    }
    
    public static Arvore Merge(Arvore arv1, Arvore arv2){
        Arvore result = new Arvore();
        result.raiz = Juntar(result.raiz, arv1.raiz);
        result.raiz = Juntar(result.raiz, arv2.raiz);
        return result;
    }
    
}




public class Trie{
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
            Arvore arvore1 = new Arvore();
            Arvore arvore2 = new Arvore();

            while(!isFIM(entrada)){
                    try{
                        int index = Integer.parseInt(entrada);
                        Jogador p = lista.get(index + 1);
                        System.out.println(p.getId() + "\t" +p.getNome());
                        arvore1.Inserir(p);
                        entrada = MyIO.readLine();
                    } catch(Exception e){
                    }
            }
            entrada = MyIO.readLine();
            while(!isFIM(entrada)){
                    try{
                    
                        
                        int index = Integer.parseInt(entrada);
                        Jogador p = lista.get(index + 1);
                        System.out.println(p.getId() + "\t" +p.getNome());
                        arvore2.Inserir(p);
                        entrada = MyIO.readLine();
                    } catch(Exception e){
                    }
            }
            Arvore resultado = Arvore.Merge(arvore1,arvore2);
            
            arvore1 = null;
            arvore2 = null;
            
            entrada = MyIO.readLine();
            double inicio = now();
            while(!isFIM(entrada)){
                resultado.PesquisaSequencial(entrada);
                entrada = MyIO.readLine();
            }
            double fim = now();
            String arquivo = "801792\t"+(fim-inicio)/1000.0+"s\t"+resultado.comparacoes;
            Arq.openWriteClose("./801792_arvoreTrie.txt", arquivo); 
        }
}