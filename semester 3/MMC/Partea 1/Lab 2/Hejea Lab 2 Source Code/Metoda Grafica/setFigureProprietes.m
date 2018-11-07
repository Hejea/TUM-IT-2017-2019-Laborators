function setFigureProprietes
    fig = figure(1);
    set(fig,'units','points','position',[400,125,430,400]);

    x = [-5 15];
    y = [-5 15];

    set(gca, 'xtick', x(1):1:x(2));
    set(gca, 'ytick', y(1):1:y(2));
    hold on

    ax = gca;
    ax.XAxisLocation = 'origin';
    ax.YAxisLocation = 'origin';

    xlabel('x1->');
    ylabel('x2->');
    grid on
    grid minor
    hold on
end