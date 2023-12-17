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
    public Jogador elemento;
    public No esq;
    public No dir;
    public boolean cor;
    
    
    public No(Jogador elemento){
        this(elemento,null,null,false);
    }
    
    public No(Jogador elemento, boolean cor){
        this(elemento,null,null,cor);
    }
    
    public No(Jogador elemento, No esq, No dir, boolean cor){
        this.elemento = elemento;
        this.esq = esq;
        this.dir = dir;
        this.cor = cor;
    }
    
}

class Arvore{
    public int comparacoes;
    public No raiz;
    
    public Arvore(){
        this.raiz = null;
        this.comparacoes = 0;
    }
    
    public void Inserir(Jogador x) throws Exception{
        if(raiz == null){
            raiz = new No(x);
        }
        else if(raiz.esq == null && raiz.dir == null){
            int comparacao = x.getNome().compareTo(raiz.elemento.getNome());
            
            if(comparacao >= 1){
                raiz.dir = new No(x);
            } else if (comparacao <= -1){
                raiz.esq = new No(x);
            } else {
                throw new Exception("ELEMENTO JA INSERIDO");
            }
        }
        else if(raiz.esq == null){
            int comparacao = x.getNome().compareTo(raiz.elemento.getNome());
            if(comparacao <= -1){
                raiz.esq = new No(x);
            } else if(comparacao!= 0){
                comparacao = x.getNome().compareTo(raiz.dir.elemento.getNome());
                if(comparacao <= -1){
                    raiz.esq = new No(raiz.elemento);
                    raiz.elemento = x;
                } else if(comparacao >= 1) {
                    raiz.esq = new No(raiz.elemento);
                    raiz.elemento = raiz.dir.elemento;
                    raiz.dir.elemento = x;
                    
                } else{
                    throw new Exception("ELEMENTO JA INSERIDO");
                }
                
            }else{
                throw new Exception("ELEMENTO JA INSERIDO");
            }
            raiz.dir.cor = raiz.esq.cor = false;
        }
        else if(raiz.dir == null){
            int comparacao = x.getNome().compareTo(raiz.elemento.getNome());
            if(comparacao >= 1){
                raiz.dir = new No(x);
            } else if(comparacao!=0){
                comparacao = x.getNome().compareTo(raiz.esq.elemento.getNome());
                if(comparacao >= 1){
                    raiz.dir = new No(raiz.elemento);
                    raiz.elemento = x;
                }
                else if(comparacao <= -1){
                    raiz.dir = new No(raiz.elemento);
                    raiz.elemento = raiz.esq.elemento;
                    raiz.esq.elemento = x;
                } else{
                    throw new Exception("ELEMENTO JA INSERIDO");
                }
            }
            else{
                throw new Exception("ELEMENTO JA INSERIDO");
            }
            raiz.dir.cor = raiz.esq.cor = false;
                        
        }
        else{
            Inserir(x, null,null,null,raiz);
        }
        raiz.cor = false;
        
    }
    
    
    private void Inserir(Jogador elemento, No bisavo, No avo, No pai, No i) throws Exception{
        if(i == null){
            int comparacao = elemento.getNome().compareTo(pai.elemento.getNome());
            if(comparacao >= 1){
                i = pai.dir = new No(elemento,true);
            } else if(comparacao <= -1){
                i = pai.esq = new No(elemento,true);
            } else {
                throw new Exception("ELEMENTO JA INSERIDO");
            }
            if(pai.cor == true){
                balancear(bisavo,avo,pai,i);
            }
        } else{
            if(i.esq != null && i.dir != null && i.dir.cor == true && i.esq.cor == true){
                i.cor = true;
                i.esq.cor = i.dir.cor = false;
                if(i == raiz){
                    i.cor = false;
                } else if(pai.cor == true){
                    balancear(bisavo,avo,pai,i);
                }
            }
            int comparacao = elemento.getNome().compareTo(i.elemento.getNome());
            if(comparacao >= 1){
                Inserir(elemento,avo,pai,i,i.dir);
            } else if(comparacao <= -1){
                Inserir(elemento,avo,pai,i,i.esq);
            } else{
                throw new Exception("ELEMENTO JA INSERIDO");
            }
            
        }
    }
    
