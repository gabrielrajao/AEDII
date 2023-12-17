#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>
#include <time.h>

int comparacoes = 0;


//novos metodos
int compareStr(char* wrd1, char* wrd2){
    int result = -1;
    int i = 0;
    while(wrd1[i]!='\0' && wrd2[i]!='\0'  && wrd1[i] == wrd2[i]){
        i++;
    }
    if(wrd1[i] == '\0' && wrd2[i] == '\0'){
        result = 0;
    } else if( wrd2[i] == '\0' || (wrd1[i] !='\0' && wrd1[i] > wrd2[i]) ){
        result = 1;
    }
    
    return result;
    
}

int toNumber(char* wrd1){
    int i = 0;
    int result = 0;
    
    while(wrd1[i] != '\0' && wrd1[i] != 13){
            int num = (int) wrd1[i];
            result*=10;
            result+= (int) num - 48;
            i++;
    }
    return result;
}


//classe jogador
typedef struct {
    int id;
    int ano;
    int altura;
    int peso;
    char nome[180];
    char uni[180];
    char estado[180];
    char cidade[180];
}jogador;

bool isNaoInf(char * word){
    int i = 0;
    bool result = false;
    char palavra[14];
    palavra[0] = 'n';
    palavra[1] = 'a';
    palavra[2] = 'o';
    palavra[3] = ' ';
    palavra[4] = 'i';
    palavra[5] = 'n';
    palavra[6] = 'f';
    palavra[7] = 'o';
    palavra[8] = 'r';
    palavra[9] = 'm';
    palavra[10] = 'a';
    palavra[11] = 'd';
    palavra[12] = 'o';
    palavra[13] = '\0';
    while(word[i]!='\0' && palavra[i] != '\0' && palavra[i] == word[i]){
        i++;
    }
    if(word[i] == palavra[i] && word[i]=='\0'){
        result = true;
    }
    return result;
}

void naoinf(char * palavra){
    palavra[0] = 'n';
    palavra[1] = 'a';
    palavra[2] = 'o';
    palavra[3] = ' ';
    palavra[4] = 'i';
    palavra[5] = 'n';
    palavra[6] = 'f';
    palavra[7] = 'o';
    palavra[8] = 'r';
    palavra[9] = 'm';
    palavra[10] = 'a';
    palavra[11] = 'd';
    palavra[12] = 'o';
    palavra[13] = '\0';
}
jogador * lerArq(){
    FILE* arq = fopen("/tmp/players.csv", "r");
    char linha[300];
    fgets(linha,300,arq);
    jogador p;
    jogador * lista = (jogador*) malloc(3922 * sizeof(jogador));
    int index = 0;
    while(fscanf(arq,"%299[^\n]\n",linha)!= EOF){
                        int j = 0;
        int i = 0;
        int search = 0;
        while(linha[j]!=','){
            search *= 10;
            search += ((int) linha[j]) - 48;
            j++;
        }
        j++;
        //if(search == s){
                p.altura=0;
                p.id=search;
                p.peso = 0;
                p.ano =0;
                    i = 0;
        while(linha[j]!=','){
            p.nome[i] = linha[j];
            i++;
            j++;
        }
        p.nome[i] = '\0';
        j++;
        while(linha[j]!=','){
            p.altura *= 10;
            p.altura += ((int) linha[j]) - 48;
            j++;
        }
        j++;
        while(linha[j]!=','){
            p.peso *= 10;
            p.peso += ((int) linha[j]) - 48;
            j++;
        }
        j++;
        i = 0;
        p.uni[0] = '\0';
        while(linha[j]!=','){
            if(linha[j]=='"'){
                j++;
                while(linha[j]!='"'){
                    if(linha[j]==','){
                        p.uni[i] = ' ';
                        i++;
                        p.uni[i] = '-';
                    }
                    else{
                        p.uni[i] = linha[j];
                    }
                    j++;
                    i++;
                }
                j++;
            }
            else{
            p.uni[i] = linha[j];
            i++;
            j++;
            }

        }
        j++;
        p.uni[i] = '\0';
        while(linha[j]!=','){
            p.ano *= 10;
            p.ano += ((int) linha[j]) - 48;
            j++;
        }
        j++;
        i = 0;
        p.cidade[0] = '\0';
        while(linha[j]!=','){
            p.cidade[i] = linha[j];
            i++;
            j++;
        }
        j++;
        p.cidade[i] = '\0';
        i = 0;
        p.estado[0] = '\0';
        while((int)linha[j]!=13 && linha[j]!='\0'){

                p.estado[i] = linha[j];
                i++;
                j++;
            }
            p.estado[i] = '\0';

        if(p.uni[0] == '\0'){
            naoinf(p.uni);
        }
        if(p.nome[0] == '\0'){
            naoinf(p.nome);
        }
        if(p.cidade[0] == '\0'){
            naoinf(p.cidade);
        }
        if(p.estado[0] == '\0'){
            naoinf(p.estado);
        }


        lista[index] = p;
        index++;

        //}

    }
    fclose(arq);
    return lista;
}

