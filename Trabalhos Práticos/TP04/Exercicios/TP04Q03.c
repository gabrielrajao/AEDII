#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>
#include <time.h>

int comparacoes = 0;


//novos metodos
int compareStr(char* wrd1, char* wrd2){
    int result = -1;
    int i = 0;
    while(wrd1[i]!='\0' && wrd2[i]!='\0' && wrd1[i] == wrd2[i]){
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
    while(wrd1[i] != '\0'){
        result*=10;
        result+= (int) wrd1[i] - 48;
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

//No

typedef struct No{
    struct No*esq;
    struct No*dir;
    jogador elemento;
    int nivel;
}No;

No* CriaNo(jogador elemento){
    No * result = (No*) malloc(sizeof(No));
    result->esq = NULL;
    result->dir = NULL;
    result->nivel = 1;
    result->elemento = elemento;
    return result;
}

//ArvoreBinaria

typedef struct ArvoreBinaria{
    No * raiz;
}ArvoreBinaria;

ArvoreBinaria * CriaArvore(){
    ArvoreBinaria* result = (ArvoreBinaria*) malloc(sizeof(ArvoreBinaria));
    result->raiz = NULL;
    return result;
}

int defineNivel(No * no){
    int maior = 0;
    if(no->esq !=NULL){
        maior = no->esq->nivel;
    }
    if(no->dir !=NULL && no->dir->nivel > maior){
        maior = no->dir->nivel;
    }
    
    return (1 + maior);
}

int calculaBalanc(No * i){
    No * esq = i->esq;
    No * dir = i->dir;
    int result = 0;
    
    if(dir != NULL){
        result+= dir->nivel;
    }
    if(esq != NULL){
        result-= esq->nivel;
    }
    return result;
}

No * rotacionaDir(No * i){
    No * esq = i->esq;
    No * esqDir = esq->dir;
    
    esq->dir = i;
    i->esq = esqDir;
    
    i->nivel = defineNivel(i);
    esq->nivel = defineNivel(esq);
    
    return esq;
}

No * rotacionaEsq(No * i){
    No *dir = i->dir;
    No *dirEsq = dir->esq;
    
    dir->esq = i;
    i->dir = dirEsq;
    
    i->nivel = defineNivel(i);
    dir->nivel = defineNivel(dir);
    
    return dir;
}

No * balancear(No * i){
    if(i != NULL){
        int bal = calculaBalanc(i);
        if(bal == 2){
            int baldir = calculaBalanc(i->dir);
            if(baldir == -1){
                i->dir = rotacionaDir(i->dir);
            }
            i = rotacionaEsq(i);
        }
        else if(bal == -2){
            int balesq = calculaBalanc(i->esq);
            if(balesq == 1){
                i->esq = rotacionaEsq(i->esq);
            }
            i = rotacionaDir(i);
        }
        else if(bal >= -1 && bal <= 1){
            i->nivel = defineNivel(i);
        } 
        else{
            printf("ERRO: fator de balanceamento invalido!\n");
        }
    }
    
    return i;
    
}

void CaminhamentoPre(No * i){
    if(i != NULL){
        printf("%d ",i->elemento.id);
        CaminhamentoPre(i->esq);
        CaminhamentoPre(i->dir);
    }
}
    
void CaminharPre(ArvoreBinaria * arv){
    CaminhamentoPre(arv->raiz);
    printf("\n");
}


No * Insercao(jogador elemento, No * i){
    if(i == NULL){
        i = CriaNo(elemento);
        
    }
    else if( compareStr(elemento.nome, i->elemento.nome) == 1){
        i->dir = Insercao(elemento,i->dir);
    }
    else if( compareStr(elemento.nome, i->elemento.nome) == -1 ){
        i->esq = Insercao(elemento,i->esq);
    }
    return balancear(i);
}

void Inserir(jogador elemento, ArvoreBinaria * arv){
    arv->raiz = Insercao(elemento, arv->raiz);
}




void Pesquisa(char * nome, No * i){
    comparacoes+=3;
    if(i == NULL){
        printf("NAO\n");
        comparacoes-=2;
    }
    else if( compareStr(nome, i->elemento.nome) == 1){
        printf("dir ");
        Pesquisa(nome, i->dir);
        comparacoes--;
    }
    else if( compareStr(nome, i->elemento.nome) == -1){
        printf("esq ");
        Pesquisa(nome, i->esq);
    }
    else{
        printf("SIM\n");
    }
}

void Pesquisar(char * nome, ArvoreBinaria * arv){
    printf("%s raiz ", nome);
    Pesquisa(nome,arv->raiz);
}




int main(){
    //define dados
    char entrada[90];
    jogador * lista = lerArq();
    ArvoreBinaria * arv = CriaArvore();
    
    //Insere Arvore
    scanf("%90[^\n]", entrada); getchar();
    while(compareStr(entrada,"FIM") != 0){
        int id = toNumber(entrada);
        Inserir( lista[id],arv );
        scanf("%90[^\n]", entrada); getchar();
    }
    
    clock_t inicio, fim;
    double total;
    inicio = clock();
    //Pesquisas
    scanf("%90[^\n]", entrada); getchar();
    while(compareStr(entrada,"FIM") != 0){
        Pesquisar(entrada,arv);
        scanf("%90[^\n]", entrada); getchar();
    }
    fim = clock();
    total = ((fim - inicio) / (double)CLOCKS_PER_SEC);
    FILE* arq = fopen("801792_avl.txt", "w");
    fprintf(arq,"801792\t%d\t%f s",comparacoes, total);
    fclose(arq);
    
    
    //FIM
    free(lista);
    return 0;
}