/**
  * Laborator nr. 4
  *
  * Conditia : Tablouri Bidimensionale
  *
  *@author Nicolae Heghea TI-173
  *@version Varianta 12
  */

#include <stdio.h>
#include <stdlib.h>     /* srand, rand */
#include <time.h>
#include <windows.h>

void sector_I(int Q[][100], int n);
void sector_II(int Q[][100], int n);
void sector_III(int Q[][100], int n);
void sector_IV(int Q[][100], int n);
void sector_V(int Q[][100], int n, int *B[10000], int *m);

int main() {
    //citirea



    /* initialize random seed: */
    //srand(time(NULL)); // rand() % 10 + 1

    int Q[100][100];
    int B[100*100];

    int i, j, k, kl, kd, p;
    int n, m;

    n = 9;
    k = n / 2;
    if(n%2 == 0){
        kl = k-1;
        kd = k+1;
        p = 1;
    }else {
        kl = k;
        kd = k+1;
        p = 2;
    }



    // Crearea si Afisarea Matricii
    printf("\n%6sMatricea Q\n", "");

    for(i = 0; i < n; i++) {
        printf("\n%4s", "");
        for(j = 0; j < n; j++) {
            //Q[i][j] = rand() % 21 - 10; // [-10 , 10]
            Q[i][j] = rand() % 200 - 99; // [-100 , 100]
            if(j == kd) printf("%6s", "");
            if(j == kl) printf("%6s", "");
            printf("%4d", Q[i][j]);
        }
        kl--;
        kd++;
        if(kl == -1 || kd == n+1) {
            int temp = kl;
            kl = kd;
            kd = temp;
            kd+=p;
            kl-=p;
        }
    }


    //* sector 1
    sector_I(Q, n);
    //*/

    //* sector 2
    sector_II(Q, n);
    //*/

    //* sector 3
    sector_III(Q, n);
    //*/

    //* sector 4
    sector_IV(Q, n);
    //*/

    //* sector 5
    sector_V(Q, n, &B, &m);

    printf("\n\n\n%6sVectorul B\n\n", "");
    for(i = 0; i < m; i++) {
        printf("%5d", B[i]);
    }
    //*/




    printf("\n\n");
    return 0;
}

void sector_I(int Q[][100], int n) {
    printf("\n\n-----------------------\n\n%6sSectorul I\n", "");

    int min;
    int k = (n%2 == 0)? n / 2 - 1: n / 2;
    int kl, kd, i, j;

    min = Q[0][0];
    kl = kd = k;

    for(i = 0; i < kl; i++, kd--) {
        printf("\n%4s", "");
        for(j = 0; j < kd; j++) {
            printf("%4d", Q[i][j]);
            if(min > Q[i][j]) min = Q[i][j];
        }
    }

    printf("\n\n%6sMin = %d", "", min);
}

void sector_II(int Q[][100], int n) {
    printf("\n\n-----------------------\n\n%6sSectorul II\n", "");

    int max;
    int k = n / 2;
    int kl, kd, i, j;

    if(n%2){
        kl = k;
    }else {
        kl = k-1;
    }
    kd = k+1;
    max = Q[0][kd];
    for(i = 0; i < kl; i++) {
        printf("\n%4s", " ");
        for(int w = 0; w < i; w++) printf("%4s", "");
        for(j = kd++; j < n; j++) {
            printf("%4d", Q[i][j]);
            if(max < Q[i][j]) max = Q[i][j];
        }
    }

    printf("\n\n%6sMax = %d", "", max);
}

void sector_III(int Q[][100], int n) {
    printf("\n\n-----------------------\n\n%6sSectorul III\n", "");

    int sum;
    int k = n / 2;
    int kl, kd, i, j;

    kl = k + 1;
    kd = n - 1;
    sum = 0;

    for(i = kl; i < n; i++) {
        printf("\n%4s", " ");
        for(int w = n-1; w > i; w--) printf("%4s", "");
        for(j = kd--; j < n; j++) {
            printf("%4d", Q[i][j]);
            sum += Q[i][j];
        }
    }

    printf("\n\n%6sSum = %d", "", sum);
}

void sector_IV(int Q[][100], int n) {
    printf("\n\n-----------------------\n\n%6sSectorul IV\n", "");

    int max;
    int k = n / 2;
    int kl, kd, i, j;

    kl = k + 1;
    kd = 1;
    max = Q[kl][kd];

    for(i = kl; i < n; i++, kd++) {
        printf("\n%4s", " ");
        for(j = 0; j < kd; j++) {
            printf("%4d", Q[i][j]);
            if(max < Q[i][j]) max = Q[i][j];
        }
    }

    printf("\n\n%6sMax = %d", "", max);
}

void sector_V(int Q[][100], int n, int *B[10000], int *m) {
    printf("\n\n-----------------------\n\n%6sSectorul V\n", "");

    int k = n / 2;
    int kl, kd, i, j;

    kl = k;
    if(n%2 == 0){
        kd = k-1;
    }else {
        kd = k;
    }
    *m = 0;

    for(i = 0; i < k; i++, kd--, kl++) {
        printf("\n%6s", " ");
        for(int w = n/2 + ((n%2)? 1:0) ; w > i; w--) printf("%4s", "");
        for(j = kd; j < kl+1 && kl < n; j++) {
            printf("%4d", Q[i][j]);
            B[(*m)++] = Q[i][j];
        }
    }


    kd = 0;
    kl = n;

    for(i = k; i < n; i++, kl--) {
        printf("\n%6s", " ");
        for(int w = n/2 - 1; w < i; w++) printf("%4s", "");
        for(j = kd++; j < kl && kl > -1; j++) {
            printf("%4d", Q[i][j]);
            B[(*m)++] = Q[i][j];

        }
    }
}
