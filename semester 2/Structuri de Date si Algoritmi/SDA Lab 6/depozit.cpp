#include <stdio.h>
#include <stdlib.h>
#include <conio.h>
#include <iostream>
#include <fstream>
using namespace std;

// DEFINE ITEM  //
#define MAXINT 9999
#define nl  cout << "\n"
#define nll "\n"
#define sp " "
#define da cout << " Da "
// END D I //
//***********************************************************************//
// STRUCTURE ITEM //

// END S I //
//***********************************************************************//
// VARIABLE ITEM //
int n, i, j, k, v, cost;
long s[100], t[100];
long a[50][50];

// END V I //
//***********************************************************************//
// FUNCTION ITEM //

void Citire()
{
    int i, j, x, y, c;

    ifstream fin("depozit.txt");
    fin >> n;
    while( fin >> x >> y >> c )
    {
        a[x][y] = a[y][x] = c;
    }
    cout << "\n Matricea custurilor este :\n";
    for( i = 1; i <= n; i++)
    {
        for( j = 1; j <= n; j++) cout << " " << a[i][j];
        cout << "\n";
    }
    fin.close();
}

void Prim()
{
    int i, j, min;
    for( i = 1; i <= n; i++) s[i] = v;
    s[v] = 0;
    for( i = 1; i <= n; i++) t[i] = 0;
    cost = 0;
//    nl; nl;
    for( k = 1; k < n; k++)
    {
        min = MAXINT;
        for( i = 1; i <= n; i++)
        {

            if( s[i] )
                if( a[s[i]][i] < min && a[s[i]][i] )
                {
                    min = a[s[i]][i];
                    j = i;
                }
//            cout << sp << j << sp << s[i] << sp << min << sp << a[s[i]][i];
        }
//        cout << nll << nll << j << sp << min << sp << s[j] << sp << a[s[j]][j];
        t[j] = s[j];
        cost += a[j][s[j]];
        s[j] = 0;
        for( i = 1; i <= n; i++)
        {
            if( s[i] )
                if( a[i][s[i]] == 0 || a[i][s[i]] > a[i][j] )
                    if ( a[i][j] )
                    {
                        s[i] = j;
//                        cout << nll << i << sp << j << sp << s[i] << sp << a[i][s[i]] << sp << a[i][j];
                    }

        }
/*        nl;
        for( i = 1; i <= n; i++) cout << s[i] << sp;
        nl;
        for( i = 1; i <= n; i++) cout << t[i] << sp;
        nl;
*/
    }
}

int Fii( int x)
{
    int k = 0;
    for( i = 1; i <= n; i++)
    {
        if( t[i] == x ) k++;
        //cout << nll << t[i] << sp << x << sp << k;
    }
    return k;
}

void Tata( int x)
{
    for( i = 1; i <= n; i++)
    {
        //cout << nll << t[i] << sp << i << sp << t[x] << sp << x;
        if( t[x] == i )
        {

            t[i] = x;
            t[x] = 0;
            da;
        }
        //cout << nll << t[i] << sp << i << sp << t[x] << sp << x;nl;
    }
}

void Oras()
{
    int max = 0, i, j, k = 0, f = 0;
    for( i = 1; i <= n; i++)
    {
        f = Fii(i);
        if( f > max ) max = f;
        //cout << nll << i << sp << max << sp << f << nll;
    }
    cout << " \n Orase optime sunt :";
    for( i = 1; i <= n; i++)
    {
        if( Fii(i) == max )
        {
            cout << "\n\t" << ++k << " ";
            Tata(i);
//            cout << nll nll << i << sp << max << sp << Fii(i);
            cout << " Vector Tata este : ";
            for( j = 1; j <= n; j++) cout << t[j] << " ";
            cout << "\n";
        }
    }
}

// END F I //
//***********************************************************************//
// MAIN FUNCTION //
int main()
{
    Citire();
    cout << " Dati virful de la care pornim : ";
    cin >> v;
    Prim();
    cout << " Costul arborelui este : " << cost;
    Oras();
    cout << "\n";
    system ("pause");
	return 0;
}
// END M F //
