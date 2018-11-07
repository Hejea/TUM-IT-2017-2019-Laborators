#include <stdio.h>
#include <conio.h>
#include <alloc.h>
#include <stdlib.h>
typedef struct tip_nod{
			       int cheie;/*informatie */
		   	       struct tip_nod *stg,*dr;
			     } TIP_NOD;
TIP_NOD *rad;
void preordine(TIP_NOD *p, int nivel)
{
  int i;
  if (p!=0){
		 for(i=0;i<=nivel;i++) printf("  ");
		 printf("%2d\n",p->cheie);
		 preordine(p->stg,nivel+1);
		 preordine(p->dr,nivel+1);
	       }
}
void inordine(TIP_NOD *p, int nivel)
{
  int i;
  if (p!=0){
		 inordine(p->stg,nivel+1);
		 for(i=0;i<=nivel;i++) printf("  ");
		 printf("%2d\n",p->cheie);
		 inordine(p->dr,nivel+1);
	       }
}
void postordine(TIP_NOD *p, int nivel)
{
  int i;
  if (p!=0){
		 postordine(p->stg,nivel+1);
		 postordine(p->dr,nivel+1);
		 for(i=0;i<=nivel;i++) printf("  ");
		 printf("%2d\n",p->cheie);
	       }
}
void inserare(int key)
{
  TIP_NOD *p,*q;
  int n;
  n=sizeof(TIP_NOD);
  p=(TIP_NOD *)malloc(n);
  p->cheie=key;
  p->stg=0;p->dr=0;
  if(rad==0){
		rad=p;
		return;
	          }
  q=rad;
  for(;;)
	 {
		if (key < q->cheie){
					if(q->stg==0){
					                          q->stg=p;
					                           return;
				                                   }
		                                     else q=q->stg;
				        }
		else if (key > q->cheie) {
					       if(q->dr == 0) {
								q->dr=p;
								return;
							        }
					        else q=q->dr;
					    }
		         else {   /* chei   egale */
			         printf("\n Exista un nod de cheie = %d\n",key);
					  /* eventuala prelucrare a nodului */
			         free(p);
			         return;
			      }
 }
            }
TIP_NOD *inserare_rec(TIP_NOD *rad,int key)
{
  TIP_NOD *p;
  int n;
  if (rad==0){
		  n=sizeof(TIP_NOD);
		  p=(TIP_NOD *)malloc(n);
		  p->cheie=key;p->stg=0;p->dr=0;
		  return p;
	            }
  else {
             if(key < rad->cheie) rad->stg=inserare_rec(rad->stg,key);
	    else {
                       if(key > rad->cheie) rad->dr=inserare_rec(rad->dr,key);
		  else { /* cheie dubla */
			 printf("\n Exista un nod de cheie=%d\n",key);
		          }
	            }
	 };
    return rad;
}

TIP_NOD * cautare(TIP_NOD *rad, int key)
{
	TIP_NOD *p;

	if(rad==0) return 0;/*arborele este vid */
	p=rad;
	while(p != 0)
		{
		  if(p->cheie == key) return p;/* s-a gasit nodul */
		  else if(key < p->cheie) p=p->stg;
		         else p=p->dr;
		 }
	return 0; /* nu exista nod de cheie key */
   }
