function [yx, xy, z] = exemplul_2
    setFigureProprietes;
    syms x;

    %cu x2
    yx = [
         (4 - x)/2
         (-1 - x)/2
    ];

    %fara x2
    xy = [
        3
    ];

    z = [
        x/4  
    ];

    x = [-5 10];
    y = [-5 10];
    axis([x y])

    x = [0 0 3 3];
    y = [0 2 1/2 0];
    fill(x,y,'y');
end