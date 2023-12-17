#include <stdio.h>
#include <stdbool.h>
#include <string.h>
#include <locale.h>
const char FIM[4] = "FIM";
const int fimlength = 3;

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

bool isPali (char * word, int len){
    bool result = true;
    len = 0;
    for(int z = 0; word[z]!= '\0'; z++){
        len++;
    }
    int reverso = len - 1;
    for(int i = 0; i < len/2 ; i++){
        if(word[reverso] != word[i]){
            result = false;
            break;
        }
        reverso = reverso - 1;
    }
    return result;
}

int main (){
    setlocale (LC_ALL, "");
    bool t = true;
    char palavra[1000];
    int tamanho;
    while (t){
        scanf("%999[^\n]", palavra); getchar();
        tamanho = strlen(palavra);
        if(tamanho == fimlength && isFIM(palavra)){
            break;
        }
        if(isPali(palavra,tamanho)){
            printf("SIM");
        }else { printf("NAO"); }
        printf("\n");
    }
    return 0;
}