TIP_NOD *stergere_nod(TIP_NOD *rad,int key)
{
TIP_NOD *p,*tata_p;/* p este nodul de sters, iar tata_p este tatal lui */
TIP_NOD *nod_inlocuire,*tata_nod_inlocuire;/*nodul care il va inlocui pe p si tatal sau */
  int directie; /*stg=1;dr=2*/
  if(rad==0) return 0; /*arborele este vid */
  p=rad; tata_p=0;
  /* cautare nod cu cheia key */
  while((p!=0)&&(p->cheie!=key))
	  {
		 if (key<p->cheie){     /*cautare in stanga */
					 tata_p=p;
					  p=p->stg;
					  directie=1;
		}
		   else {    /*cautare in dreapta */
			    tata_p=p;
			    p=p->dr;
			    directie=2;
			}
	     }
     if(p==0){
		  printf("\n NU EXISTA NOD CU CHEIA=%d\n",key);
		  return rad;
	          }
      /* s-a gasit nodul p de cheie key */
      if(p->stg==0) nod_inlocuire=p->dr; /* nodul de sters p nu are fiu sting */
else if(p->dr==0) nod_inlocuire=p->stg;	/*nodul de sters p  nu are fiu drept*/
	    else {  /* nodul de sters p are fiu stang si fiu drept */
		   tata_nod_inlocuire=p;
		   nod_inlocuire=p->dr;        /* se cauta in subarborele drept*/
		   while(nod_inlocuire->stg!=0)
		        {
			tata_nod_inlocuire=nod_inlocuire;
			nod_inlocuire=nod_inlocuire->stg;
		        }
		    if(tata_nod_inlocuire!=p)
			{
			    tata_nod_inlocuire->stg=nod_inlocuire->dr;
			    nod_inlocuire->dr=p->dr;
			 }
		     nod_inlocuire->stg=p->stg;
		  }
        free(p);
        printf("\nNodul de cheie=%d a fost sters!\n",key);
       if(tata_p==0)  return nod_inlocuire; /*s-a sters chiar radacina initiala */
       else {
	        if (directie==1) tata_p->stg=nod_inlocuire;
	        else tata_p->dr=nod_inlocuire;
	        return rad;
               }
}
void stergere_arbore(TIP_NOD *rad)
{
  if(rad!=0) {
		     stergere_arbore(rad->stg);
	                 stergere_arbore(rad->dr);
		     free(rad);
	          }
}
int main(void)
{
  TIP_NOD *p;
  int i, n,key;
  char ch;
  printf("ALEGETI Inserare recursiva r/R sau nerecursiva alt caracter");
  scanf("%c",&ch);
  printf("\nNumarul total de noduri=");
  scanf("%d",&n);
  rad=0;
  for(i=1;i<=n;i++)
        {
	            printf("\nCheia nodului=");
	            scanf("%d",&key);
	            if((ch=='R')||(ch=='r')) rad=inserare_rec(rad,key);
	            else inserare(key);
         }
  printf("\nVIZITAREA IN PREORDINE\n");
  preordine(rad,0);
  getch();
  printf("\nVIZITAREA IN INORDINE\n");
  inordine(rad,0);
  getch();
  printf("VIZITAREA IN POSTORDINE\n");
  postordine(rad,0);
  getch();
  fflush(stdin);
  printf("\n Doriti sa cautati un nod DA=D/d Nu= alt caracter :");
  scanf("%c",&ch);
  while((ch=='D')||(ch=='d'))
                             {
	                         printf("Cheia nodului cautat=");
	                         scanf("%d",&key);
	                         p=cautare(rad,key);
	                        if(p!=0) printf("Nodul exista si are adresa p\n");
	                        else printf("Nu exista un nod de cheie data\n");
	                        fflush(stdin);
	                        printf("\n Doriti sa cautati un nod DA=D/d Nu= alt caracter : ");
	                        scanf("%c",&ch);
                             }
  fflush(stdin);
  printf("\n Doriti sa sterget un nod DA=D/d Nu= alt caracter :");
  scanf("%c",&ch);
  while((ch=='D')||(ch=='d'))
                            {
	                       printf("Cheia nodului de sters=");
	                       scanf("%d",&key);
	                       rad=stergere_nod(rad,key);
	                       inordine(rad,0);
	                       fflush(stdin);
	                       printf("\n Doriti sa stergeti un nod DA=D/d Nu= alt caracter : ");
	                       scanf("%c",&ch);
                           }
 printf("stergeti arborele creat ? da=d/D nu=alt caracter  ");
 fflush(stdin);
 scanf("%c",&ch);
 if((ch=='D')||(ch=='d')) {
				 stergere_arbore(rad);
				 rad=0;
				 printf("\nARBORELE ESTE STERS!!\n");
			         }
 getch();
}
