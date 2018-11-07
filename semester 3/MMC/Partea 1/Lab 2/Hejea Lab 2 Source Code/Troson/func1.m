function func1

x1 = linspace(1.5, 3.5);
x2 = linspace(-1, 4);
x3 = linspace(-1, 6);
x4 = linspace(-1, 3);

y1 = 2*x1 - 4;
y2 = -x2 + 5;
y3 = 0*x3;
y4 = 0*x4;

figure(1);
setFigureProprietes(1)
axis([-2 5 -2 7])

plot(x1, y1, '-r', 'Linewidth', 2); hold on;
plot(x2, y2, '-r', 'Linewidth', 2); hold on;
plot(y3, x3, '-b', 'Linewidth', 2); hold on;
plot(x4, y4, '-b', 'Linewidth', 2); hold on;

end

