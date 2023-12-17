class Celula{
    private int elemento;
    private Celula esq;
    private Celula dir;
    private Celula sup;
    private Celula inf;

    //Construtor
    public Celula(){
        this(-1);
    }
    public Celula(int x){
        elemento = x;
        esq = null;
        dir = null;
        sup = null;
        inf = null;
    }

    //gets
    public int getElemento(){
        return this.elemento;
    }

    public Celula getEsq(){
        return this.esq;
    }

    public Celula getDir(){
        return this.dir;
    }

    public Celula getSup(){
        return this.sup;
    }

    public Celula getInf(){
        return this.inf;
    }

    //sets
    public void setElemento(int x){
        this.elemento = x;
    }

    public void setEsq(Celula esq){
        this.esq = esq;
    }

    public void setDir(Celula dir){
        this.dir = dir;
    }

    public void setSup(Celula sup){
        this.sup = sup;
    }

    public void setInf(Celula inf){
        this.inf = inf;
    }



}

class Matriz{
    private Celula inicio;
    private int linha;
    private int coluna;
    //Construtor
    public Matriz(){
        this(3,3);
    }
    public Matriz(int l, int c){
        try{
            if(l < 1 || c < 1){
                throw new IllegalArgumentException("ERRO: Matriz pequena demais");
            }
            //salvando atributos do objeto
            this.linha = l;
            this.coluna = c;
            //alocacao propriamente dita
            this.inicio = new Celula();
            Celula temp = this.inicio;
            for(int i = 0; i < c-1; i++){
                temp.setDir(new Celula());
                (temp.getDir()).setEsq(temp);
                temp = temp.getDir();

            }
            temp = inicio;
            for(int i = 0; i < l - 1; i++){
                //gerando linha
                temp.setInf(new Celula()) ;
                Celula temp2 = temp.getInf();
                temp2.setSup(temp);
                //preparando para gerar colunas
                Celula index = temp;
                Celula index2 = temp2;
                for(int j = 0; j < c-1; j++){
                    index = index.getDir();
                    index2.setDir(new Celula());
                    (index2.getDir()).setEsq(index2);
                    index2 = index2.getDir();
                    index2.setSup(index);
                    index.setInf(index2);
                }
                temp = temp.getInf();
            }

        } catch(IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
    }
    //gets
    public Celula getInicio(){
        return this.inicio;
    }

    public int getColuna(){
        return this.coluna;
    }
    public int getLinha(){
        return this.linha;
    }


    //metodos
    public void Inserir(int x, int l, int c){
        try{
            if(l > this.linha || c > this.coluna || l < 1 || c < 1){
                throw new IllegalArgumentException("ERRO: Elemento fora da matriz");
            }
            Celula index = inicio;
            for(int i = 0; i < l - 1; i++){
                index = index.getInf();
            }
            for(int i = 0; i < c - 1; i++){
                index = index.getDir();
            }
            index.setElemento(x);


        }catch(IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
    }
    public void Imprimir(){

        for(Celula temp = inicio; temp != null; temp = temp.getInf()){
                for(Celula index = temp; index != null; index = index.getDir()){
                    System.out.print(""+ index.getElemento() +" ");
                }
                System.out.println("");
        }
    }

    public void mostrarDiagonalPrincipal(){
        Celula index = this.inicio;
        while(index != null){
            System.out.print(""+index.getElemento() + " ");
            index = index.getInf();
            if(index != null){
                index = index.getDir();
            }
        }
        System.out.println("");
    }

    public void mostrarDiagonalSecundaria(){
        Celula index = this.inicio;
        while(index.getDir()!= null){
            index= index.getDir();
        }
        while(index != null){
            System.out.print(""+index.getElemento() + " ");
            index = index.getEsq();
            if(index != null){
                index = index.getInf();
            }
        }
        System.out.println("");
    }

    public Matriz soma(Matriz a){
        Matriz result = null;
        try{
            if( a.getLinha() != this.linha || a.getColuna() != this.coluna ){
                throw new IllegalArgumentException("ERRO: Matrizes de tamanhos diferentes");
            }
            result = new Matriz(this.linha, this.coluna);
            for(Celula index = this.inicio,  index2 = a.getInicio(),  indexres = result.getInicio(); index !=null; index = index.getInf(), index2 = index2.getInf(), indexres = indexres.getInf() ){
                for(Celula temp = index,  temp2 = index2, tempres = indexres; temp!=null; temp = temp.getDir(), temp2 = temp2.getDir(), tempres = tempres.getDir()){
                    tempres.setElemento(temp.getElemento() + temp2.getElemento());
                }
            }


        }catch(IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
        return result;
    }
    public Matriz multiplicacao(Matriz a){
        Matriz result = null;
        try{
            if( a.getColuna() != this.linha){
                throw new IllegalArgumentException("ERRO: Matrizes de tamanhos diferentes");
            }
            result = new Matriz(this.linha, a.getColuna());
            Celula index1 = this.inicio;

            for(Celula index = result.getInicio(); index!= null; index = index.getInf()){
                Celula index2 = a.getInicio();
                    for(Celula temp = index; temp!= null; temp = temp.getDir()){
                        Celula temp1 = index1;
                        Celula temp2 = index2;
                        int eleme = 0;
                        while(temp1 != null){
                            eleme += (temp1.getElemento() * temp2.getElemento());
                            temp1 = temp1.getDir();
                            temp2 = temp2.getInf();
                        }


                        temp.setElemento(eleme);


                        index2 = index2.getDir();
                    }
                    index1 = index1.getInf();
            }


        }catch(IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
        return result;
    }

}

public class MatrizDinamica{
    public static int toNumber (String num){
        int result = 0;
        for(int i = 0; i < num.length(); i++){
            result*=10;
            result+= ((int) num.charAt(i) ) - 48;
        }
        return result;
    }

    public static String[] splitatSpace (String a){
        int arraysize = 1;
        for(int i = 0; i < a.length(); i++){
                if(a.charAt(i) == ' '){
                    arraysize++;
                }
        }
        int i = 0;
        int index = 0;
        String[] result = new String[arraysize];
        boolean newindex = true;
        while(i < a.length()){
                if(newindex){
                    result[index] = "";
                    newindex = false;
                }

                if(a.charAt(i) == ' '){
                    index++;
                    newindex = true;
                }else{
                    result[index] += a.charAt(i);
                }
                i++;

        }
        return result;
    }

    public static void main(String[] args){
        int casos = toNumber(MyIO.readLine());
        for(int m = 0; m < casos; m++){

            //Main Matriz

            int linhas = toNumber(MyIO.readLine());
            int colunas = toNumber(MyIO.readLine());
            Matriz principal = new Matriz(linhas, colunas);

            for(int i = 1; i <= principal.getLinha(); i++){
                String[] strcolunas = splitatSpace(MyIO.readLine());
                for(int j = 1; j <= principal.getColuna(); j++){

                    int elem = toNumber(strcolunas[j - 1]);

                    principal.Inserir(elem,i,j);

                }
            }


            //Sec Matriz

            linhas = toNumber(MyIO.readLine());
            colunas = toNumber(MyIO.readLine());
            Matriz secundaria = new Matriz(linhas, colunas);

            for(int i = 1; i <= secundaria.getLinha(); i++){
                String[] strcolunas = splitatSpace(MyIO.readLine());
                for(int j = 1; j <= secundaria.getColuna(); j++){

                    int elem = toNumber(strcolunas[j - 1]);

                    secundaria.Inserir(elem,i,j);

                }
            }

            principal.mostrarDiagonalPrincipal();
            principal.mostrarDiagonalSecundaria();

            Matriz soma = principal.soma(secundaria);
            soma.Imprimir();

            Matriz multi = principal.multiplicacao(secundaria);
            multi.Imprimir();


        }
    }
}
