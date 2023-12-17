#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int Pali(char * word, int tamanho, int index){
    if(index >= tamanho){
        return 1;
    }
    if(word[index] == word[tamanho]){
        return Pali(word,tamanho-1,index+1);
    } else{
        return -1;
    }
}

int Pali1(char * word){
    int tamanho = 0;
    int index = 0;
    for (tamanho = 0; word[tamanho] != '\0'; tamanho++){}
    if(tamanho == 3 && word[0] == 'F' && word[1] == 'I' && word[2] == 'M' && word[3] == '\0') { return -35; }
    tamanho -= 1;
    if(word[index] == word[tamanho]){
            return Pali(word,tamanho-1,index+1);
    } else{
        return -1;
    }

}


int main(){
    char palavra[10000];
    int a = 0;
    while (a != -1){
        scanf("%9999[^\n]", palavra); getchar();
        int r = Pali1(palavra);
        if(r==1){
                    printf("SIM\n");
        }
        else{
            if(r==-35){ a = -1;}
            else{ printf("NAO\n");}

        }
    }
}

