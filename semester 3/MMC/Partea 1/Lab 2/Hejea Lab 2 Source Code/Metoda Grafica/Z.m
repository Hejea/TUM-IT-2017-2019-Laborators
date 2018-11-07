function Z(y)
    syms x;
    if size(y) > 0
        fplot(y(1), [-100 100], '-r', 'Linewidth', 2); hold on;
        fplot(-y(1), [0 100], '--r', 'Linewidth', 2); hold on;
    end
end