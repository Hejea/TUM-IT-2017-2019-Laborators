function cond_nenegativ
    syms x;
    int = [0 100];
    oxy = 0*x;

    fplot(oxy, int, '-k', 'Linewidth', 2); hold on;
    fplot(oxy, x, int, '-k', 'Linewidth', 2); hold on;
end