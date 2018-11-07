function setFigureProprietes ( fig )
set(fig,'units','points','position',[400,125,430,400]);
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

