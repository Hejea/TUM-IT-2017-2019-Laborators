function [yx, xy, z] = exemplul_0
setFigureProprietes;
syms x;

%cu x2
yx = [
    -x + 18
    (-x)/2 + 6
    9 + 0*x
];

%fara x2
xy = [
    12 + 0*x   
];

z = [];

x = [0 0 12];
y = [0 6 0];
fill(x,y,'y');

x = [-5 15];
y = [-5 15];
axis([x y])

end