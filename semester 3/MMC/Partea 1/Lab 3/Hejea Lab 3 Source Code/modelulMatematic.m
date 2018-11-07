function [f, re, b, z] = modelulMatematic

    n = 7; 
    x = sym('x',[1 n]);

%%% Functiile
    f = [
        4*x(1) + x(2)
        x(1) + 4*x(2)
        3*x(1) + 2*x(2)
    ];

%%% Restrictiile
    re = [
        x(2)
    ];

%%% b (partea dreapta)
    b = [
        45
        30
        42
        6
    ];
 
%%% Z = Functia scop
    z = [
        3*x(1) + 2*x(2)
    ];

end