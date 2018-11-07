function func2

x = linspace(-10, 20);


y1 = -x + 18;
y2 = (-x)/2 + 6;
y3 = 12 + 0*x ;
y4 = 9 + 0*x;
yx = 0*x;

figure(1);
setFigureProprietes(1)
axis([-3 14 -3 14])

plot(x, y1, '-g', 'Linewidth', 2); hold on;
plot(x, y2, '-r', 'Linewidth', 2); hold on;
plot(y3, x, '-r', 'Linewidth', 2); hold on;
plot(x, y4, '-r', 'Linewidth', 2); hold on;


plot(yx, x, '-k', 'Linewidth', 2); hold on;
plot(x, yx, '-k', 'Linewidth', 2); hold on;

end

