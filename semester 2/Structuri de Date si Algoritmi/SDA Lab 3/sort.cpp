#include <stdio.h>
#include <conio.h>
#include <stdlib.h>
#include <string.h>
#include <iostream>
#include <conio.h>
using namespace std;
struct st_ex {
    char product[16];
    float price;
};
/* qsort C-string comparison function */
void print_struct_array(struct st_ex *array, size_t len)
{
    size_t i;

    for(i=0; i<len; i++)
        printf("[ product: %s \t price: $%.2f ]\n", array[i].product, array[i].price);

    puts("--");
}
int struct_cmp_by_product(const void *a, const void *b)
{
    struct st_ex *ia = (struct st_ex *)a;
    struct st_ex *ib = (struct st_ex *)b;
    return strcmp(ia->product, ib->product);
	/* strcmp functions works exactly as expected from
	comparison function */
}

void sort_structs_example(void)
{
    struct st_ex structs[] = {{"aaa", 299.0f}, {"plasma tv", 2200.0f},
                              {"otebook", 1300.0f}, {"bbb", 499.99f},
                              {"dvd player", 150.0f}, {"matches", 0.2f }};
    size_t structs_len = sizeof(structs) / sizeof(struct st_ex);

    print_struct_array(structs, structs_len);

    qsort(structs, structs_len, sizeof(struct st_ex), struct_cmp_by_product);

    print_struct_array(structs, structs_len);
}
int main ()
{
    sort_structs_example();
    return 0;
}
