/**
  * Laborator nr. 3
  *
  * Conditia : Tablouri Unidimensionale
  *
  *@author Nicolae Heghea TI-173
  *@version Varianta 12
  */

#include <stdio.h>
#include <stdlib.h>

int cubeOfNumber(int number) {
    return number * number * number;
}

int sumOfCubesOfDigits(int value){
    int sumOfCubes;
    int contor;
    sumOfCubes = 0;
    contor = 0;
    do{
        contor = value % 10;
        value /= 10;
        sumOfCubes += cubeOfNumber(contor);
        //printf("i = %d\ns = %d\n\n", i, s);
    }while(value != 0);
    return sumOfCubes;
}

int CubesEqualNumber(int value){
    int sumOfCubes;


    for(int i = 0; i <= value; i++) {
        sumOfCubes = sumOfCubesOfDigits(i);
        if(i == sumOfCubes) {
            printf(" value = %d      sum = %d\n", i, sumOfCubes);
        }
    }
    return sumOfCubes;
}

int main()
{
    int value; // max = 9999

    value = 9999;

    printf("\n");

    CubesEqualNumber(value);

    return 0;
}