//celula
typedef struct Celula{
    jogador elemento;
    struct Celula * prox;
}Celula;


Celula * CriaCelula(jogador x){
    Celula * result = (Celula*) malloc(sizeof(Celula));
    result->elemento = x;
    result->prox = NULL;
    return result;
}

//tabelaHASH

typedef struct Hash{
    Celula * array[25];
}Hash;

Hash * CriaHash(){
    Hash * result = (Hash*) malloc(sizeof(Hash));
    for(int i = 0; i < 25; i++){
        result->array[i] = NULL;
    }
    return result;
}

void FreeLista(Celula * x){
    if(x!=NULL){
        FreeLista(x->prox);
        free(x);
    }
}

void DeletaHash(Hash* x){
    for(int i = 0; i < 25; i++){
        FreeLista(x->array[i]);
    }
    free(x);
}



int hash(jogador x){
    int result = 0;
    result = x.altura %25;
    return result;
}

Celula* InserirCel(jogador ele, Celula * x){
    if(x == NULL){
        x = CriaCelula(ele);
    } else{
        x->prox = InserirCel(ele, x->prox);
    }
    return x;
}

void Inserir(jogador ele, Hash * x){
    int index = hash(ele);
    x->array[index] = InserirCel(ele, x->array[index]);
}

void Pesquisa(jogador ele, Celula * x){
    comparacoes+=2;
    if(x == NULL){
        printf("NAO");
        comparacoes--;
    }
    else if( compareStr(x->elemento.nome,ele.nome) == 0){
        printf("SIM");
    }else{
        Pesquisa(ele,x->prox);
    }
}




void printa(char * wrd){
    int i = 0;
    while(wrd[i] != '\0' && wrd[i]!='*'){
        printf("%c",wrd[i]);
        i++;
    }
    printf(" ");
}


void Buscar(jogador ele, Hash * x){
    int index = hash(ele);
    printa(ele.nome);
    Pesquisa(ele,x->array[index]);
    printf("\n");
}


//classe main
jogador Procura(char * nome, jogador * lista){
    int resultado = 0;
    for(int i = 0; i < 3922; i++){
        int j = 0;
        while(nome[j]!='\0' && lista[i].nome[j]!='\0' && nome[j] == lista[i].nome[j]){
            j++;
        }
        if(nome[j] == '\0'){
            resultado = i;
        }
    }
    return lista[resultado];
}


void Corrige(char * wrd){
    int i = 0;
    while(wrd[i]!='\0'){
        if(wrd[i] == 13){
            wrd[i] = 0;
        }
        else{
            i++;
        }
    }
}


int main(){
    //define dados
    char entrada[90];
    jogador * lista = lerArq();
    Hash * hind = CriaHash();
    //Insere Arvore
    scanf("%90[^\n]", entrada); getchar();
    Corrige(entrada);
    while(compareStr(entrada,"FIM") != 0){

        int id = toNumber(entrada);
        Inserir(lista[id], hind);
        scanf("%90[^\n]", entrada); getchar();
        Corrige(entrada);
    }
    clock_t inicio, fim;
    double total;
    inicio = clock();
    
    scanf("%90[^\n]", entrada); getchar();
    Corrige(entrada);
    while(compareStr(entrada,"FIM") != 0){
        jogador index = Procura(entrada,lista);
        Buscar(index,hind);
        scanf("%90[^\n]", entrada); getchar();
        Corrige(entrada);
    } 
    fim = clock();
    total = ((fim - inicio) / (double)CLOCKS_PER_SEC);
    //arquivo
    FILE* arq = fopen("801792_hashIndireta.txt", "w");
    fprintf(arq,"801792\t%d\t%f s",comparacoes, total);
    fclose(arq);
    //freelista
        DeletaHash(hind);
        free(lista);
    
    return 0;
}