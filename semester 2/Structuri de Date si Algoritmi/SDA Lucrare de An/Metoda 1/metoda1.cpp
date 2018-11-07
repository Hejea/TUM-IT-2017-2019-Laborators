#include <fstream>
#include <iostream>
#include <windows.h>
#include <conio.h>
#include <stdlib.h>
using namespace std;

// DEFINE ITEM  //
#define InFile "taxe1.in"
#define OutFile "taxe1.out"
#define null NULL
#define tip long
// END D I //
//***********************************************************************//

// STRUCTURE ITEM //
struct Lista
{
    tip x;
    tip y;
    Lista *next;
};

// END S I //
//***********************************************************************//

// VARIABLE ITEM //
const char *meniu1[] =
{
    "Afiseaza Fisierul de Intrare",
    "Afiseaza Fisierul de Iesire",
    "Editeza Fisierul de Intrare",
    "Informatii si Ajutor",
    "Iesire",
    "\n\n Esc -> Iesire."
};

tip map[100][100],
    m[100][100],
    dy[]={1,-1,0,0},
    dx[]={0,0,1,-1},
    n[2],
    rez;

Lista *f1 = null,
      *ultim = null;

bool  ok = 0;

// END V I //
//***********************************************************************//

// FUNCTION ITEM //
int IsNumeric(const char *p)
{
    for ( ; *p; p++)
    if (*p < '0' || *p > '9')
        return 0;
    return 1;
}

void GotoXY( int x, int y)
{
   COORD coord;
   coord.X = x;
   coord.Y = y;
   SetConsoleCursorPosition( GetStdHandle( STD_OUTPUT_HANDLE ), coord );
}

void Color(int i = 0)
{
    if(i) SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE),
    FOREGROUND_INTENSITY | BACKGROUND_GREEN | BACKGROUND_RED | BACKGROUND_BLUE | BACKGROUND_INTENSITY);
    else SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE),
    BACKGROUND_GREEN | BACKGROUND_RED | BACKGROUND_BLUE | BACKGROUND_INTENSITY);
}

void Set(int current, int prewiev)
{
    Color(1);
    switch(current)
    {
        case 1:
        case 2:
        case 3:
        case 4:
        case 5:
        {
            GotoXY(10, current);
            cout << meniu1[current-1];
        }break;
    }
    Color(0);
    switch(prewiev)
    {
        case 1:
        case 2:
        case 3:
        case 4:
        case 5:
        {
            GotoXY(10, prewiev);
            cout << meniu1[prewiev-1];
        }break;
    }
}

void GetMeniu(int pos)
{
    int i;
    system("CLS");
    for(i = 0; i < 5; i++)
    {
        GotoXY(10, i + 1);
        Color(0);
        cout << meniu1[i];
    }
    Set(pos, 0);
}

void Ad(Lista *&stiva, tip x, tip y)
{
    Lista *NodNou;
    NodNou = new (nothrow)(Lista);
    NodNou->x = x;
    NodNou->y = y;
    NodNou->next = null;
    if(!stiva) stiva = NodNou;
    else ultim->next = NodNou;
	ultim = NodNou;
}

void Ext(Lista *&stiva)
{
    if(stiva)
    {
        Lista *NodS;
        NodS = stiva;
        stiva = stiva->next;
        delete NodS;
    }
}

void Citire()
{
    ifstream fin(InFile);
    tip i = 0, j = 0;
    while(i < 2){ fin >> n[i++]; }
    i = 0;
    while(i < n[1] && j < n[1])
    {
        if(!(fin >> m[i][j])) m[i][j] = 0;
        j++;
        if(j >= n[1])
        {
            j = 0;
            i++;
        }
    }
    fin.close();
}

int Posibil(int x, int y)
{
    if( (x < 0) || (x > n[1] - 1) ||
        (y < 0) || (y > n[1] - 1) ) return 0;
    return 1;
}

void Lee()
{
    int x = 0, y = 0, i = 0, cx, cy;
    map[x][y] = m[x][y];
    Ad(f1, 0, 0);
    while(1)
    {
        cx = 0; cy = 0;
        for(i = 0; i < 4; i++)
        {
            cx = f1->x + dx[i];
            cy = f1->y + dy[i];
            if(Posibil(cx, cy))
            {
                if(map[cx][cy] == 0 || map[cx][cy] > m[cx][cy] + map[f1->x][f1->y])
                {
                    if(n[0] >= m[f1->x][f1->y])
                    {
                        Ad(f1, cx, cy);
                        map[cx][cy] = m[cx][cy] + map[f1->x][f1->y];
                    }
                }
            }
        }
        Ext(f1);
        if(f1 != null)
        {
            x = f1->x;
            y = f1->y;
        }
        else break;
        if(x == n[1] - 1 && y == n[1] - 1) ok = 1;
    }
    if(ok)
    {
        if(n[0] - map[n[1] - 1][n[1] - 1] >= 0)
            rez = n[0] - map[n[1] - 1][n[1] - 1];
        else rez = -1;
    }
    else rez = -1;
}

