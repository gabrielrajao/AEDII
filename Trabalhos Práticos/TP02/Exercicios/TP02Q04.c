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
    char estado[90];
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
        if(linha[j]!=' '){
            while(linha[j]!='\0'){
                p.estado[i] = linha[j];
                i++;
                j++;
            }
            p.estado[i] = '\0';
        }
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
    printf("[%d ## %s ## %d ## %d ## %d ## %s ## %s ## %s]\n",p.id,p.nome,p.altura,p.peso,p.ano,p.uni,p.cidade,p.estado);
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

int strtoInt(char* str){
    int result = 0;
    int i =0;
    while( str[i]!= '\0'){
        result*=100;
        result += (int)str[i] - 48;
        i++;
    }
    return result;
}
int contador = 0;
void insertion(jogador* p, int tam){
    for(int i = 1; i < tam; i++){
            jogador temp = p[i];
            int j = i - 1;
            contador++;
            while((j>=0)&& strtoInt(p[j].nome)> strtoInt(temp.nome)){
                p[j+1] = p[j];
                j--;
            }
            p[j+1] = temp;
    }
}
int isEqual(char* str1, char * str2){
    int tam1 = 0;
    int tam2 = 0;
    while(str1[tam1]!='\0'){
        tam1++;
    }
    while(str2[tam2]!='\0'){
        tam2++;
    }
    int result = 0;
    contador++;
    if(tam1 == tam2){
        for(int i = 0; str1[i]!='\0';i++){
            contador++;
            if(str1[i] != str2[i]){
                result = -1;
            }
        }
    } else{ result = -1;}
    return result;
}
int pesqbin(char * str, jogador * array, int tam){
    int esq = 0;
    int dir = tam - 1;
    int pesqint = strtoInt(str);
    while(esq <= dir){
            int meio = (esq + dir)/2;
            contador++;
            if(pesqint > strtoInt(array[meio].nome)){
                esq = meio + 1;
            } else{
                if(isEqual(str,array[meio].nome)!= -1){
                    printf("SIM\n");
                    return 0;
                }
                dir = meio - 1;
            }
    }
    printf("NAO\n");
    return -1;
}


int main(){
    char ent[90];
    int num = 0;
    scanf("%s",ent); getchar();
    num=toNum(ent);
    jogador m[300];
    int tam = 0;
    while(num!=-1){
            m[tam] = lerArq(num);
            tam++;

            scanf("%s",ent); getchar();
            num=toNum(ent);
    }
    clock_t inicio, fim;
    double total;
    inicio = clock();
    insertion(m,tam);
    scanf("%89[^\n]",ent); getchar();
    num=toNum(ent);
    while(num!=-1){
        pesqbin(ent,m, tam);
        scanf("%89[^\n]",ent);getchar();
        num=toNum(ent);
    }
    fim = clock();
    total = ((fim - inicio) / (double)CLOCKS_PER_SEC);
    FILE* arq = fopen("801792_binaria.txt", "w");
    fprintf(arq,"801792\t%f s\t%d",total,contador);
    fclose(arq);
    return 0;
}
