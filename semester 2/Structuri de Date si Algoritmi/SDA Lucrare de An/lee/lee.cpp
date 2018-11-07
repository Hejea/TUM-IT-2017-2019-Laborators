#include <stdio.h>

int map[40][40];
int steps[41][2];
int dy[]={1,-1,0,0},
    dx[]={0,0,1,-1};
int i,j;

int Posibil(int x, int y)
{
    if( (x < 0) || (x > 40 - 1) ||
        (y < 0) || (y > 40 - 1) ) return 0;
    return 1;
}

//Lee function
int lee()
{
    int x = 0, y = 0, k = 0, pos = 0, cx, cy;
    map[x][y] = 1;
    while(1)
    {
        for(i = 0; i < 4; i++)
        {
            cx = x + dx[i];
            cy = y + dy[i];
            if( Posibil(cx, cy) && map[cx][cy] == 0)
            {
                k = k % 40;
                steps[k][0] = cx;
                steps[k++][1] = cy;
                map[cx][cy] = map[x][y] + 1;
            }
        }
        pos = pos % 40;
        if( steps[pos][0] != -1)
        {
            x = steps[pos][0];
            y = steps[pos][1];
            steps[pos++][0] = -1;
        }
        else break;
    }
    return 0;
}
//****************************************************

int main()
{
    lee();
    FILE *g=fopen("lee.out","w");
    for(i=0;i<40;i++)
    {
        for(j=0;j<40;j++)
            fprintf(g,"%d ",map[i][j]);
        fprintf(g,"\n");
    }
    fclose(g);
    return 0;
}
