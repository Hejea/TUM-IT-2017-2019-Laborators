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
#define tip long long
#define nl cout << "\n"
// END D I //
//***********************************************************************//
// STRUCTURE ITEM //
// END S I //
//***********************************************************************//
// VARIABLE ITEM //
// END V I //
//***********************************************************************//
// FUNCTION ITEM //
tip** init(int n)
{
	tip **plin, *pelem;
 	int i;
   	pelem = (tip*) calloc (n * n, sizeof(tip));
  	if(pelem == (tip*) NULL)
	{
		printf("Spatiu Insuficient\n");
		exit(1);
	}
   	plin = (tip**) calloc (n, sizeof(tip*));
   	if(plin == (tip**) NULL)
	{
		printf("Spatiu Insuficient\n");
		exit(1);
	}
   	for (i = 0; i < n; i++)
	{
		plin[i] = pelem;
		pelem += n;
	}
	return plin;
}
tip destroy(tip **matrix, int n)
{
	int i;
    for(i = 0; i < n; i++) free(matrix[i]);
    free(matrix);
}
tip** submatrix(tip **matrix, int n, int i, int j)
{
	tip **subm;
	subm = init(n - 1);
	int p, q;
	int a = 0, b;
	for(p = 0; p < n; p++)
	{
		if(p == i) continue;
		b = 0;
		for(q = 0; q < n; q++)
		{
			if(q == j) continue;
			subm[a][b++] = matrix[p][q];
		}
		a++;
	}
	return subm;
}
tip detm(tip **matrix, int n)
{
	if(n == 1) return **matrix;
	int i;
	tip det = 0;
	for(i = 0; i < n; i++)
	det += (i%2 == 1?-1:1) * matrix[i][0] * detm(submatrix(matrix, n, i, 0), n - 1);
	return det;
}
void r_matrix(tip **matrix, tip n = 0)
{
    tip *i, *j;
    int k = 1;
    cout << "\n Dati elementele :\n";
    for(i = matrix[0]; i < matrix[0] + n * n; i = j)
	for(j = i; j < i + n; j++)
    {
        cout << " Elem " << k++ << " = ";
        cin >> *j;
    }
}
void w_matrix(tip **matrix, int n = 0)
{
    tip *i, *j;
    for(i = matrix[0]; i < matrix[0] + n * n; i = j)
    {
        nl;
		for(j = i; j < i + n; j++)
		cout << " " << *j;
    }
}
void transpune(tip **in, tip **out, int n)
{
	tip *i, *j, *k, *l;
	for(i = in[n-1] + n-1, k = out[0]; i > in[n-1] - 1; i--, k = l)
	{
		for(j = i, l = k; j > i - (n-1)*n-1; j -= n, l++)
		*l = *j;
	}
}
tip** multiple(tip **m1, tip **m2, int n, int nr = 1)
{
	tip *p1, *p2, *p3, *q1, *q2, *q3;
	tip s;
	tip **c;
	c = init(n);
	for(p3 = c[0], p1 = m1[0]; p3 < c[0] + n*n; p3 = q3, p1 = q1)
	{
		for(q3 = p3, p2 = m2[0]; q3 < p3 + n; q3++, p2++)
		{

			for(q2 = p2, q1 = p1, s = 0; q2 < p2 + (n-1)*n+1; q2 += n, q1++)
			{
				s += *q1 * *q2;
			}
			*q3 = s;
		}
	}
	nr--;
	if(nr > 0)c = multiple(m1, c, n, nr);
	return c;
}
// END F I //
//***********************************************************************//
// MAIN FUNCTION //
int main()
{
	tip **y, **b, **y4, **b5, **e, **r;
	int n;
	tip det;
	cout << "\n Dati lungimea n = ";
//	cin >> n;
    n = 2;
	y = init(n);	b = init(n);	y4 = init(n);
	e = init(n);	r = init(n);	b5 = init(n);
/*
	cout << "\n Citirea Matricii";
	r_matrix(y, n);
*/
    y[0][0] = 1;
    y[0][1] = 2;
    y[1][0] = 3;
    y[1][1] = 4;

	cout << "\n Matricea Y\n";
	w_matrix(y, n);

	cout << "\n Matricea Transpusa B\n";
	transpune(y, b, n);
	w_matrix(b, n);

	cout << "\n Matricea E\n";
	b5 	= multiple(b, b, n, 5);
	y4 	= multiple(y, y, n, 4);
	r  	= multiple(y, b5, n);
	e	= multiple(r, y4, n);
	w_matrix(e, n);

	det = detm(e, n);
	cout << "\n\n Determinantul matricei E";
	cout << "\n det = " << det;
	cout << "\n Matricea E este";
	(det == 0)? cout << " singulara" : cout << " nesingulara";
    destroy(y, n);	destroy(b, n);	destroy(y4, n);
    destroy(b5, n);	destroy(e, n);	destroy(r, n);
	return 0;
}
// END M F //
