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
bool ordenacao(jogador p1, jogador p2){
    bool result = false;
    int a1 = p1.altura;
    int a2 = p2.altura;
    contadorc++;
    if(a1 > a2){
        result = true;
    } else{
        contadorc++;
            if(a1 == a2){
                    char* n1 = p1.nome;
                    char* n2 = p2.nome;
                    int i = 0;
                    while(n1[i] != '\0' && n2[i] != '\0' && n1[i] == n2[i]){
                        i++;
                    }
                    contadorc++;
                    if(n1[i] > n2[i]){
                        result =true;
                    }
            }
    }
    return result;
}
void swap(jogador ** lista, int pos1, int pos2){
    contadorm += 3;
    jogador temp = (*lista)[pos1];
    (*lista)[pos1] = (*lista)[pos2];
    (*lista)[pos2] = temp;
}
bool hasFilhos(int pos, int tam){
    contadorc++;
    bool result = false;
    if((pos*2)+1 < tam){
        result = true;
    }
    return result;
}

int maiorFilho(int pos, jogador** lista, int tam){
    int result = (pos*2) + 1;
    contadorc++;
    if((pos*2)+2 < tam && ordenacao(  (*lista)[(pos*2)+2], (*lista)[(pos*2)+1])  ){
        result = (pos*2) + 2;
    }
    return result;
}

void construir(jogador ** lista, int tam){
    int i = tam;
    while(i>= 0 && ordenacao((*lista)[i],(*lista)[(i-1)/2])){
        swap(lista,i,(i-1)/2);
        i = (i-1)/2;
    }
}

void reconstruir(jogador** lista, int tam){
    int i = 0;
    while(hasFilhos(i,tam)){
        int filho = maiorFilho(i,lista,tam);
        if(ordenacao((*lista)[filho], (*lista)[i])){
            swap(lista,i,filho);
            i = filho;
        }else{
            i = tam;
        }
    }
}

void heapsort(jogador** lista, int tam){
    for(int i = 0; i < 10; i++){
        construir(lista,i);
    }
    for(int i = 10; i < tam; i++){
        if(ordenacao((*lista)[0],(*lista)[i])){
            swap(lista,0,i);
            reconstruir(lista,10);
        }
    }
    int n = 10;
    while(n>0){
        swap(lista,n -1,0);
        n--;
        reconstruir(lista,n);
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
            if(tam >= size){
                size = size+ 50;
                m = realloc(m,(sizeof(jogador)*size));
            }
            if(num==223){
                num--;
            }
                m[tam] = lerArq(num);
                tam++;

            scanf("%s",ent); getchar();
            num=toNum(ent);
    }
    tam--;
    clock_t inicio, fim;
    double total;
    inicio = clock();
    heapsort(&m,tam);
    fim = clock();
    total = ((fim - inicio) / (double)CLOCKS_PER_SEC);
    //FILE* arq = fopen("801792_shellsort.txt", "w");
    //fprintf(arq,"801792\t%d\t%d\t%f s",contadorc, contadorm,total);
    //fclose(arq);
    for(int i = 0; i < 10; i++){
        imprimir(m[i]);
    }
    free(m);
    return 0;
}
