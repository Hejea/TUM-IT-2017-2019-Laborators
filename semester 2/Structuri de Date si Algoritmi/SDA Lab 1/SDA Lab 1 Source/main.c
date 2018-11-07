/**
  * Laborator nr. SDA 1
  *
  * Conditia : Tablouri Bidimensionale (*pointeri)
  *
  *@author Nicolae Heghea TI-173
  *@version Varianta 12
  */

#include <stdio.h>
#include <stdlib.h>     /* srand, rand */
#include <time.h>
#include <string.h>

const char *marginLeft = "\n%2s";
const char *cellChar = "%4d";
const char *sCellChar = "%4s";
const char *sEmptyCellSpace = "%6s";

int** createMatrix(int nRow, int mCol);
int* createVector(int nSize);
void freeMem(int **matrix, int nRow, int *vector);
void showMatrix(int **matrix, int nRow, int mCol);
void showAllSections(int **matrix, int nRow, int mCol);
void section_I(int **matrix, int nRow);
void section_II(int **matrix, int nRow);
void section_III(int **matrix, int nRow);
void section_IV(int **matrix, int nRow);
void section_V(int **matrix, int nRow, int *B, int *nSize);
void getTitle(int n);
void setTitleMargin(int n);

char *title[] = {
    /*0*/   "\n\n-----------------------\n\n%2sMatricea Q\n",
    /*1*/   "\n\n-----------------------\n\n%2sMatricea Q - in sectiuni\n",
    /*2*/   "\n\n-----------------------\n\n%2sSection I\n",
    /*3*/   "\n\n-----------------------\n\n%2sSection II\n",
    /*4*/   "\n\n-----------------------\n\n%2sSection III\n",
    /*5*/   "\n\n-----------------------\n\n%2sSection IV\n",
    /*6*/   "\n\n-----------------------\n\n%2sSection V\n",
    /*7*/   "\n\n%2sMin = %d\n",
    /*8*/   "\n\n%2sMax = %d\n",
    /*9*/   "\n\n%2sSum = %d\n",
    /*10*/  "\n\n-----------------------\n\n%2sVectorul B\n\n",
    /*11*/  NULL
};

int main() {
    /* initialize random seed: */
    //srand(time(NULL));

    // matricea NxN elemente
    // vector - pentru a transforma matricea in vector
    int **Q;
    int *B;

    int i;
    int nRow, nSize;

    nRow = 5;
    nSize = nRow * nRow;

    // creare / initiere matrice / vector
    Q = createMatrix(nRow, nRow);
    B = createVector(nSize);

    // afisare matrice
    printf(title[0], "");
    showMatrix(Q, nRow, nRow);

    printf(title[1], "");
    showAllSections(Q, nRow, nRow);

    // section 1
    printf(title[2], "");
    section_I(Q, nRow);

    // section 2
    printf(title[3], "");
    section_II(Q, nRow);

    // section 3
    printf(title[4], "");
    section_III(Q, nRow);

    // section 4
    printf(title[5], "");
    section_IV(Q, nRow);

    // section 5
    printf(title[6], "");
    section_V(Q, nRow, B, &nSize);

    printf(title[10], "");
    for(i = 0; i < nSize; i++) {
        printf(cellChar, *(B + i));
    }

    freeMem(Q, nRow, B);
    printf("\n\n");
    return 0;
}

int** createMatrix(int nRow, int mCol){
    int **newMatrix = NULL;
    int i, j;

    newMatrix = malloc(nRow * sizeof(int*));
    if(newMatrix != NULL){
        for(i = 0; i < nRow; i++){
            *(newMatrix + i) = malloc(mCol * sizeof(int));
            if(*(newMatrix + i) != NULL){
                // initiere cu numere random
                for(j = 0; j < mCol; j++){
                    //*(*(a + i) + j) = rand() %  21 - 10; // [-10 , 10]
                    *(*(newMatrix + i) + j) = rand() % 200 - 99; // [-100 , 100]
                }
            }
        }
    }
    return newMatrix;
}

int* createVector(int nSize){
    int *newVector = NULL;

    newVector = malloc(nSize * sizeof(int));

    return newVector;
}

void freeMem(int **matrix, int nRow, int *vector){
    int i;
    for(i = 0; i < nRow; i++){
        free(*(matrix + i));
    }
    free(matrix);
    free(vector);
}

void showMatrix(int **matrix, int nRow, int mCol){
    int i, j;
    for(i = 0; i < nRow; i++){
        printf(marginLeft, "");
        for(j = 0; j < mCol; j++){
            printf(cellChar, *(*(matrix + i) + j));
        }
    }
}

