#include <fstream>
#include <iostream>
#define InFile "taxe1.in"
#define OutFile "taxe1.out"
using namespace std;

int y[10000][2];
int sol = 0, taxa = 0, t = 0;
int dx[4] = {0,0,1,-1};
int dy[4] = {1,-1,0,0};
int n[2] = {0};
int m[201][201];
int ok = 0;

int posibil(int k)
{
    if( (y[k][0] < 0) || (y[k][0] > n[1] - 1) ||
        (y[k][1] < 0) || (y[k][1] > n[1] - 1) ) return 0;
    for(int l = 0; l < k - 1; l++)
        if( (y[k][0] == y[l][0]) && (y[k][1] == y[l][1]) ) return 0;
    return 1;
}

void taxaf(int c)
{
    sol++;
    if(sol == 1) t = c;
    else if( c < t ) t = c;
}

int DrumP(int k)
{
    taxa += m[y[k-1][0]][y[k-1][1]];
    if( taxa > n[0] ){ taxa -= m[y[k-1][0]][y[k-1][1]]; return 0;}
    for(int l = 0; l < 4; l++)
    {
        y[k][0] = y[k-1][0] + dx[l];
        y[k][1] = y[k-1][1] + dy[l];
        if(posibil(k))
        {
            if((y[k][0] == n[1] - 1) && (y[k][1] == n[1] - 1))
            {
                if( taxa > n[0] ){ break;}
                taxa += m[y[k][0]][y[k][1]];
                taxaf(taxa);
                taxa -= m[y[k][0]][y[k][1]];
                ok = 1;
            }
            else {  DrumP(k+1); }
        }
    }
    taxa -= m[y[k-1][0]][y[k-1][1]];
    return 1;
}

int main()
{
    int i = 0, j = 0;
    ifstream fin(InFile);
    ofstream fout(OutFile);
    while(i < 2){ fin >> n[i++]; }
    i = 0;
    while(fin >> m[i][j])
    {
        j++;
        if(j >= n[1])
        {
            j = 0;
            i++;
        }
    }
    y[0][0] = 0;
    y[0][1] = 0;
    DrumP(1);
    fin.close();
    t = n[0] - t;
    fout << (( t >= 0 && ok)? t : -1) << "\n";
    fout.close();
    return 0;
}
