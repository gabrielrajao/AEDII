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

class Hash{
    private Jogador[] main;
    private Jogador[] reserva;
    public int comparacoes;
    
    public Hash(){
        main = new Jogador[21];
        reserva = new Jogador[9];
        for(int i = 0; i < 21; i++){
            main[i] = null;
        }
        for(int i = 0; i < 9; i++){
            reserva[i] = null;
        }
    }
    
    private int hash(Jogador p){
        return p.getAltura()%21;
    }
    public void Inserir(Jogador p){
        int h = hash(p);
        if(this.main[h] == null) this.main[h] = p;
        else{
            for(int i = 0; i < 9; i++){
                if(this.reserva[i] == null){
                    this.reserva[i] = p;
                    i = 20;
                }
            }
        }
    }
    public void Printa(){
        for(int i = 0; i < 21; i++){
            if(this.main[i]!= null){
                System.out.println(this.main[i].getId() + " " + this.main[i].getNome());
                
            }
            else{
                System.out.println("NULL");
            }
        }
    }
    
    
    public void PesquisaSequencial(Jogador p){
       
        System.out.print(p.getNome()+" ");
        int h = hash(p);
        comparacoes++;
        if(this.main[h] == null){
            System.out.println("NAO");
        } else{
            comparacoes++;
            if(this.main[h].getId() == p.getId()){
                System.out.println("SIM");
            } else{
                boolean encontrado = false;
                for(int i = 0; i < 9; i++){
                    comparacoes++;
                    if(this.reserva!= null && this.reserva[i].getId() == p.getId()){
                        System.out.println("SIM");
                        encontrado = true;
                    }
                }
                comparacoes++;
                if(!encontrado){
                    System.out.println("NAO");
                }
                
            }
        }
    }
    
    
}



public class hdreserva{
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
    
    public static Jogador Procura(String nome, ArrayList<Jogador> lista){
        Jogador result = null;
        for(int i = 0; i < lista.size(); i++){
            String nomejog = lista.get(i).getNome();
            int j = 0;
            while(j < nome.length() && j <nomejog.length() &&nomejog.charAt(j) == nome.charAt(j)){
                j++;
            }
            if(j == nome.length()){
                result = lista.get(i);
                i = lista.size()+1;
            }
        }
        return result;
    }

    public static void main (String[] args){
        ArrayList<Jogador> lista = Jogador.ler();
        String entrada = MyIO.readLine();
        Hash hash = new Hash();

        while(!isFIM(entrada)){
                try{
                    int index = Integer.parseInt(entrada);
                    Jogador p = lista.get(index + 1);
                    hash.Inserir(p);
                    entrada = MyIO.readLine();
                } catch(Exception e){
                }
        }
        //hash.Printa();
        entrada = MyIO.readLine();
        double inicio = now();
        while(!isFIM(entrada)){
            Jogador index = Procura(entrada, lista);
            hash.PesquisaSequencial(index);
            entrada = MyIO.readLine();
        }
        double fim = now();
        String arquivo = "801792\t"+(fim-inicio)/1000.0+"s\t"+hash.comparacoes;
        Arq.openWriteClose("./801792_hashReserva.txt", arquivo);
    }
}