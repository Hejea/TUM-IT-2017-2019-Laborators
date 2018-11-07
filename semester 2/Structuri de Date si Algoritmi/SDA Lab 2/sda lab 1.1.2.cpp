/*
lab 6;
23. Pentru matricea data Y={yi,j}, i=1,n; j=1,n;
efectuati transpunerea fata de diagonala secundara si obtineti matricea B,
apoi calculati E=Y*B5*Y4. Verificati daca matricea E este singulara sau nu.
*/
#include <conio.h>
#include <stdio.h>
#include <stdlib.h>
#include <iostream>
using namespace std;
// DEFINE ITEM  //
#define max 50
#define min 0
#define out(a) cout << #a
#define nl cout << "\n "
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
void in(long *n)
{ cin >> *n; }
long** init(int n)
{
    int i;
    long **matrix;
    matrix = new long* [n];
    for(i = 0; i < n; i++)
    {
        matrix[i] = new long[n];
    }
    return matrix;
}
void destroy(long **matrix, int n)
{
	int i;
    for(i = 0; i < n; i++) free(matrix[i]);
    free(matrix);
}
long** submatrix(long **matrix, int n, int i, int j)
{
	long **subm;
	long p, q;
	long a = 0, b;
	subm = new long* [n - 1];
	for(p = 0; p < n; p++) {
		if(p==i) continue;
			subm[a] = new long[n - 1];
			b = 0;
		for(q = 0; q< n; q++) {
				if(q==j) continue;
			subm[a][b++] = matrix[p][q];
		}
		a++;
	}
	return subm;
}
long detm(long **matrix, int n)
{
	if(n == 1)return **matrix;
	long i;
	long det = 0;
	for(i = 0; i < n; i++)
	det +=(i%2==1?-1:1)*matrix[i][0]*detm(submatrix(matrix, n, i, 0), n - 1);
	return det;
}
void r_matrix(long **matrix, int n = 0)
{
    int i, j;
    nl;
    out(Dati elementele :\n);
    for(i = 0; i < n; i++)
    {
        for(j = 0; j < n; j++)
        {
        cout << " Y[ " << i+1 << ", " << j+1 << " ] = ";
        in(&matrix[i][j]);
        }
    }
}
void w_matrix(long **matrix, int n = 0)
{
    int i, j;
    for(i = 0; i < n; i++)
    {
        nl;
		for(j = 0; j < n; j++)
        {
			cout << " " << matrix[i][j];
        }
    }
}
void transpune(long **in, long **out, int n)
{
	long i, j, k, l;
	for(j = n-1, k = 0; j >= 0; j--, k++)
	{
	    for(i = n-1, l = 0; i >= 0; i--, l++)
	    {
	      out[k][l] = in[i][j];
	    }
	}
}
long** multiple(long **m1, long **m2, int n, int nr = 1)
{
	long i,j,k;
	long s;
	long **c1;
	c1 = new long* [n];
	for(i = 0; i < n; i++)
	{
		c1[i] = new long [n];
	    for(j = 0; j < n; j++)
	    {
	      	s = 0;
	      	for(k = 0; k < n; k++)s += m1[i][k]* m2[k][j];
	      	c1[i][j] = s;
	    }
	}
	nr--;
	if(nr > 0)c1 = multiple(m1, c1, n, nr);
	return c1;
}
// END F I //
//***********************************************************************//
// MAIN FUNCTION //
int main()
{
	long **y, **b, **y4, **b5, **e, **r;
	int n;
	long det;
	out(\n Dati lungimea n =); sp; cin >> n;
	y = init(n);	b = init(n);	y4 = init(n);
	e = init(n);	r = init(n);	b5 = init(n);

	out(\n Citirea Matricii);
	r_matrix(y, n);

	out(\n Matricea Y\n);
	w_matrix(y, n);

	out(\n Matricea Transpusa B\n);
	transpune(y, b, n);
	w_matrix(b, n);

	out(\n Matricea E\n);
	b5 	= multiple(b, b, n, 5);
	y4 	= multiple(y, y, n, 4);
	r  	= multiple(y, b5, n);
	e	= multiple(r, y4, n);
	w_matrix(e, n);

	det = detm(e, n);
	out(\n\n Determinantul matricei E);
	cout << "\n det = " << det;
	out(\n Matricea E este);
	(det == 0)? cout << " singulara" : cout << " nesingulara";
    destroy(y, n);	destroy(b, n);	destroy(y4, n);
    destroy(b5, n);	destroy(e, n);	destroy(r, n);
	return 0;
}
// END M F //
