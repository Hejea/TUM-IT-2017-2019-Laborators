/**
  * Laborator nr. 2
  *
  * Conditia : Teste 1 - 7
  *
  *@author Nicolae Heghea TI-173
  *@version Varianta 12
  */

#include <stdio.h>
#include <stdlib.h>


const double PI = 3.14;

void test1() {
    printf("Hello, world!!!!!\n");
}

void test2() {

    int	int1,int2;
    long long1,long2;
    float float1, float2;
    char char1, char2, char3;
    int1 = 99;
    int2 = 0xFF01;
    long1 = -68741L;
    long2 = 0xFFF1;
    float1 = 3.14159;
    float2 = 1.5E04;
    char1 = 'C';
    char2 = 15;
    char3 = 0xF1;

    printf("int1== %d \n", int1);
    printf(" int2== %#x \n", int2);
    printf(" long1== %ld \n", long1);
    printf(" long2== %ld \n", long2);
    printf(" float1== %f \n", float1);
    printf(" PI== %f \n", PI);
    printf(" float2== %E \n", float2);
    printf(" char1== %c \n", char1);
    printf(" char2== %d \n", char2);
    printf(" char3== %c \n", char3);
}
void test3() {
    int i;
    char ch;
    float fl;

    printf("introduceti valori pentru i, c, fl\n");
    printf(" i = ");
    //scanf("%d", &i);
    i = 643;
    printf(" ch = ");
    //scanf("%c", &ch);
    ch = 'w';
    printf(" fl = ");
    //scanf("%f", &fl);
    fl = 353.452;
    printf(" i = %6d fl = %4.2f ch = %6c", i, fl, ch);
}

void test4() {
    int m,n, i = 33, j = 55;
    float f2 = 12.23f;
    m = i+j;
    n = 1;
    n += j;
    f2 /= 3;
    int z = i%5;

    printf("m = %d\tn = %d\n", m, n);
    printf(" f2 = %f\tz = %d\n", f2, z);
    printf(" ++i este %d\n", ++i);
    printf(" i este %d\n", i);
    printf(" i++ este %d\n", i++);
    printf(" i este %d\n", i);
}

void test5() {
    int m, n, i, j;
    float f2=12.23f;
    i = 33;
    j = 55;
    n = 0;
    m = i+j;
    n += j;
    f2 /= 3;


    printf("m=%d \t n=%d\n", m, n);
    printf(" f2=%f\n", f2);
    printf(" i+1 este %d iar noua valoare a lui i este %d", ++i, i);
    printf(" j+1 este %d iar noua valoare a lui j este %d\n", j++, j);
}

void test6() {
    unsigned int n,i,cont=0;
    printf ("Introduceti un numar : ");
    //scanf ("%d",&n);
    n = 11;
    for (i=2; i<floor(sqrt(n)); i++) { // impartim pe n la i care creste cu 1 de fiecare data pana la parte intreaga (floor) din radical (sqrt) de n
        if (n%i==0) { //daca n se imparte fix (%) la i atunci nu este prim
            printf(" Numarul %d nu este prim!\n",n);
            cont=1; //in cazul in care nu este prim cu ajutorul lui cont avem grija sa nu mai afiseze ca este prim
            break; //cand am aflat ca nu este prim iesim din for astfel scurtam timpul de ciclare
        }
    }
    if (cont!=1) { //daca este prim cont ramane 0 cum l-am initializat si se va afisa ca numarul n este prim
        printf(" Numarul %d este prim!\n",n);
    }
}

void test7() {
    unsigned int n, i, cont=0;
    printf ("Introduceti un numar : ");
    //scanf ("%d",&n);
    n = 45;
    for (i=2; i<=(n/2); i++) // mergem pana la jumatatea numarului
        if (n%i==0) { //daca n se imparte fix (%) la i atunci i este divizor
            printf(" Cel mai mic divizor propriu al lui %d este %d\n",n,i);
            cont=1;
            break;
        }
    if(cont==0){
        printf(" Numarul este prim!\n");
    }
}

#define n1Line printf("\n ");
#define n2Line printf("\n\n ");

int main() {

    n1Line;
    test1();
    n2Line;
    test2();
    n2Line;
    test3();
    n2Line;
    test4();
    n2Line;
    test5();
    n2Line;
    test6();
    n2Line;
    test7();
    n2Line;

    return 0;
}