    private void balancear(No bisavo, No avo, No pai, No i) throws Exception{
        if(pai.cor == true){
            int comparacao1 = pai.elemento.getNome().compareTo(avo.elemento.getNome());
            int comparacao2 = i.elemento.getNome().compareTo(pai.elemento.getNome());
            if(comparacao1 >= 1){
                if(comparacao2 >= 1){
                    avo = RotacionarEsq(avo);
                } else if(comparacao2 <= -1){
                    avo = RotacionarDirEsq(avo);
                } else{
                    throw new Exception("ERRO DESCONHECIDO");
                }
                
                
            } else if(comparacao1 <= -1){
                if(comparacao2 <= -1){
                    avo = RotacionarDir(avo);
                } else if(comparacao2 >= 1){
                    avo = RotacionarEsqDir(avo);
                } else{
                    throw new Exception("ERRO DESCONHECIDO");
                }
            }
            else{
                throw new Exception("ERRO DESCONHECIDO");
            }
        } 
        if(bisavo == null){
            raiz = avo;
        } else {
            int comparacao3 = avo.elemento.getNome().compareTo(bisavo.elemento.getNome());
            if(comparacao3 >= 1){
                bisavo.dir = avo;
            }else if(comparacao3 <= -1){
                bisavo.esq = avo;
            } else{
                throw new Exception("ERRO DESCONHECIDO");
            }
        }
        avo.cor = false;
        avo.esq.cor = avo.dir.cor = true;
    }
    
    private No RotacionarDir(No i){
        No esq = i.esq;
        i.esq = esq.dir;
        
        esq.dir = i;
        
        return esq;
    }
    private No RotacionarEsq(No i){
        No dir = i.dir;
        i.dir = dir.esq;
        dir.esq = i;
        
        return dir;
    }
    private No RotacionarEsqDir(No i){
        i.esq = RotacionarEsq(i.esq);
        return RotacionarDir(i);
    }
    
    private No RotacionarDirEsq(No i){
        i.dir = RotacionarDir(i.dir);
        return RotacionarEsq(i);
    }
    
    private void Pesquisa(String entrada, No i){
        this.comparacoes++;
        if( i == null){
            System.out.println("NAO");
        } else{
            int comparacao = entrada.compareTo(i.elemento.getNome());
            this.comparacoes+=2;
            if(comparacao >= 1){
                System.out.print("dir ");
                Pesquisa(entrada, i.dir);
                this.comparacoes--;
            } else if(comparacao <= -1){
                System.out.print("esq ");
                Pesquisa(entrada,i.esq);
            } else{
                System.out.println("SIM");
            }
        }
    }
    
    public void PesquisaSequencial(String entrada){
        System.out.print(entrada + " raiz ");
        Pesquisa(entrada, raiz);
    }
    
    
    
}


public class Alvinegra{
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
        Arvore arvore = new Arvore();

        while(!isFIM(entrada)){
                try{
                    int index = Integer.parseInt(entrada);
                    Jogador p = lista.get(index + 1);
                    arvore.Inserir(p);
                    entrada = MyIO.readLine();
                } catch(Exception e){
                    //System.out.println(e.getMessage());
                }
        }
        
        
        
        entrada = MyIO.readLine();
        double inicio = now();
        while(!isFIM(entrada)){
            arvore.PesquisaSequencial(entrada);
            entrada = MyIO.readLine();
        }
        double fim = now();
        String arquivo = "801792\t"+(fim-inicio)/1000.0+"s\t"+arvore.comparacoes;
        Arq.openWriteClose("./801792_avinegra.txt", arquivo);
        
    }
}