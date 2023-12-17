#include <stdio.h>
#include <stdlib.h>
#include <time.h>
typedef struct {
    int id;
    int ano;
    int altura;
    int peso;
    char nome[90];
    char uni[180];
    char estado[180];
    char cidade[90];
}jogador;

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
jogador lerArq(int s){
    FILE* arq = fopen("/tmp/players.csv", "r");
    char linha[300];
    fgets(linha,300,arq);
    jogador p;
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
        if(search == s){
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

        }

    }
    fclose(arq);
    return p;
}
void imprimir(jogador p){

    printf("[%d ## %s ## %d ## %d ## %d ## %s ## %s ## ",p.id,p.nome,p.altura,p.peso,p.ano,p.uni,p.cidade);
    printf("%s]\n", p.estado);


}

int toNum(char * pal){
    int tam = 0;
    while(pal[tam] != '\0'){
        tam++;
    }
    if(tam == 3 && pal[0] == 'F' && pal[1] == 'I' && pal[2] == 'M' ){
        return -1;
    }
    int i = 0;
    int result = 0;
    while(tam > i){
        result*=10;
        result+= ((int)pal[i]) - 48;
        i++;
    }
    return result;
}


int contadorm = 0;
int contadorc = 0;

int smallerThan(char* str1, char* str2){
    int i = 0;
    while( str1[i] != '\0' && str2[i] != '\0' && str1[i] == str2[i]){
        i++;
    }
    if(str1[i] > str2[i]){
        return 1;
    }
    return - 1;
}

void selection(jogador ** lista, int tam, int i){
    if(i < tam - 1){
        int menor = i;
        for(int j = i+1; j < tam; j++){
            contadorc++;
            if(smallerThan((*lista)[menor].nome,(*lista)[j].nome) != -1){
                menor = j;
            }

        }
        jogador temp = (*lista)[i];
        (*lista)[i] = (*lista)[menor];
        (*lista)[menor]=temp;
        contadorm += 3;
        selection(lista,tam, i + 1);
    }
}


int main(){
    char ent[90];
    int num = 0;
    scanf("%s",ent); getchar();
    num=toNum(ent);
    jogador * m = malloc(sizeof(jogador)*300);
    int tam = 0;
    int size = 300;
    while(num!=-1){
            if(tam > size + 1){
                size = size+ 50;
                m = realloc(m,(sizeof(jogador)*size));
            }
            m[tam] = lerArq(num);
            tam++;

            scanf("%s",ent); getchar();
            num=toNum(ent);
    }
    clock_t inicio, fim;
    double total;
    inicio = clock();
    selection(&m,tam,0);
    fim = clock();
    total = ((fim - inicio) / (double)CLOCKS_PER_SEC);
    FILE* arq = fopen("801792_selecaoRecursiva.txt", "w");
    fprintf(arq,"801792\t%d\t%d\t%f s",contadorc, contadorm,total);
    fclose(arq);
    for(int i = 0; i < tam; i++){
        imprimir(m[i]);
    }
    free(m);
    return 0;
}
