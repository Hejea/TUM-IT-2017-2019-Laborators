#include <stdio.h>
#include <math.h>
#include <stdlib.h>
#include <iostream>
#include <string.h>
#include <fstream>
#include <iomanip>
#include "includes/meniu_consola.h"
using namespace std;

#define FILE_IN  "files/in.txt"
#define FILE_OUT "files/out.txt"

const int Nmax = 20, Nr_It = 10;

typedef double Matrice[Nmax + 1][Nmax + 2], Sir[Nmax + 1];

Matrice a, ca;
Sir x, y;
int Cod, n;
double eps = 0.001;

int Digits()
{
    if(eps < 1 && eps != 0) return fabs(log(eps));
    else return log(eps) + 1;
}

void Copy()
{
    memmove(a, ca, ( Nmax+1 ) * ( Nmax + 2 ) * sizeof(double));
}

void Citire()
{
    int p, q;
    ifstream fin(FILE_IN);
    fin >> n;
    for(p = 1; p <= n; ++p)
    {
        for(q = 1; q <= n; ++q)
            fin >> a[p][q];
        fin >> a[p][n+1];
    }
    fin.close();
    memmove(ca, a, ( Nmax+1 ) * ( Nmax + 2 ) * sizeof(double));
}

void ScrieMat()
{
    int i, j;
    cout << setprecision(4);
    for(i = 1; i <= n; ++i)
    {
        cout << "\n";
        for(j = 1; j <= n; ++j)
        {
            cout << "\t" << a[i][j];
        }
        cout << "\t| " << a[i][n + 1];
    }
    cout << "\n\n";
}

//________________________________________________________________________
//------------------------------------------------------------------------

void Maxim(int i, int &Rang)
{
    double m;
    int k;

    m = fabs(a[i][i]);
    Rang = i;
    for(k = i; k <= n; ++k)
        if(fabs(a[k][i]) > m)
        {
            m = fabs(a[k][i]);
            Rang = k;
        }
}

void Schimb(int i, int &Cod)
{
    double t;
    int Rang, j;

    Maxim(i, Rang);

    if(a[Rang][i] == 0) Cod = 1;
    else
    {
        Cod = 0;
        for(j = i; j <= n + 1; ++j)
        {
            t = a[Rang][j];
            a[Rang][j] = a[i][j];
            a[i][j] = t;
        }
    }
}

void Redus(int i)
{
    int j, k;

    for(j = i+1; j <= n+1; ++j)
        a[i][j] = a[i][j] / a[i][i];

    a[i][i] = 1;

    for(k = i+1; k <= n; ++k)
    {
        for(j = i+1; j <= n+1; ++j)
            a[k][j] = a[k][j] - a[i][j] * a[k][i];
        a[k][i] = 0;
    }
}

void Rezolv()
{
    int i, j;
    double s;

    x[n] = a[n][n+1];
    for(i = n-1; i >= 1; --i)
    {
        s = 0;
        for(j = i+1; j <= n; ++j)
            s = s + a[i][j] * x[j];
        x[i] = a[i][n+1] - s;
    }
}

void Rez_Sreen_1()
{
    for(int i = 1; i <= n; ++i)
        cout << setprecision(Digits()) << "\n x" << i << " = " << x[i];
}

int Gauss()
{
    int i;
    Cod = 0;
    Copy();
    cout << "\n\n Sistemul initial\n a =";
    ScrieMat();
    for(i = 1; i <= n; ++i)
    {
        Schimb(i, Cod);
        if(Cod != 0)
        {
            if(a[n][n] == 0 && a[n][n+1] == 0)
                cout << "\n Sistem COMPATIBIL NEDERMINAT\n ";
            else cout << "\n Sistem INCOMPATIBIL\n";
            return 0;
        }
        else Redus(i);
        cout << "\n\n Pasul : " << i << "\n a =";
        ScrieMat();
    }
    //cout << "\n\n Pasul : " << i - 1 << "\n a =";
    cout << "\n\n Solutia\n";
    Rezolv();
    Rez_Sreen_1();
    cout << "\n\n";
    return 0;
}

//________________________________________________________________________
//------------------------------------------------------------------------
int Cond_E()
{
    double max = fabs(y[1] - x[1]), aux;
    for(int i = 2; i <= n; ++i)
    {
        aux = fabs(y[i] - x[i]);
        if(max < aux) max = aux;
    }
    if(max < eps) return 0;
    return 1;
}

int Cond_1_2()
{
    int i, j;
    double C1 = 0, C2 = 0;
    Copy();
    for(i = 1; i <= n; ++i)
    {
        for(j = 1; j <= n; ++j)
            if(i != j)
            {
                C1 += fabs(a[i][j]);
                C2 += fabs(a[j][i]);
            }
        if(fabs(a[i][i]) <= C1 || fabs(a[i][i]) <= C2 ) return 0;
    }
    return 1;
}

int Jacobi()
{
    int i, j, k;
    Copy();

    cout << "\n  Sistemul Initial Si Aaproximatia initiala Initiala\n ";
    ScrieMat();

    if(!Cond_1_2())
    {
        cout << "\n  Matricea nu este convergenta\n ";
        return 0;
    }

    for(i = 1; i <= n; ++i)
    {
        x[i] = a[i][n + 1];
        y[i] = 0;
    }

    while(++k)
    {
        for(i = 1; i <= n; ++i)
        {
            y[i] = a[i][n+1]/a[i][i];
            for(j = 1; j <= n; ++j)
            {
                if(j != i) y[i] = y[i] - a[i][j] / a[i][i] * x[j];
            }
        }
        if(!Cond_E()) break;

        cout << "\n\n  Iteratia : " << k;

        for(i = 1; i <= n; ++i)
        {
            cout << setprecision(Digits()) << "\n    y" << i << " = " << y[i];
            x[i] = y[i];
        }

    }
    return 0;
}

