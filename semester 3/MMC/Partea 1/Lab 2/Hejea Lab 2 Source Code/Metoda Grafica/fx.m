function fx(y)
    int = [-100 100];
    n = size(y);
    n = n(1,1);

    for i = 1:n
        fplot(y(i), int, '-b', 'Linewidth', 2); hold on;
    end
end