void Scrie()
{
    ofstream fout(OutFile);
    fout << rez << "\n";
    fout.close();
}

void Wait()
{
    while(!(getch() == 27));
}

void FDIA()
{
    system("CLS");
    cout << "\n S euro\t\t= " << n[0]
         << "\n Numarul N\t= " << n[1];
    cout << "\n\n Elementele sunt";
    for(int i = 0; i < n[1]; i++)
    {
        cout << "\n ";
        for(int j = 0; j < n[1]; j++)
            cout << m[i][j] << "\t";
    }
    cout << meniu1[5];
    Wait();
}

void FDIE()
{
    system("CLS");
    ofstream fout(InFile);
    char k[10];
    tip i = 0, j = 0;
    while(1)
    {
        GotoXY(0, 1);
        cout << " S euro\t    =                 ";
        GotoXY(14, 1);
        cin >> k;
        if( !IsNumeric(k)) continue;
        n[0] = atoi(k);
        if( n[0] > 10000 || n[0] < 1) continue;
        else break;
    }
    fout << n[0] << " ";
    while(1)
    {
        GotoXY(0, 2);
        cout << " Numarul N  =                  ";
        GotoXY(14, 2);
        cin >> k;
        if( !IsNumeric(k)) continue;
        n[1] = atoi(k);
        if( n[1] > 100 || n[1] < 3) continue;
        else break;
    }

    if( n[1] < 15)
    {
        Color(1);
        for(int i = 0; i < n[1]; i++)
        {

            GotoXY(i * 6 + 4, 5);
            cout << i + 1;
            GotoXY(1, i + 6);
            cout << i + 1;
        }
        Color(0);
    }

    fout << n[1] << "\n";
    while( i < n[1])
    {
        GotoXY(0, 3);
        cout << " Element    =                  \n                     ";
        GotoXY(14, 3);
        cin >> k;
        if( !IsNumeric(k)) continue;
        m[i][j] = atoi(k);
        if( m[i][j] > 200 || m[i][j] < 0) continue;
        GotoXY(j * 6 + 4, i + 6);
        cout << k;
        fout << m[i][j] << " ";
        j++;
        if( j >= n[1]) { j = 0; i++; fout << "\n";}
    }
    GotoXY(0, i + 6);
    cout << meniu1[5];
    fout.close();
    Wait();
}

void FDIeA()
{
    system("CLS");
    cout << "\n Fisierul de Iesire\n Suma de euro maxima cu care a ramas ( -1 daca nu poate sa treca )\n";
    cout << "__________________\n " << rez;
    cout << meniu1[5];
    Wait();
}

void Informatii()
{
    ifstream fin("info");
    system("CLS");
    char str[256];
    while ( fin.getline( str, 256, '\n'))
        cout << str << "\n";
    fin.close();
    cout << meniu1[5];
    Wait();
}

void Select1(int pos)
{
    switch(pos)
    {
        case 1 :
            {
                FDIA();
                GetMeniu(pos);
            }break;
        case 2 :
            {
                FDIeA();
                GetMeniu(pos);
            }break;
        case 3 :
            {
                Lee();
                FDIE();
                GetMeniu(pos);
            }break;
        case 4 :
            {
                Informatii();
                GetMeniu(pos);
            }break;
        case 5 :
            {
                GotoXY(10, 20);
                exit(0);
            }break;
    }
}

void Meniu()
{
    GetMeniu(1);
    int i, j, key;
    i = 1; j = 0;
    do
    {
        GotoXY(0, 0);
        key = getch();
        switch(key)
        {
            case 80:
                {
                    j = i;
                    if(i+1 > 5) i = 1; else i++;
                    Set(i, j);
                }break;
            case 72:
                {
                    j = i;
                    if(i-1 < 1) i = 5; else i--;
                    Set(i, j);
                }break;
            case 13:
            {
                Select1(i);
            }break;
        }
    }while(key != 27);
    GotoXY(10, 20);
}

// END F I //
//***********************************************************************//

// MAIN FUNCTION //
int main()
{
    Citire();
    Lee();
    Meniu();
    Scrie();
    return 0;
}
// END M F //
