/*
Pentru fisierul creat anterior sa se scrie programul care
il actualizeaza prin adaugarea a inca m studenti la
sfarsitul fisierului. Dupa adaugare se cere generarea unui
alt fisier cu numele studentilor in ordine alfabetica.
Folositi functiile qsort si strcmp, precum si pointeri
spre siruri de caractere pentru ordonare.
*/
#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <iostream>
#include <windows.h>
#include <conio.h>
using namespace std;
// DEFINE ITEM  //
#define nl cout << "\n"
const char *meniuA[] =
{   "Aauga student",
//    "Sterge student",
    "Afiseaza Lista de Studenti (ordonat)",
    "Afiseaza Lista de Studenti (neordonat)"
};

// END D I //
//***********************************************************************//
// STRUCTURE ITEM //
struct student
{
    char name[20];
};
student stud[50];
int ns = 0;
// END S I //
//***********************************************************************//
// FUNCTION ITEM //
void gotoxy( int x, int y)
{
   COORD coord;
   coord.X = x;
   coord.Y = y;
   SetConsoleCursorPosition( GetStdHandle( STD_OUTPUT_HANDLE ), coord );
}
void color(int i = 0)
{
    if(i) SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE),
    FOREGROUND_INTENSITY | BACKGROUND_GREEN | BACKGROUND_RED | BACKGROUND_BLUE | BACKGROUND_INTENSITY);
    else SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE),
    BACKGROUND_GREEN | BACKGROUND_RED | BACKGROUND_BLUE | BACKGROUND_INTENSITY);
}
void set(int current, int prewiev)
{
    color(1);
    switch(current)
    {
        case 1:
        case 2:
        case 3:
        case 4:
        {
            gotoxy(10, current);
            cout << meniuA[current-1];
        }break;
    }
    color(0);
    switch(prewiev)
    {
        case 1:
        case 2:
        case 3:
        case 4:
        {
            gotoxy(10, prewiev);
            cout << meniuA[prewiev-1];
        }break;
    }
}
int citire() //student *stud)
{
    char c, t[50];
    int ns = 0, n = 0;
    FILE *pfile;
    pfile = fopen("stud.txt","r");
    if (pfile == NULL) perror("N-a fost deschis fisierul");
    else
    {
        do
        {
            c = fgetc(pfile);
            if (c == '\n')
            {
                t[n] = '\0';
                strcpy(stud[ns].name, t);
                n = 0;
                ns++;
            }
            else
            {
                t[n] = c;
                n++;
            }
        }while(c != EOF);
    }
    fclose (pfile);
    return ns;
}

void print_stud(int len) // , struct student *array)
{
    int i;
    color(1);
    for(i = 0; i < len; i++)
        cout << " " << stud[i].name << "\n";
}
int cmp_by_name(const void *a, const void *b)
{
    struct student *ia = (struct student *)a;
    struct student *ib = (struct student *)b;
    return strcmp(ia->name, ib->name);
}
void sort_stud(int nstud) // , student *stud)
{
    qsort(stud, nstud, sizeof(struct student), cmp_by_name);
}
void export_stud(int nstud)
{
    int n;
    FILE *pfile;
    pfile = fopen("qstud.txt","w");
    for (n = 0; n < nstud; n++)
    {
        fputs(stud[n].name, pfile);
        fputc('\n', pfile);
    }
    fclose (pfile);
}

void add_stud(int *nstud, const char name[]) // , student *stud,
{
    FILE *pfile;
    pfile = fopen("stud.txt","a");
    if (pfile == NULL) perror("Error opening file");
    else
    {
        fpos_t pos;
        char c;
        fputs(name, pfile);
        fputc('\n', pfile);
    }
    fclose (pfile);
    *nstud  = citire();
}
void getmeniu(int pos)
{
    int i, j;
    system("CLS");
    for(i = 0; i < 3; i++)
    {
        gotoxy(10, i+1);
        color(0);
        cout << meniuA[i];
    }
    set(pos, 0);
}
void select(int pos)
{
    switch(pos)
    {
        case 1 :
            {
                gotoxy(1, 6);
                cout << "\n Nume / Prenume :\n";
                char name[20];
                gets(name);
                add_stud(&ns, name);
                getmeniu(pos);
            }break;
        case 4 :
            {

            }break;
        case 2 :
            {
                gotoxy(1, 6);
                cout << "Nume / Prenume :\n";
                sort_stud(ns);
                print_stud(ns);
                while(!getch());
                getmeniu(pos);
            }break;
        case 3 :
            {
                gotoxy(1, 6);
                cout << "Nume / Prenume :\n";
                citire();
                print_stud(ns);
                while(!getch());
                cout << "sfirsit";
                getmeniu(pos);
            }break;
    }

}
// END F I //
//***********************************************************************//
// MAIN FUNCTION //
int main()
{
    ns = citire();
    getmeniu(1);
    int i, j, key;
    i = 1; j = 0;
    do
    {
        key = getch();
        switch(key)
        {
            case 80:
                {
                    j = i;
                    if(i+1 > 3) i = 1; else i++;
                    set(i, j);
                }break;
            case 72:
                {
                    j = i;
                    if(i-1 < 1) i = 3; else i--;
                    set(i, j);
                }break;
            case 13:
            {
                select(i);
            }break;
        }
    }while(key != 27);
    gotoxy(10, 20);

    sort_stud(ns);
    export_stud(ns);
    return 0;
}
// END M F //

