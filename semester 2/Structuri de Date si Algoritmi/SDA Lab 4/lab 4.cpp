#include <stdio.h>
#include <conio.h>
#include <iostream>
using namespace std;

// DEFINE ITEM  //
#define tip int

// END D I //
//***********************************************************************//

// STRUCTURE ITEM //

// END S I //
//***********************************************************************//

// VARIABLE ITEM //
int n, y[20][3], sol = 0;
int dx[4] = {-1,0,1,0};
int dy[4] = {0,1,0,-1};
int a[4][4];
// END V I //
//***********************************************************************//

// FUNCTION ITEM //
int posibil(int k)
{
    if( (y[k][1] < 1) || (y[k][1] > n) ||           // verificam daca sunt
        (y[k][2] < 1) || (y[k][2] > n) ) return 0;  // coordonate posibile

    if( !a[y[k][1]][y[k][2]] ) return 0;    // verificam daca in casuta este 0 sau 1

    for(int l = 1; l <= k - 1; l++)
        if( (y[k][1] == y[l][1]) && (y[k][2] == y[l][2]) ) return 0;  // verificam daca casuta curenta este o casuta vizitata anterior
    return 1;
}

void citire(void)           // citim datele din fisier
{
    FILE *pf = fopen("mat.txt", "r");
    fscanf(pf, "%d", &n);
    for(int i = 1; i <= n; i++)
        for(int j = 1; j <= n; j++)
            fscanf(pf, "%d", &a[i][j]);
    fclose(pf);
}

void afis(void)             // afisam datele citite din fisier
{
    tip *m = &a[0][0];
    tip *i, *j;
    cout << "\n Matricea A\n\n";
    for(int i = 1; i <= n; i++)
    {
        for(int j = 1; j <= n; j++)
        {
            cout << "\t" << a[i][j];
        }
        cout << "\n";
    }
}

void scrie(int k)           // afisam la ecran solutia
{
    sol++;
    cout << "\n Solutia " << sol << "\n\n ";
    for(int i = 1; i <= k; i++)
        cout << y[i][1] << " - " << y[i][2] << "\n ";
}

void DrumF(int k)                       // daca pe un drun putem iesi
{                                       // pe maimulte iesiri le afla pe toate posibile
    int px[2] = {-1, 1};
    for(int l = 0; l < 2; l++)
    {
        y[k+1][1] = n;
        y[k+1][2] = y[k][2] + px[l];
        if(posibil(k+1))
        {
            if(y[k+1][1] == n)
            {
                scrie(k+1);
                DrumF(k+1);
            }
        }
    }
}

void DrumP(int k)                        // aflam toate drumurile posibile pina la prima iesire
{
    for(int l = 0; l < 4; l++)
    {
        y[k][1] = y[k-1][1] + dx[l];
        y[k][2] = y[k-1][2] + dy[l];
        if(posibil(k))
        {
            if(y[k][1] == n)
            {
                scrie(k);
                DrumF(k);
            }
            else DrumP(k+1);
        }
    }
}
// END F I //
//***********************************************************************//

// MAIN FUNCTION //
int main()
{
    citire();
    afis();
    for(int j = 1; j <= n; j++)                 //<-.   aflam toate intrarile posibile
        if(a[1][j])                             //  |   si cu ajutorul funciei DrumP()
        {                                       //  |   aflam daca e posibil un drum.
            y[1][1] = 1;                        //  |
            y[1][2] = j;                        //  |
            DrumP(2);                           //  |
        }                                       //<-'
    if(!sol)cout << "\nNu exista solutii\n";
//	system("pause");
	return 0;
}
// END M F //
