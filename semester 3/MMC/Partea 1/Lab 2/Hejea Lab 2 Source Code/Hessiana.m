function Hessiana
%#ok<*NOPRT>%#ok<*AGROW>%#ok<*NBRAK>
%%%%%%%%%%-Exemple test-%%%%%%%%%%%%%%%%%%%%%%%%
%%%-Ex 3
%     n = 2; 
%     x = sym('x',[1 n]);
%     f = x(1)^3 -12*x(1)*x(2) + 8*x(2)^3;

%%%-Ex 4
%     n = 2; 
%     x = sym('x',[1 n]);
%     f = x(1)^4 + x(1)^2*x(2)^2 - 2*x(1)^2;

%%%%%%%%%%-Pe Acasa-%%%%%%%%%%%%%%%%%%%%%%%%
%%%-Exemplul 9.2.pag.11.
%     n = 1; 
%     x = sym('x',[1 n]);
%     f = -(x(1)^(1/2));

%%%-Exemplul 9.3.pag.11.   
%     n = 2; 
%     x = sym('x',[1 n]);
%     f = 2*x(1)^2 + x(2)^2 - 2*x(1)*x(2);

%%%-Exemplul 9.4.pag.11.
    n = 3; 
    x = sym('x',[1 n]);
    f = x(1)^4 + 2*x(2)^2 + 3*x(3)^2 - 4*x(1) - 4*x(2)*x(3);
     
    Diferentiala_Ord_1 = diff(f, x(1));
    for i = 2:n
        Diferentiala_Ord_1 = [Diferentiala_Ord_1, diff(f, x(i))];
    end
    Diferentiala_Ord_1 = Diferentiala_Ord_1.';
    Hessian = hessian(f,x);
    Det = det(Hessian);
    
    r = solve(Diferentiala_Ord_1, x, 'Real', true);
    rs = size(r);
    if rs > 0 
        r = struct2cell(r);
        R = sym('q',[1 rs]);

        R = cell2sym(r(1));
        for i = 2:n
           R = [R, cell2sym(r(i))];
        end
        
        nr = size(R);
        nr = nr(1,1);
        p = zeros( nr , 1 );

        for i = 1:nr
            s = Det;
            for j = 1:n
                s = subs(s, x(j), R(i,j));
            end
            p(i) = s;
        end

        T = repmat({''}, nr, 1);
        for i = 1:nr
            q = [ '(' ]; 
            for j = 1:n
                q = [q, char(R(i,j))]; 
                if j < n
                    q = [q, ', '];
                end
            end
            q = [q, ') = ', num2str(p(i)), ' -> ' ];
            if p(i) <= 0        
                q = [q '(Concava) => Punct de Maxim' ];
            else
                q = [q '(Convexa) => Punct de Minim' ];
            end
            T(i) = {q};
        end
    end

%%%%-Afisarea-%%%%%%%%%%%%
    f 
    
    Diferentiala_Ord_1
    
    if rs > 0
        R
    end

    Hessian
    
    Det
    
    if rs > 0
        T
    end
end