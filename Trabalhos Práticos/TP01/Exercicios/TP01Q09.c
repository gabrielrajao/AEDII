#include <stdio.h>
#include <stdlib.h>

int main (){
    int n = 0;
    double numero = 0;
    FILE * arq;
    arq = fopen("./arq.txt","wb");
    scanf("%d", &n);
    for(int i = 0; i < n; i++){
        scanf("%lf", &numero);
        fwrite(&numero,sizeof(double),1,arq);
    }
    fclose(arq);
    arq = fopen("./arq.txt","rb");
    fseek(arq, -sizeof(double), SEEK_END );
    for(int i = 1; i <= n; i++){
        fread(&numero,sizeof(double),1,arq);
        printf("%g\n", numero);
        fseek(arq, -2*sizeof(double), SEEK_CUR);
    }
    fclose(arq);
    return 0;
}
