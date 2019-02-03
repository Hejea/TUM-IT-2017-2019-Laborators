#include <iostream>
#include <stdio.h>
#include <fstream>
#include <vector>
#include <algorithm>
#include <iomanip>
#include <sstream>
using namespace std;

#define FILE_IN  "files/in.txt"

typedef struct {
    double x;
    double y;
} point_t;

ostringstream sout;
vector<point_t> table;
vector<point_t> points;
vector<double> coefs;

const string aranjat(double d) {
    ostringstream st;
    st << d;

    char buff[100];
    snprintf(buff, 100, "%8s", st.str().c_str());

    string buffAsStdStr = buff;

    return buffAsStdStr;
}

void citire() {
    point_t node, point;
    int i, n, m;
    ifstream fin(FILE_IN);
    fin >> n;
    for(i = 0; i < n; i++)
    {
        fin >> node.x;
        fin >> node.y;
        table.push_back(node);
    }
    i = 0;
    fin >> m;
    for(i = 0; i < m; i++)
    {
        fin >> point.x;
        point.y = 0;
        points.push_back(point);
    }
    fin.close();
}

void showTable(vector<point_t> table) {
    cout << "\n\n x : ";
    for (auto coeff : table) {
        cout << aranjat(coeff.x);
    }
    cout << "\n y : ";
    for (auto coeff : table) {
        cout << aranjat(coeff.y);
    }
    cout << "\n";
}

void showLN() {
    cout << "\n\n Coeficientii : ";
    for (auto coeff : coefs) {
        cout << coeff << "  ";
    }
    cout << "\n\n Ln(x) = ";
    int i = 0;
    for (auto coeff : coefs) {
        if (i != 0 && coeff >= 0) cout << " + ";
        if (coeff < 0) cout << " - ";
        cout << fabs(coeff) << "(x^"<< i << ")";
        i++;
    }
    cout << "\n";
}

vector<double>* CalcCoefLagrange() {
    auto len = table.size();
    auto finalCoef = new vector<double> (len, 0);

    for (auto curpoint : table) {
        vector<double> tmpcoefs (len, 0);
        tmpcoefs[0] = curpoint.y;
        double impartitor = 1;
        for(auto point : table) {
            if (curpoint.x == point.x) continue;
            sout << "prodImp = " << curpoint.x << " - "<< point.x << " = " << curpoint.x - point.x << "\n";
            impartitor *= curpoint.x - point.x;
            double precedent = 0;
            for (auto resptr = tmpcoefs.begin(); resptr < tmpcoefs.end(); resptr++) {
                double newres = (*resptr) * (-point.x) + precedent;
                precedent = *resptr;
                *resptr = newres;
            }
        }
        sout << "impartitor = " << impartitor << "\n\n";
        transform(finalCoef->begin(), finalCoef->end(),
                  tmpcoefs.begin(),
                  finalCoef->begin(),
                  [=] (double oldcoef, double addcoef) {
                        sout << "coefDeAdaugat = " << addcoef;
                        sout << " / " << impartitor;
                        sout << endl;
                        return oldcoef + addcoef/impartitor;
                    }
                  );

        sout << "\ntmpcoef : ";
        for (auto coeff : *finalCoef) {
            sout << coeff << "    ";
        }
        sout << "\n\n\n\n";
    }
    return finalCoef;
}

void solvPoints() {
    int i;
    for (auto iter = points.begin(); iter < points.end(); iter++) {
        i = 0;
        for (auto iter2 = coefs.begin(); iter2 < coefs.end(); i++, iter2++) {
            (*iter).y += (*iter2) * pow((*iter).x, i);
        }
    }
}

int verifica() {
    if (table.size() < 1) return 0;

    auto i = table.begin();
    auto j = table.end();
    auto n = j;

    if (i != j) {
        for (; i < n; i++) {
            for(j = i + 1; j < n; j++){
                if ((*i).x == (*j).x) return 0;
            }
        }
    }

    return 1;
}


int main()
{
    citire();
    cout << "\n Tabelul : nodurilor";
    showTable(table);

    if(verifica()) {
        coefs = *CalcCoefLagrange();
        solvPoints();

        showLN();
        cout << "\n\n Tabelul : punctelor de test";

        showTable(points);
    } else {

        cout << "\n Nodurile nu sunt distincte.\n";
    }

    //cout << sout.str();
    return 0;
}
