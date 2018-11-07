#include <stdio.h>
#include <stdlib.h>
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

// END V I //
//***********************************************************************//

// FUNCTION ITEM //
void citire(tip *m, tip n)
{
    tip *i, *j;
    int k = 0;
    cout<<" Dati elementele: \n";
    for(i = m; i < m + n * n; i = j)
    {
        for(j = i; j < i + n; j++)
        {
            k++;
            cout << " elem " << k << " = ";
            cin >> *j;
        }
    }
}

void afis(tip *m, tip n)
{
    tip *i, *j;
    cout << "\n Matricea A\n\n";
    for(i = m; i < m + n * n; i = j)
    {
        for(j = i; j < i + n; j++)
        {
            cout << "\t" << *j;
        }
        cout << "\n";
    }

}

void swap(tip *a)
{
    (*a != 0)? *a = -*a : *a = 0;
}

void transforma(void ( &func)(tip*), tip a[][4], tip n)
{
    int i, j, k;
    for(i = 0, k = n - 1; i < n; i++,k--)
    {
        for(j = 0; j < n; j++)
        {
            if( j != i)
            {
                func(&a[i][j]);
                if( k == j ) func(&a[i][k]);
            }
        }
    }
}

// END F I //
//***********************************************************************//

// MAIN FUNCTION //
int main()
{
	tip a[4][4] = { {1, 2, 3, 4},
                    {4, 5, 6, 7},
                    {7, 8, 9, 0},
                    {2, 3, 5, 6}
                    };

    citire(&a[0][0], 4);
    transforma(swap, &a[0], 4);
    afis(&a[0][0], 4);

	system("pause");
	return 0;
}
// END M F //