//________________________________________________________________________
//------------------------------------------------------------------------

int Gauss_Seidel()
{
    int i, j, k;
    Copy();
    for(i = 1; i <= n; ++i)
    {
        x[i] = a[i][n + 1];
        y[i] = 0;
    }

    cout << "\n  Sistemul Initial Si Aaproximatia initiala Initiala\n ";
    ScrieMat();
    while(++k)
    {
        for(i = 1; i <= n; ++i)
        {
            y[i] = a[i][n+1]/a[i][i];
            for(j = 1; j <= i - 1; ++j)
                y[i] = y[i] - a[i][j] / a[i][i] * y[j];
            for(j = i + 1; j <= n; ++j)
                y[i] = y[i] - a[i][j] / a[i][i] * x[j];
        }
        cout << "\n\n  Iteratia : " << k;
        for(i = 1; i <= n; ++i)
            cout << setprecision(Digits()) << "\n    x" << i << " = " << x[i];

        if(!Cond_E()) break;

        for(i = 1; i <= n; ++i)
        {
            x[i] = y[i];
        }
    }
    return 0;
}

//________________________________________________________________________
//------------------------------------------------------------------------
double y3[Nmax+1], x1[Nmax+1];

void mul(double a[][Nmax+2], double x1[])
{
    double s;
    int i, j;

    for (i = 1; i <= n; ++i)
    {
        s=0.;
        for (j = 1; j <= n; ++j)

                s+=a[i][j] * x1[j];

        y3[i] = s;
    }
}

double mul(double y3[], double x1[])
{
    double s = 0.;
    int i, j;

    for (i = 1; i <= n; ++i)

        s += x1[i] * y3[i];

    return (s);
}

int Pozitiv_Definita()
{
    double val;

    // CONDITIA Aii  > 0, i=1..n
    for (int i=1; i<=n; ++i)
    {
        val=a[i][i];
        if (val < 0)
        {
            cout << "\n\n A[ " << i << ", " << i << " ] = " << setprecision(4) << a[i][i] << " - Element Negativ pe diagonala principala.";
            return 0;
        }
        if (val == 0)
        {
            cout << "\n\n A[ " << i << ", " << i << " ] = " << setprecision(4) << a[i][i] << " - Element Zerou pe diagonala principala.";
            return 0;
        }
    }

    for (int i=1; i<n; ++i)
        for (int j=i+1; j<=n; ++j)
           if (a[i][j]!=a[j][i])
           {
                cout << "\n\n Matricea nu este Simetrica";
                return 0;
           }

    for (int i=0; i<n; ++i) x1[i]=i;

    mul(a,x1);

    double ok = mul(y3,x1);

    if (ok > 0)
    {
        for (int i=0; i<=n; ++i) x1[i] = y3[i] = 0;
        return 1;
    }
    else
    {
        cout << "\n\n Matricea NU este POZITIV definita";
        return 0;
    }
}

int Cholesky()
{
    int i, j, k;
    double t, l[Nmax+1][Nmax+2]={0};
    double x1[Nmax] = {0}, y1[Nmax] = {0};

    Copy();
    cout << "\n Sistemul Initial\n";
    ScrieMat();

    if(Pozitiv_Definita())
    {

        l[1][1] = sqrt(a[1][1]);

        for(i = 2; i <= n; ++i)
            l[i][1] = a[i][1]/l[1][1];

        for(k = 2; k <= n; k++)
        {
            t = 0;
            for(j = 1; j <= k-1; j++)
                t += l[k][j] * l[k][j];

            l[k][k] = sqrt(a[k][k] - t);

            for(i = k + 1; i <= n; i++)
            {
                t = 0;
                for(j = 1; j <= k-1; j++)
                    t += l[i][j] * l[k][j];

                l[i][k] = (a[i][k] - t) / l[k][k];
            }
        }

        cout << "\n Matricea L\n";
        for(i = 1; i <= n; i++)
        {
            cout << "\n ";
            for(j = 1; j <= n; j++)
            {
                cout << "\t" << l[i][j];
            }
        }

        for(i = 1; i <= n; i++)
            for(j = i+1; j <= n; j++)
                l[i][j] = l[j][i];

        for(i = 1; i <= n; i++)
        {
            t = 0;
            for(j = 1; j <= i; j++) t += l[i][j] * y1[j];
            t = a[i][n+1] - t;
            y1[i] = t / l[i][i];
        }

        cout << "\n\n Componentele vectorului Y\n";
        for(i = 1; i <= n; i++) cout << setprecision(4) << "\n y = " << y1[i];

        for(i = n; i > 0; i--)
        {
            t = 0;
            for(j = n; j >= i; j--)
                t += l[i][j] * x1[j];
            t = y1[i] - t;
            x1[i] = t / l[i][i];
        }

        cout << "\n\n Componentele vectorului X\n";
        for(i = 1; i <= n; i++) cout << setprecision(4) << "\n x = " << x1[i];

    }

    return 0;
}
//________________________________________________________________________
//------------------------------------------------------------------------

Func_ptr fun[] = {Gauss, Jacobi, Gauss_Seidel, Cholesky};
string text[] = {"Gauss", "Jacobi", "Gauss - Seidel", "Cholesky"};
Meniu meniu;

int main()
{
    Citire();
    meniu.AddFunction(fun);
    meniu.AddMeniuText(text);
    meniu.Start();
    return 0;
}

