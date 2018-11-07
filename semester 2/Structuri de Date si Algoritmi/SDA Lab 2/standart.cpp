#include <stdio.h>
#include <stdlib.h>
#include <iostream>
using namespace std;

// DEFINE ITEM  //
#define tip int
#define max 50
#define min 0
#define nl cout << '\n'
#define sp cout << " "
// END D I //
//***********************************************************************//
// STRUCTURE ITEM //

// END S I //
//***********************************************************************//
// VARIABLE ITEM //

// END V I //
//***********************************************************************//
// FUNCTION ITEM //
void transpune(tip *(in[3]), tip *(out[3]), int n)
{
	tip *i, *j, *k, *l;
	for(i = in[n-1] + n-1, k = out[0]; i > in[n-1] - 1; i--, k = l)
	{
		for(j = i, l = k; j > i - (n-1)*n-1; j -= n, l++)
		*l = *j;
	}
}
// END F I //
//***********************************************************************//
// MAIN FUNCTION //
int main()
{
	int a[3][3] = { {1, 2, 3},
					{4, 5, 6},
					{7, 8, 9}
				  };
	int c[3][3] = {0};
	int *p1, *q1, *p2, *q2, *p3, *q3;
	int n = 3, s = 0;
	for(p2 = a[0]; p2 < a[0] + n*n; p2 = q2)
	{
		for(q2 = p2; q2 < p2 + n; q2++)
		cout << " " << *q2;
		cout << "\n";
	}
	nl;
	nl;

    for(q1 = a[0]; q1 < a[0] + (n-1)*n+1; q1 += n)
	{
		cout << " " << *q1;
	}

	nl;
	nl;

	for(p1 = a[n-1] + n-1; p1 > a[n-1] - 1; p1--)
	{
		for(q1 = p1; q1 > p1 - (n-1)*n-1; q1 -= n)
        cout << " " << *q1;
		cout << "\n";
	}



	nl;
	nl;
/*
	for(p3 = c[0], p1 = a[0]; p3 < c[0] + n*n; p3 = q3, p1 = q1, p3 = q3)
	{
		for(q3 = p3, p2 = a[0]; q3 < p3 + n; q3++, p2++)
		{

			for(q2 = p2, q1 = p1, s = 0; q2 < p2 + (n-1)*n+1; q2 += n, q1++)
			{
				s += *q1 * *q2;
			}
			*q3 = s;
		}
	}
*/

	for(p2 = c[0]; p2 < c[0] + n*n; p2 = q2)
	{
		for(q2 = p2; q2 < p2 + n; q2++)
		cout << " " << *q2;
		cout << "\n";
	}
	system("pause");
    return 0;
}
// END M F //

