#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <stdbool.h>
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
void imprimir(jogador p){

    printf("[%d ## %s ## %d ## %d ## %d ## %s ## %s ## ",p.id,p.nome,p.altura,p.peso,p.ano,p.uni,p.cidade);
    printf("%s]\n", p.estado);


}

typedef struct Celula{
    jogador elemento;
    struct Celula * prox;
    struct Celula * ant;
} Celula;

Celula * CriaCell(jogador elemento){
    Celula * cell = (Celula *) malloc(sizeof(Celula));
    cell->elemento = elemento;
    cell->prox = NULL;
    cell->ant = NULL;
    return cell;
}
Celula * CriaCellV(){
    Celula * cell = (Celula *) malloc(sizeof(Celula));
    cell->prox = NULL;
    cell->ant = NULL;
    return cell;
}

typedef struct{
    Celula * inicio;
    Celula * final;
    int tamanho;
}Lista;

Lista * CriaLista(){
    Lista * p = malloc(sizeof(Lista));
    p->inicio = CriaCellV();
    p->final = p->inicio;
    p->tamanho = 0;
    return p;
}

void InsertFinal(jogador p, Lista * x ){
    Celula * cell = CriaCell(p);
    cell->ant = x->final;
    (x->final)->prox = cell;
    x->final = cell;
    x->tamanho++;
}

void RemoveLista(Lista * x){
    while(x->final != x->inicio){
            Celula * temp = (x->final)->ant;
            free(x->final);
            x->final = temp;
    }
}

int ComparaStrings(char * a, char * b){
    int i = 0;
    while(a[i] != '\0' && b[i]!='\0' && a[i] == b[i])i++;
    if(a[i] == '\0'){
        if(b[i] == '\0'){
            return 0;
        }else{
            return -1;
        }
    }
    if(b[i] == '\0'){
        return 1;
    }

    if(a[i] > b[i]){
        return 1;
    } else {
        return -1;
    }
}

int Algoritmo (jogador a, jogador b){
    int rem = ComparaStrings(a.estado, b.estado);
    if(rem == 0){
        rem = ComparaStrings(a.nome, b.nome);
    }
    if(rem == 1){
        return 1;
    } else{
        return 0;
    }
}

int comp = 0;
int mov = 0;

void Swap(Celula * a, Celula * b){
    mov+=3;
    jogador temp = a->elemento;
    a->elemento = b->elemento;
    b->elemento = temp;
}

void Sort(Celula * esq, Celula * dir, int iesq, int idir){
    Celula * i = esq;
    Celula * j = dir;
    int ii = iesq;
    int ij = idir;
    int ipivo = (iesq + idir)/2;
    Celula * fakepivo = esq;
    for(int i = iesq; i < ipivo; i++){
        fakepivo = fakepivo->prox;
    }
    jogador pivo = fakepivo->elemento;

    if(ii <= ij){
        comp++;
        while(Algoritmo(pivo,i->elemento)){
            i = i->prox;
            ii++;
        }
        comp++;
        while(Algoritmo(j->elemento, pivo)){
            j = j->ant;
            ij--;
        }

        if(ii <= ij){
            Swap(i,j);
            i = i->prox;
            j = j->ant;
            ii++;
            ij--;
        }


    }

    if(ij > iesq){
        Sort(esq, j, iesq, ij);
    }
    if(ii < idir){
        Sort(i, dir, ii, idir);
    }



}

void QuickSort(Lista * x){
    Sort(x->inicio->prox, x->final, 0, x->tamanho);
}

const char FIM[4] = "FIM";

bool isFIM(char * word){
    bool result = true;
    for(int i = 0; i < 4; i++){
        if(FIM[i] != word[i]){
            result = false;
            i = 5;
        }
    }
    return result;
}

int toNumber(char * word){
    int result = 0;
    int i = 0;
    while(word[i] != '\0'){
        result*=10;
        result+= ((int) word[i]) - 48;
        i++;
    }
    return result;
}


int main(){
    jogador * lista = lerArq();
    Lista * list = CriaLista();
    char entrada[90];
    scanf("%s", entrada);
    while(!isFIM(entrada)){
        getchar();
        InsertFinal(lista[toNumber(entrada)], list);
        scanf("%s", entrada);
    }


    clock_t inicio, fim;
    double total;
    inicio = clock();
    QuickSort(list);
    fim = clock();
    total = ((fim - inicio) / (double)CLOCKS_PER_SEC);


    FILE* arq = fopen("801792_quicksort2.txt", "w");
    fprintf(arq,"801792\t%d\t%d\t%f s",comp, mov,total);
    fclose(arq);

    Celula * index = (list->inicio)->prox;
    for(int i = 0; i < list->tamanho; i++){
        imprimir(index->elemento);
        index = index->prox;
    }


    RemoveLista(list);
    free(list->inicio);
    free (list);
    free(lista);
    return 0;
}
