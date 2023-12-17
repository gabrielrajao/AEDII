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

    printf(" ## %s ## %d ## %d ## %d ## %s ## %s ## ",p.nome,p.altura,p.peso,p.ano,p.uni,p.cidade);
    printf("%s ##\n", p.estado);


}



typedef struct Celula{
    jogador elemento;
    struct Celula * prox;
} Celula;

Celula * CriaCell(jogador elemento){
    Celula * cell = (Celula *) malloc(sizeof(Celula));
    cell->elemento = elemento;
    cell->prox = NULL;
    return cell;
}
Celula * CriaCellV(){
    Celula * cell = (Celula *) malloc(sizeof(Celula));
    cell->prox = NULL;
    return cell;
}



typedef struct {
    Celula * inicio;
    Celula * fim;
    int tamanho;

}Lista;

Lista * CriaLista(){
    Lista * result = malloc(sizeof(Lista));
    result->inicio = CriaCellV();
    result->fim = result->inicio;
    result->tamanho = 0;
    return result;
}


//Prints
void PrintLista(Lista * obj){
    Celula * index = obj->inicio;
    index = index->prox;
    int num = 0;
    while(index != obj->fim){
        printf("[%d]", num);
        num = num + 1;
        imprimir(index->elemento);
        index = index->prox;
    }
    printf("[%d]", num);
     num = num + 1;
     imprimir(index->elemento);
     index = index->prox;
}

void PrintMedia(Lista * obj){
    Celula * index = obj->inicio;
    index = index->prox;
    int media = 0;
    int qnt = 0;
    while(index != obj->fim){
        media+= index->elemento.altura;
        qnt++;
        index = index->prox;
    }
    media+= index->elemento.altura;
    qnt++;
    double mediaf = ((double)media)/( (double) qnt);
    mediaf += 0.5;

    printf("%d\n", (int) mediaf );
}


//Removes


void RemoveInicio(Lista * obj){
    printf("(R) %s\n", obj->inicio->prox->elemento.nome);
    obj->inicio = obj->inicio->prox;
    obj->tamanho--;

}

//Inserts

void InsertFinal(jogador x, Lista * obj){
    if(obj->tamanho == 5){

        obj->inicio = obj->inicio->prox;
        obj->tamanho--;
    }
    obj->fim->prox = CriaCell(x);
    obj->fim = obj->fim->prox;
    obj->tamanho++;
    PrintMedia(obj);
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
    int IorR; scanf("%d", &IorR);
    for(int i = 0; i < IorR; i++){
        scanf("%s", entrada);
        if(entrada[0] == 'R'){
            RemoveInicio(list);
        }
        else{
            int id;
            scanf("%d", &id);
            InsertFinal(lista[id], list);
        }
    }



    PrintLista(list);

    free (list);
    free(lista);
    return 0;
}
