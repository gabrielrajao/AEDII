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

    printf("[%d] ## %s ## %d ## %d ## %d ## %s ## %s ## ",p.id,p.nome,p.altura,p.peso,p.ano,p.uni,p.cidade);
    printf("%s ##\n", p.estado);


}




typedef struct {
    jogador array[1000];
    int tamanho;

}Lista;

Lista * CriaLista(){
    Lista * result = malloc(sizeof(Lista));
    result->tamanho = 0;
    return result;
}

//Inserts

void InsertFinal(jogador x, Lista * obj){
    x.id = obj->tamanho;
    obj->array[obj->tamanho] = x;
    obj->tamanho++;
}

void InsertInicio(jogador x, Lista * obj){
    x.id = 0;
    jogador temp = obj->array[0];
    obj->array[0] = x;
    obj->tamanho++;
    for(int i = 1; i <= obj->tamanho; i++){
        jogador temp2 = obj->array[i];
        temp.id = i;
        obj->array[i] = temp;
        temp = temp2;
    }
}

void Insert(int pos, jogador x, Lista * obj){
    if(pos == 0){
        InsertInicio(x,obj);
    } else if( pos == obj->tamanho){
        InsertFinal(x,obj);
    } else if ( pos < obj->tamanho){
        jogador temp = obj->array[pos];
        x.id = pos;
        obj->array[pos] = x;
        obj->tamanho++;
        for(int i = pos+1; i <= obj->tamanho; i++){
            jogador temp2 = obj->array[i];
            temp.id = i;
            obj->array[i] = temp;
            temp = temp2;
        }

    }
}

//Removes

void RemoveFinal(Lista * obj){
    obj->tamanho--;
    printf("(R) %s\n", obj->array[obj->tamanho].nome);

}

void RemoveInicio(Lista * obj){
    printf("(R) %s\n", obj->array[0].nome);
    for(int i = 1; i < obj->tamanho; i++){
        obj->array[i-1] = obj->array[i];
        obj->array[i-1].id = i-1;
    }
    obj->tamanho--;
}

void Remove(int pos, Lista * obj){
    if(pos == 0){
        RemoveInicio(obj);
    } else if (pos == obj->tamanho - 1){
        RemoveFinal(obj);
    } else if (pos < obj->tamanho){
        printf("(R) %s\n", obj->array[pos].nome);
        for(int i = pos+1; i < obj->tamanho; i++){
            obj->array[i-1] = obj->array[i];
            obj->array[i-1].id = i-1;
        }
        obj->tamanho--;
    }
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
            if(entrada[1] == '*'){
                int posicao;
                scanf("%d", &posicao);
               // printf("%d\n", posicao);
                Remove(posicao, list);
            }

            if(entrada[1] == 'F'){
                RemoveFinal(list);
            }

            if(entrada[1] == 'I'){
                RemoveInicio(list);
            }
        }
        else{
            if(entrada[1] == '*'){
                int posicao, id;
                scanf("%d %d", &posicao, &id);
                //printf("%d %d\n", posicao, id );
                Insert(posicao, lista[id],list);
            }

            if(entrada[1] == 'F'){
                int posicao;
                scanf("%d", &posicao);
                //printf("%d\n", posicao);
                InsertFinal(lista[posicao], list);
            }

            if(entrada[1] == 'I'){
                int posicao;
                scanf("%d", &posicao);
                //printf("%d\n", posicao);
                InsertInicio(lista[posicao], list);
            }
        }
    }

    for(int i = 0; i < list->tamanho; i++){
        imprimir(list->array[i]);
    }



    free (list);
    free(lista);
    return 0;
}
