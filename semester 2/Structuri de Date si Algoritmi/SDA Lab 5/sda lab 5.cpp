/*
13.	Scrieti un program de convertire a fiecarui
element dintr-o stiva intr-un sir de caractere,
salvandu-le in una noua si sa se sorteze.
*/
#include <stdio.h>
#include <stdlib.h>
#include <conio.h>
#include <iostream>
using namespace std;

// DEFINE ITEM  //
#define tip int

// END D I //
//***********************************************************************//
// STRUCTURE ITEM //
struct MyStiva
{
    char date;
    MyStiva *next, *ultim;
};

// END S I //
//***********************************************************************//
// VARIABLE ITEM //

// END V I //
//***********************************************************************//
// FUNCTION ITEM //
MyStiva* Creare()
{
    return NULL;
}

void AdVirf( MyStiva *&stiva, char date)
{
    MyStiva *NodNou;
    NodNou = new (MyStiva);             // se creează ultimul nod
    NodNou->date = date;
    NodNou->next = NULL;
    if( stiva == NULL )                 // daca stiva e vida atunci
    {                                   //
        stiva = NodNou;                 // noul nod este insasi stiva
        stiva->ultim = NodNou;          // si ultimul nod al stivei
    }
    else                                // daca nu e vida
    {
        stiva->ultim->next = NodNou;    // plasam noul nod la urma
        stiva->ultim = NodNou;          // marcam noul nod ca fiind ultim
    }
}

void CitireChar(MyStiva *&stiva)
{
    char c;
    cout << " Tipareste char :\n ";
    while(1)
    {
        c = getch();
        if((int)c == 13) break;                 // atâta timp cât nu introducem ENTER
        else
        if( (( c >= 'A' ) && ( c <= 'Z' )) ||   // putem introduce doar asa tip de char
            (( c >= 'a' ) && ( c <= 'z' )) ||
            (( c >= '0' ) && ( c <= '9' )))
        {
            cout << c;
            AdVirf(stiva, c);                   // introducel in stiva
        }
    }
}

char* StivaToChar(MyStiva *stiva)
{
    char *c;
    c = new char[256];
    int i = 0;
    while(stiva) 		                    //
    {
        c[i] = stiva->date;                 //
        stiva = stiva->next; 		        //
        i++;
    }
    c[i] = '\0';
    return c;
}

MyStiva* CharToStiva(char *sir)
{
    MyStiva *newS;
    newS = Creare();
    int i = 0;
    while(sir[i]) 		                    //
    {
        AdVirf(newS, sir[i]);
        i++;
    }
    return newS;
}

void AfisStiva(MyStiva *stiva)
{
    while(stiva) 		                    // atâta timp cât stiva nui vida
    {
        cout << stiva->date; 	            // se afişează nodul curent
        stiva = stiva->next; 		        // se trece la următorul nod
    }
}

void StergeStiva (MyStiva *stiva)
{
    MyStiva *Snod;
    while(stiva)                        // cit timp stiva nui vida
   	{
        Snod = stiva;           	    // marcam virful stivei pentru al sterge
  		stiva = stiva->next;    	    // urmatorul nod devine virful stivei
   		free( Snod );                   // se sterge virful stivei
   	}
}

MyStiva* sort(MyStiva *stiva)
{
    MyStiva *virf, *pre, *newS;
    newS = Creare();                    // cream o stiva noua
    while(stiva)                        //
    {                                   // fac o copie a stivei
        AdVirf(newS, stiva->date);      //
        stiva = stiva->next;            //
    }                                   //
    char c;
    virf = newS;
    while(newS && newS->next)           // cit timp stiva are cel putin 2 elem   vom executa
    {
        pre = virf;
        while(pre != newS->next)        // de la primul element al stivei pina la elementul curent
        {
            if(pre->date > newS->next->date)    // daca elementul predecesor este mai mare ca elementul succesor
            {
                c = pre->date;                  // se schimba cu locul
                pre->date = newS->next->date;   //
                newS->next->date = c;           //
            }
            pre = pre->next;                    //
        }
        newS = newS->next;                      // trecem la elementul urmator
    }
    return virf;
}

// END F I //
//***********************************************************************//
// MAIN FUNCTION //
int main()
{
    MyStiva *stiva, *OrdStiva, *DinChar;
    char *sir;

    stiva = Creare();               // cream 3 stive neordonata
    OrdStiva = Creare();            //
    DinChar = Creare();

    CitireChar( stiva );            // citim charactere si le introducem in stiva

    cout << endl;

    cout << "\n Lista ordonata este :\n ";
    OrdStiva = sort(stiva);         // ordonam stiva ctita si o salvam in alta stiva
    AfisStiva (OrdStiva);           // afisam stiva ordonata
    cout << endl;

    cout << "\n Lista neordonata este :\n ";
    AfisStiva (stiva);              // afisam stiva neordonata
    cout << endl;

    cout << "\n Sirul de char este :\n ";
    sir = StivaToChar(stiva);
    cout << sir;
    cout << endl;

    cout << "\n Din sir in stiva :\n ";
    DinChar = CharToStiva(sir);
    AfisStiva(DinChar);

    StergeStiva (stiva);            // stergem ambele stive
    StergeStiva (OrdStiva);         // <-'
    cout << endl << endl;
	system ("pause");
	return 0;
}
// END M F //
