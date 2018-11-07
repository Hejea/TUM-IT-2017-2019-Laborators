/**
  * Laborator nr. 7
  *
  * Conditia : 12	Gasiti cel mai lung cuvint comun pentru doua propozitii date.
  *
  *@author Nicolae Heghea TI-173
  *@version Varianta 12
  */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>

int inCuvinte(char cuvinte[][256], char *ctxt) {
    char keys[] = " ,.";
    char *cuvint, txt[100];
    int k = 0, i, j;

    strcpy(txt, ctxt);
    cuvint = strtok(txt, keys);

    while(cuvint != NULL) {
        j = 1;
        for(i = 0; i < k; i++) {
            if(strcmp(cuvint, cuvinte[i]) == 0) {
                j = 0;
            }
        }
        if(j) strcpy(cuvinte[k++], cuvint);

        cuvint = strtok (NULL, keys);
    }
    return k;
}

int celeMaiLungiCuvinteComune(char rez[][256], char cuv1[][256], char cuv2[][256]) {

    int i, j, k, n, maxLegth = 0;
    i = 0;
    while(strlen(cuv1[i]) != 0) {
        j = 0;
        while(strlen(cuv2[j]) != 0) {
            if(strcmp(cuv1[i], cuv2[j]) == 0) {
                if(maxLegth < strlen(cuv1[i])) {
                    maxLegth = strlen(cuv1[i]);
                }
            }
            j++;
        }
        i++;
    }


    i = 0;
    n = 0;
    while((k = strlen(cuv1[i])) != 0) {
        if(k == maxLegth) {
            j = 0;
            while(strlen(cuv2[j]) != 0) {
                if(strcmp(cuv1[i], cuv2[j]) == 0) {
                    strcpy(rez[n++], cuv1[i]);
                }
                j++;
            }
        }
        i++;
    }

    return n;
}



int main() {

    // 15
    // patru, cinci - 5 lungime

    char s1[] = "unu doi trei, patru, cinci, unu doi, trei opt noua, patru.";
    /*
    char s2[] = "zece, unu patru trei opt cinci, patru.";//*/
    //*
    char s2[] = "zece, unu cinci trei opt, unu doi zece, cinci.";//*/


    char cuvinte1[100][256];
    char cuvinte2[100][256];
    char cuvinte3[100][256];

    int i, n1, n2, n3;

    n1 = 0;
    n2 = 0;


    n1 = inCuvinte(cuvinte1, s1);
    n2 = inCuvinte(cuvinte2, s2);
    n3 = celeMaiLungiCuvinteComune(cuvinte3, cuvinte1, cuvinte2);

    puts(s1);
    puts(s2);

    for(i = 0; i < n1; i++) {
        printf("\n %s", cuvinte1[i]);
    }
    printf("\n %d\n", n1);



    for(i = 0; i < n2; i++) {
        printf("\n %s", cuvinte2[i]);
    }
    printf("\n %d\n", n2);



    for(i = 0; i < n3; i++) {
        printf("\n %s", cuvinte3[i]);
    }
    printf("\n %d\n", n3);


    return 0;
}
