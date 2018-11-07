function [] = tabelulSimplex

    listSimplex = sym('s', [0 0 0]);


% %%%% Exemplul 1
%     re = 1; 
%     M = sym('m', [1 re]);
%     
%     rx = 7;
%     x = sym('x', [1 rx]);
% 
% %%% | C | Baza | x1 -> xn | b |
%               % | C | Baza |          x1 -> xn          |  b |
%     tableSimplex = [
%               {     0,  x(3),   4,  1,  1,  0,  0,  0,  0,  45}
%               {     0,  x(4),   1,  4,  0,  1,  0,  0,  0,  30}
%               {     0,  x(5),   3,  2,  0,  0,  1,  0,  0,  42}
%               { -M(1),  x(7),   0,  1,  0,  0,  0, -1,  1,   6}
%     ];
% 
%     
% %%% p(i) | b = 0 |
%     pret = [
%         3
%         2
%         0
%         0
%         0
%         0
%         -M(1)
%         0
%     ];

% %%% Exemplul 2
%     re = 0; 
%     M = sym('m', [1 re]);
%     
%     rx = 5;
%     x = sym('x', [1 rx]);
% %%% | C | Baza | x1 -> xn | b |
%               % | C | Baza |          x1 -> xn          |  b |
%     tableSimplex = [
%               {     0,  x(3),   4,  1,  1,  0,  0,  45}
%               {     0,  x(4),   1,  4,  0,  1,  0,  30}
%               {     0,  x(5),   3,  2,  0,  0,  1,  42}
%     ];
% 
%     
% %%% p(i) | b = 0 |
%     pret = [
%         3
%         2
%         0
%         0
%         0
%         0
%     ];

% %%% Exemplul 3
%     re = 0; 
%     M = sym('m', [1 re]);
%     
%     rx = 5;
%     x = sym('x', [1 rx]);
% %%% | C | Baza | x1 -> xn | b |
%               % | C | Baza |          x1 -> xn          |  b |
%     tableSimplex = [
%               {     0,  x(3),   1,  3,  1,  0,  0,  12}
%               {     0,  x(4),   1,  0,  0,  1,  0,  30}
%               {     0,  x(5),   0,  1,  0,  0,  1,  3}
%     ];
% 
%     
% %%% p(i) | b = 0 |
%     pret = [
%         3
%         2
%         0
%         0
%         0
%         0
%     ];

% %%% Exemplul 4
%     re = 0; 
%     M = sym('m', [1 re]);
%     
%     rx = 5;
%     x = sym('x', [1 rx]);
% %%% | C | Baza | x1 -> xn | b |
%               % | C | Baza |          x1 -> xn          |  b |
%     tableSimplex = [
%               {     0,  x(3),   1,  2,  1,  0,  0,  70}
%               {     0,  x(4),   1,  0,  0,  1,  0,  30}
%               {     0,  x(5),   0,  1,  0,  0,  1,  25}
%     ];
% 
%     
% %%% p(i) | b = 0 |
%     pret = [
%         2
%         3
%         0
%         0
%         0
%         0
%     ];

%%% Exemplul 5
    re = 1; 
    M = sym('m', [1 re]);
    
    rx = 7;
    x = sym('x', [1 rx]);
%%% | C | Baza | x1 -> xn | b |
              % | C | Baza |          x1 -> xn          |  b |
    tableSimplex = [
              {     0,  x(3),   1,  2,  1,  0,  0,   0,   0,  70}
              {     0,  x(4),   1,  0,  0,  1,  0,   0,   0,  26}
              {     0,  x(5),   0,  1,  0,  0,  1,   0,   0,  25}
              { -M(1),  x(7),   0,  1,  0,  0,  0,  -1,   1,  18}
    ];

    
%%% p(i) | b = 0 |
    pret = [
        2
        3
        0
        0
        0
        0
        -M(1)
        0
    ];


    tableSimplex = cell2sym(tableSimplex);
    listSimplex(:,:,1) = tableSimplex;
    n = size(pret, 1);
    m = size(tableSimplex, 1);
    listiIndex = zeros([0 0]);
    pas = 1;
   
    D = delta(tableSimplex, pret);
    
    Simplex = tableSimplex
    Delta = D
    
    while isOptim(D) < 1
        q = listSimplex(:, :, pas);
        mins = zeros([0 0]);
        jIndex = Min(D);
        for i = 1:m
            t = 1;
            for j = 1:size(listiIndex, 2)
                if i == listiIndex(j)
                    t = 0;
                end
            end
            if t == 1
                s1 = q(i, n+2);
                s2 = q(i, jIndex+2);
                [s1 s2];
                s = s1/s2;
                mins = [mins, s];
            end
        end
   
        e = min(mins);
        iIndex = 0;
        
        for i = 1:m
            t = 1;
            for j = 1:size(listiIndex, 2)
                if i == listiIndex(j)
                    t = 0;
                end
            end
            if t == 1
                s1 = q(i, n+2);
                s2 = q(i, jIndex+2);
                s = s1/s2;
                if s == e
                    iIndex = i;
                end
            end
        end
        
        listiIndex = [listiIndex, iIndex];
        
        q = subs(q, q(iIndex, 2), x(jIndex));
        q(iIndex, 1) = pret(jIndex);


        
        index = q(iIndex, jIndex+2);
        


        for i = 1:m
            for j = 1:n
                if iIndex ~= i && jIndex ~= j
                    s1 = index * q(i, j+2);
                    s2 = q(i, jIndex+2) * q(iIndex, j+2);
                    [s1 s2];
                    e = (s1 - s2)/index;
                    q(i, j+2) = e;
                end
            end
        end
        
        for i = 1:jIndex-1
            q(iIndex, i+2) = q(iIndex, i+2)/index;
        end
        for i = jIndex+1:n
            q(iIndex, i+2) = q(iIndex, i+2)/index;
        end
        
        for j = 1:m
            if j ~= iIndex
                q(j, jIndex+2) = 0;
            end
        end
        q(iIndex, jIndex+2) = 1;
     
        D = delta(q, pret);
       
        pas = pas + 1;
        
        listSimplex(:, :, pas) = q;
        
        Simplex = q
        Delta = D
    end

    
    
    
end









