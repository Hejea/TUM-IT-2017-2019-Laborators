function [] = hesiana
syms x  y;

%f = x^3 - 12*x*y + 8*y^3;
f = x^4 + x^2*y^2 - 2*x^2;

diff_1_x = diff(f, x);
diff_1_y = diff(f, y);

R = solve([diff_1_x == 0, diff_1_y == 0], [x, y], 'Real', true);

diff_2_xx = diff(diff_1_x, x);
diff_2_yy = diff(diff_1_y, y);
diff_2_xy = diff(diff_1_x, y);

%H = [diff_2_xx diff_2_xy; diff_2_xy diff_2_yy];

H = hessian(f,[x,y]);

d = det(H);

n = min( size(R.x), size(R.y));
n = n(1,1);
p = zeros( n );

for i = 1:n
    s = subs(d, R.x(i,1));
    p(i) = subs(s, R.y(i,1));
end

 
T = {['F(x,y) = ', char(f), '                                                          ']; 
     ['F->x   = ', char(diff_1_x) '                                                    '];
     ['F->y   = ', char(diff_1_y) '                                                    '];
     ['F->xx  = ', char(diff_2_xx) '                                                     '];
     ['F->yy  = ', char(diff_2_yy) '                                                       '];
     ['F->xy  = ', char(diff_2_xy) '                                                        '];
     }

Hesiana = H
Det = [char(d)]


T = repmat({''}, n, 1);
for i = 1:n 

    q = [ '(', char(R.x(i,1)), ', ', char(R.y(i,1)), ') = ', num2str(p(i)), ' -> ' ];
    if p(i) <= 0        
        q = [q '(Concava) => Punct de Maxim' ];
    else
        q = [q '(Convexa) => Punct de Minim' ];
    end
    T(i) = {q};
end

T

end














