/**
  *_Laborator nr. 1
  *
  *     Conditia : Rezolvarea unei ecuatii.
  *
  *@author Nicolae Heghea TI-173
  *@version Varianta 1
***/

#include <stdio.h>
#include <conio.h>
#include <math.h>
#include <stdlib.h>

const double PI = 2 * acos(0.0);

int main()
{
    /*
    din cauza ca z, v sunt egale cu doua fractii,
    de aceea le-am separat ca   Z = zNumarator / zInpartitor;,
                                V = yNumarator / yInpartitor;,
    */

    // Variabile de separare a conditiiei problemei
    float zNumarator, zInpartitor, vNumarator, vInpartitor;

    // necunoscutele
    float z, v;

    // Variabile de calcul
    float xIntervalInceput, xIntervalSfirsit, y, c;
    xIntervalInceput = 0.345;
    xIntervalSfirsit = 7.45;
    c = 1.8;
    y = 0.570;

    // Constante
    float pasul;
    pasul = 0.1;

    // variabile auxiliare
    float i;
    // pasul curent
    i = xIntervalInceput;

    // zInpartitor nu depinde de interval (nu se modifica)
    // l-am scos in afara ciclului
    zInpartitor = (1/7) + pow(log(y), 2);

    printf(" Inceput\n");

    while(i <= xIntervalSfirsit)
    {
        zNumarator = c * pow(sin(i - (PI/3)), 4);
        z = zNumarator / zInpartitor;
        printf(" zNumarator = %.6f   zInpartitor = %.6f   pasulCurent = %.6f\n Z = %.6f\n", zNumarator, zInpartitor, i, z);

        vNumarator = c + z * z;
        vInpartitor = 3 + pow(z, 3)/5;
        v = vNumarator / vInpartitor;
        printf(" vNumarator = %.6f   vInpartitor = %.6f\n v = %.6f\n\n", vNumarator, vInpartitor, v);

        i += pasul;
    }
    return 0;
}