void showAllSections(int **matrix, int nRow, int mCol){
    int i, j;
    int kMid, kLeft, kRight, posError, temp;

    kMid = nRow / 2;
    if(nRow % 2 == 0){
        kLeft = kMid-1;
        kRight = kMid+1;
        posError = 1;
    }else {
        kLeft = kMid;
        kRight = kMid+1;
        posError = 2;
    }

    // afisarea Matricii
    for(i = 0; i < nRow; i++) {
        printf(marginLeft, "");
        for(j = 0; j < mCol; j++) {
            if(j == kRight) printf(sEmptyCellSpace, ""); // space
            if(j == kLeft) printf(sEmptyCellSpace, "");
            printf(cellChar, *(*(matrix + i) + j));
        }
        kLeft--;
        kRight++;
        if(kLeft == -1 || kRight == nRow + 1) {
            temp = kLeft;
            kLeft = kRight;
            kRight = temp;
            kRight += posError;
            kLeft -= posError;
        }
    }
}

void section_I(int **matrix, int nRow) {
    int min;
    int kMid, kLeft, kRight;
    int i, j;

    min = **matrix;
    kMid = (nRow % 2 == 0)? nRow / 2 - 1: nRow / 2;
    kLeft = kRight = kMid;

    for(i = 0; i < kLeft; i++, kRight--) {
        printf(marginLeft, "");
        for(j = 0; j < kRight; j++) {
            printf(cellChar, *(*(matrix + i) + j));
            if(min > *(*(matrix + i) + j)) min = *(*(matrix + i) + j);
        }
    }

    printf(title[7], "", min);
}

void section_II(int **matrix, int nRow) {
    int max;
    int kMid, kLeft, kRight;
    int i, j;
    int nEmptySpase;

    kMid = nRow / 2;
    kRight = kMid + 1;
    kLeft = (nRow%2)? kMid : kMid - 1;
    max = *(*matrix + kRight);

    for(i = 0; i < kLeft; i++) {
        printf(marginLeft, "");
        for(nEmptySpase = 0; nEmptySpase < i; nEmptySpase++) printf(sCellChar, "");
        for(j = kRight++; j < nRow; j++) {
            printf(cellChar, *(*(matrix + i) + j));
            if(max < *(*(matrix + i) + j)) max = *(*(matrix + i) + j);
        }
    }

    printf(title[8], "", max);
}

void section_III(int **matrix, int nRow) {
    int sum;
    int kMid, kLeft, kRight;
    int i, j;
    int nEmptySpase;

    kMid = nRow / 2;
    kLeft = kMid + 1;
    kRight = nRow - 1;
    sum = 0;

    for(i = kLeft; i < nRow; i++) {
        printf(marginLeft, "");
        for(nEmptySpase = nRow-1; nEmptySpase > i; nEmptySpase--) printf(sCellChar, "");
        for(j = kRight--; j < nRow; j++) {
            printf(cellChar, *(*(matrix + i) + j));
            sum += *(*(matrix + i) + j);
        }
    }

    printf(title[9], "", sum);
}

void section_IV(int **matrix, int nRow) {
    int max;
    int kMid, kLeft, kRight;
    int i, j;

    kMid = nRow / 2;
    kLeft = kMid + 1;
    kRight = 1;
    max = *(*(matrix + kLeft) + kRight);

    for(i = kLeft; i < nRow; i++, kRight++) {
        printf(marginLeft, "");
        for(j = 0; j < kRight; j++) {
            printf(cellChar, *(*(matrix + i) + j));
            if(max < *(*(matrix + i) + j)) max = *(*(matrix + i) + j);
        }
    }

    printf(title[8], "", max);
}

void section_V(int **matrix, int nRow, int *B, int *nSize) {
    int kMid, kLeft, kRight;
    int i, j;
    int nEmptySpase;

    *nSize = 0;
    kMid = nRow / 2;
    kLeft = kMid;
    kRight = (nRow % 2 == 0) ? kMid - 1 : kMid;

    for(i = 0; i < kMid; i++, kRight--, kLeft++) {
        printf(marginLeft, "");
        for(nEmptySpase = nRow/2 + ((nRow%2)? 1:0) ; nEmptySpase > i; nEmptySpase--) printf(sCellChar, "");
        for(j = kRight; j < kLeft+1 && kLeft < nRow; j++) {
            printf(cellChar, *(*(matrix + i) + j));
            *(B + (*nSize)++) = *(*(matrix + i) + j);
        }
    }

    kRight = nRow;
    kLeft = 0;

    for(i = kMid; i < nRow; i++, kRight--) {
        printf(marginLeft, "");
        for(nEmptySpase = nRow / 2 - 1; nEmptySpase < i; nEmptySpase++) printf(sCellChar, "");
        for(j = kLeft++; j < kRight && kRight > -1; j++) {
            printf(cellChar, *(*(matrix + i) + j));
            *(B + (*nSize)++) = *(*(matrix + i) + j);
        }
    }
}



