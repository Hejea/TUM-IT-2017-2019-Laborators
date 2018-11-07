function [yx, xy, z] = exemplul_1
    setFigureProprietes;
    syms x;

    %cu x2
    yx = [
        (12 - x)/3
        3*x - 6
        (-3*x)/4
    ];

    %fara x2
    xy = [

    ];

    z = [
        (-x)  
    ];

    x = [-5 10];
    y = [-5 10];
    axis([x y])

    x = [0 0 3 2];
    y = [0 4 3 0];
    fill(x,y,'y');
end