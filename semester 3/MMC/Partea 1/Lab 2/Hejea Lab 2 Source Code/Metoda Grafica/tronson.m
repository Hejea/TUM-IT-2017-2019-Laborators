function tronson(yx, xy)

syms x;

yx = [yx; sym('0')]
xy = [xy; sym('0')]

n1 = size(yx);
n1 = n1(1,1);

n2 = size(xy);
n2 = n2(1,1);

r = sym([]);

for i = 1:n1
    eq1 = yx(i);
    for j = (i+1):n1
        eq2 = yx(j);
        r1 = solve(eq1==eq2, x, 'Real', true);
        if size(r1) > 0
            r2 = subs(eq1, x, r1);
            r3 = [r1, r2];
            r = [r; r3];
        end
    end
    for j = 1:n2
        eq2 = xy(j);
        r1 = subs(eq1, x, eq2);
        if size(r1) > 0
            r2 = subs(eq2, x, r1);
            r3 = [r2, r1];
            r = [r; r3];
        end
    end
end

end