function [ D ] = delta (simplex, pret)
    
    D = sym('d', [0 0]);
    n = size(simplex, 1);
    m = size(pret, 1);

    for i = 1:m
        s = 0;
        for j = 1:n
            s = s + simplex(j, 1)*simplex(j, i+2);
        end
        D = [D, s - pret(i)]; 
    end

end