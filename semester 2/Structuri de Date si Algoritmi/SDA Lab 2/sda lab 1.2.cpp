/*
14. Din propozitia data eliminati cuvintele care se intilnesc in
propozitie de mai multe ori si inlocuiti-le cu numarul format din
suma literelor in cuvint si codul lor. Calculati si  vizualizati
frecventa repetarii fiecarei cifre din numerele obtinute.
*/
#include <stdio.h>
#include <stdlib.h>
#include <conio.h>
#include <string.h>
#include <time.h>
using namespace std;

// DEFINE ITEM  //
#define max 50
#define min 0
#define nl printf("\n")
#define sp printf(" ")

void in(int *n)
{ scanf("%d", n); }
void vout(int *n)
{ printf("%d", *n); }
void vout(char *a)
{ printf("%s", a); }

// END D I //
//***********************************************************************//
// STRUCTURE ITEM //
typedef struct
{
	int f;
	char c[max];
}trs;
// END S I //
//***********************************************************************//
// VARIABLE ITEM //

// END V I //
//***********************************************************************//
// FUNCTION ITEM //
void citire(char *pr)
{
	printf("\n Dati propozitia :\n ");
	gets(pr);
}
void strc(char *out, char *in)
{
	char *p = out, *q = in;
	while(*p)++p;
	while(*p++ = *q++);
}
void strcc(char *out, char in)
{
 	char *p = out;
	while(*p)++p;
	*p = in;
	*(++p) = '\0';
}
void pinc(char *prop, char *pc[], int *n)
{
	char *p = prop;
	int l = 0;
	while(*p)
	{
		if((*p == ' '))
		{
			while(*p == ' '){*(p) = '\0';p++;}
			l = 0;
		}
		else
		{
			if(!l)
			{
				*(pc + *n) = p;
				l = 1;
				(*n)++;
			}
			p++;
		}
	}
}
void mod(char *cuv[], int n, trs flag[], int *k)
{
	int i, j, p, q = 0, l = 0, l1 = 0;
	for(i = 0; i < n - 1; i++)
	{
		for(p = 0; p < q; p++)if(i == flag[p].f)l1 = 1;
		if(l1){l1 = 0; continue;}
		for(j = i + 1; j < n; j++)
		{
			if(strcmp(cuv[i], cuv[j]) == 0)
			{
				flag[q].f = j;
				l = 1;
				q++;
			}
		}
		if(l){flag[q++].f = i; l = 0;}
	}
	char buff[8], *w;
	int z, e, f[50], v, x;
	for(i = 0; i < q; i++)
	{
		w = cuv[flag[i].f];
		*k = strlen(w);
		flag[i].c[0] = '\0';
		while(*w)
		{
			itoa((int)(*w), buff, 10);
			strc(flag[i].c, buff);
			w++;
		}

		x = strlen(flag[i].c);
		e = 0; v = 1;l = 0; l1 = 0;
		strc(flag[i].c, " (");
        for(z = 0; z < x; z++)
		{
			for(p = 0; p < e; p++)if(z == f[p])l1 = 1;
			if(l1){l1 = 0; continue;}
            for(j = z + 1; j < x; j++)
			{
				if(flag[i].c[z] == flag[i].c[j])
				{
					v++;
					f[e] = j;
					l = 1;
					e++;
				}
			}
			strc(flag[i].c, "[");
			itoa(v, buff, 10);
			strc(flag[i].c, buff);
			strc(flag[i].c, ", ");
			strcc(flag[i].c, flag[i].c[z]);
			strc(flag[i].c, "] ");
			v = 1;
			if(l){f[e++] = z; l = 0;}
		}
		itoa(*k, buff, 10);
		strc(flag[i].c, ")");
		strc(flag[i].c, "(");
		strc(flag[i].c, buff);
		strc(flag[i].c, ")");
	}
}
void afis(char *pc[], trs flag[], int n, int k)
{
	int i, j, l = 1;
	printf("\n Propozitia Initiala :\n ");
	for(i = 0; i < n; i++)
	{
		printf("%s", pc[i]);
		if((i < n - 1))sp;else printf(".\n");
	}
	printf("\n\n Propozitia Finala :\n");
	for(i = 0; i < n; i++)
	{
		for(j = 0; j < k; j++)
		{
			if(flag[j].f == i)
			{
				printf(" %s", flag[j].c);
				l = 0;
				break;
			}
		}
		if(l){ printf(" %s", pc[i]);}else l = 1;
		if((i < n - 1))nl;
	}
}
// END F I //
//***********************************************************************//
// MAIN FUNCTION //
int main()
{
	char prop[256] = "Din propozitia data eliminati Din";
	char *pc[50];
	trs cc[max];
	int n = 0, k = 0;
//	citire(prop);
	pinc(prop, pc, &n);
	mod(pc, n, cc, &k);
	afis(pc, cc, n, k);
    return 0;
}
// END M F //
