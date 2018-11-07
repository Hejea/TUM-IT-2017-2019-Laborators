/*
lab 6;

lab 7;

*/
#include <stdio.h>
#include <stdlib.h>
#include <iostream>
using namespace std;

// DEFINE ITEM  //
#define max 50
#define min 0
#define out(a) cout << " " << #a << " "
#define nl cout << '\n'
#define sp cout << " "
int in()
{
    int a;
    cin >> a;
    return a;
}

void vout(int a)
{
    cout << a;
}
// END D I //
//***********************************************************************//
// STRUCTURE ITEM //

// END S I //
//***********************************************************************//
// VARIABLE ITEM //

// END V I //
//***********************************************************************//
// FUNCTION ITEM //
int** init(int n)
{
    int i, j;
    int **matrix;
    matrix = new int* [n];
    for(i = 0; i < n; i++)
    {
        matrix[i] = new int[n];
    }
    return matrix;
}

int** r_matrix(int n = 0)
{
    int i, j;
    int **matrix;
    matrix = new int* [n];
    out( Dati elementele : );
    nl;
    for(i = 0; i < n; i++)
    {
        matrix[i] = new int[n];
        for(j = 0; j < n; j++)
        {
            out( y[ );
        vout( i+1 );
        out( ][ );
        vout( j+1 );
        out( ]= );
        matrix[i][j] = in();
        }
    }
    return matrix;
}
void w_matrix(int **matrix, int n)
{
    int i, j;
    for(i = 0; i < n; i++)
    {
        nl;
        for(j=0;j<n;j++)
        {
            sp;
            vout( matrix[i][j] );
        }
    }
}

// sector 1 //

}
// END F I //
//***********************************************************************//
// MAIN FUNCTION //
int main()
{

    return 0;
}
// END M F //
