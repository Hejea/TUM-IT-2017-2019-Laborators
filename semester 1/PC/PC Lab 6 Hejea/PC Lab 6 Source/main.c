/**
  * Laborator nr. 6
  *
  * Conditia : Prelucrarea sirurilor caracteriale
  *
  *@author Nicolae Heghea TI-173
  *@version Varianta 12
  */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

const char *csir1 = "Aceasta este Lucrarea de laborator nr. 6 \"Prelucrarea sirurilor caracteriale\"";
const char *csir2 = "123456 789012 3 45 678 9012";

void citesteSir(char *s){
    printf("\n Dati un sir :\n\n ");
    //gets(s);
    //strcpy(s, csir1);
    strcpy(s, csir2);
}

void afisareSir(char *s){
    printf("\n\n Sirul este :\n\n %s\n", s);
}

void afisareSir2(char vS[][256], char *s){

    printf("\n\n Sirul prelucrat este :\n");

    int i, j, k, p, p1;

    i = 0;
    k = 0;
    while(s[i] != '\0' && s[i + 4] != '\0'){

        j = 0;
        p = 4;
        p1 = 0;
        while(s[j] != '\0' && j < p){
            if(s[i + j] == ' ') {
                p++;
                j++;
            }
            vS[k][p1] = s[i + j];
            j++;
            p1++;
        }
        vS[k][j] = '\0';
        printf("\n %s", vS[k]);
        k++;
        i++;
    }
    printf("\n");
}

int main()
{
    char txt[256], vTxt[100][256];

    citesteSir(txt);

    afisareSir(txt);

    afisareSir2(vTxt, txt);

    return 0;
}
