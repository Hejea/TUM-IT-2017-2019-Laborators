    /**
      * Laborator SDA
      *
      * Conditia : SOR - Metoda relaxarii (Succesive Over-Relaxation).
      *
      *@author Nicolae Heghea TI-173
      */

    #include <stdio.h>
    #include <stdlib.h>
    #include <string.h>
//---------------------------------------------------
//---------------------------------------------------
    // definiri tipuri si constante
    #define INPUT_FILE "files\\input1.txt"
    #define OUTPUT_FILE "files\\output1.txt"

    typedef float Cell;
    typedef Cell *Vector;
    typedef Vector *Matrix;

    const int nMaxIeration = 10;
    const float EPS = 0.001;
    const Cell w = 1.2;
    int nSizeOfMatrix;

//---------------------------------------------------
//---------------------------------------------------

    Matrix createMatrix(int nRow, int nCol);
    Vector createVector(int nSize);
    void freeMemMatrix(Matrix matrix);
    void showMatrix(Matrix matrix);
    Matrix readFromFile();
    void writeToFile(Vector vector);
    Cell multiplyVectorMatrixVector(Matrix a, Vector x);
    int Pozitiv_Definita(Matrix matrix);
    Vector SOR(Matrix matrix);
    int condtionErrorEPS(Vector x, Vector y);

//---------------------------------------------------
//---------------------------------------------------

    int main() {
        Matrix A = NULL;
        Vector X = NULL;

        A = readFromFile();
        showMatrix(A);

        if(Pozitiv_Definita(A)){
            printf("\n\n\n  Matricea acceptata");
            printf("\n  Matricea e pozitiv definita\n");
        } else {
            printf("\n\n\n  Matricea ne acceptata");
            printf("\n  Matricea nu e pozitiv definita\n");
        }

        X = SOR(A);

        writeToFile(X);

        freeMemMatrix(A);
        free(X);
        printf("\n");
        return 0;
    }

//---------------------------------------------------
//---------------------------------------------------

    Matrix createMatrix(int nRow, int nCol){
        Matrix newMatrix = NULL;
        int i;

        newMatrix = malloc(nRow * sizeof(Vector));
        if(newMatrix != NULL){
            for(i = 0; i < nRow; i++){
                *(newMatrix + i) = calloc(nCol , sizeof(Cell));
                //if(*(newMatrix + i) == NULL) return NULL;
            }
        }

        return newMatrix;
    }

    Vector createVector(int nSize){
        Vector newVector = NULL;

        newVector = malloc(nSize * sizeof(Cell));

        return newVector;
    }

    void freeMemMatrix(Matrix matrix){
        int i;
        for(i = 0; i < nSizeOfMatrix; i++){
            free(*(matrix + i));
        }
        free(matrix);
    }

    void showMatrix(Matrix matrix){
        int i, j;
        printf("\n  Matricea A[NxN]\n\n");
        for(i = 1; i <= nSizeOfMatrix; i++){
            printf("%9s%d", "x", i);
        }
        printf("%9s", "B");
        for(i = 0; i < nSizeOfMatrix; i++){
            printf("\n%3s", "");
            for(j = 0; j <= nSizeOfMatrix; j++){
                printf("%10.3f", *(*(matrix + i) + j) );
            }
        }
    }

    Matrix readFromFile(Matrix matrix) {
        FILE *file;
        int i, j;

        if((file = fopen(INPUT_FILE, "r")) == NULL) {
            printf("\n Eroare la desciderea Fisierului !");
        } else {
            fscanf(file, "%d", &nSizeOfMatrix);

            matrix = createMatrix(nSizeOfMatrix, nSizeOfMatrix + 1);

            if(matrix != NULL) {
                for(i = 0; i < nSizeOfMatrix; i++) {
                    for(j = 0; j < nSizeOfMatrix + 1; j++){
                        fscanf(file, "%f", *(matrix + i) + j );
                    }
                }
            }
            fclose(file);
        }
        return matrix;
    }

    Cell multiplyVectorMatrixVector(Matrix a, Vector x) {
        Cell s = 0.;
        int i, j;

        for(i = 0; i < nSizeOfMatrix; ++i) {
            s += a[i][i] * x[i] * x[i];
            for(j = i + 1; j < nSizeOfMatrix; ++j) {
                    s += 2 * a[i][j] * x[i] * x[j];
            }
        }
        return s;
    }

    int Pozitiv_Definita(Matrix matrix) {
        Cell val;
        int i, j;
        Vector x;

        // Pozitiv + ne nula pe diagonala
        for(i = 0; i < nSizeOfMatrix; ++i) {
            val = matrix[i][i];
            if (val <= 0) {
                return 0;
            }
        }

        // Simetrie";
        for(i = 0; i < nSizeOfMatrix; ++i)
            for(j = i + 1; j < nSizeOfMatrix; ++j)
               if(matrix[i][j] != matrix[j][i])
                    return 0;

        // un Vector aliator ne nul
        x = createVector(nSizeOfMatrix);
        for(i = 0; i < nSizeOfMatrix; ++i) x[i] = i;

        Cell ok = multiplyVectorMatrixVector(matrix, x);

        if(ok < 0.) {
            return 0;
        }

        return 1;
    }

    Vector SOR(Matrix matrix) {
        if(!Pozitiv_Definita(matrix)) return;

        Vector X = NULL, Y = NULL;
        int i, j, k;

        X = createVector(nSizeOfMatrix);
        Y = createVector(nSizeOfMatrix);

        for(i = 0; i < nSizeOfMatrix; i++) {
            Y[i] = X[i] = matrix[i][nSizeOfMatrix];
        }

        for(k = 0; k < nMaxIeration; k++) {

            for(i = 0; i < nSizeOfMatrix; i++) {
                Y[i] = matrix[i][nSizeOfMatrix];
                for(j = 0; j < i; j++) {
                    Y[i] = Y[i] - matrix[i][j] * Y[j];
                }
                for(j = i + 1; j <= nSizeOfMatrix; ++j) {
                    Y[i] = Y[i] - matrix[i][j] * X[j];
                }
                Y[i] = (1.0 - w)*X[i] + (w * Y[i]) / matrix[i][i];
            }
            printf("\n\n iteratia - %d\n", k+1);
            for(i = 0; i < nSizeOfMatrix; i++) {
                printf("%10.3f", X[i]);
            }

            if(!condtionErrorEPS(X, Y)) break;

            for(i = 0; i < nSizeOfMatrix; i++) {
                X[i] = Y[i];
            }
        }
        free(Y);
        return X;
    }

    int condtionErrorEPS(Vector x, Vector y) {
        Cell max = fabs(y[1] - x[1]), aux;
        int i;
        for(i = 1; i < nSizeOfMatrix; ++i) {
            aux = fabs(y[i] - x[i]);
            if(max < aux) max = aux;
        }
        if(max < EPS) return 0;
        return 1;
    }

    void writeToFile(Vector vector){
        FILE *file;
        int i, j;

        if((file = fopen(OUTPUT_FILE, "w")) == NULL) {
            printf("\n Eroare la desciderea Fisierului !");
        } else {
            for(i = 0; i < nSizeOfMatrix; i++){
                fprintf(file, "x%d = %10.3f\n", i+1, vector[i]);
            }
            fclose(file);
        }
    }
