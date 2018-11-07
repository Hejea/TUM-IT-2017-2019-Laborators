function [ t ] = isOptim ( array )

    n = size(array, 2);
    M = sym('m', [1 n]);
       
    array = double(subs(array, M(1), 100000));
    
    t = 1;
    for i = 1:n-1
        if array(i) < 0
            t = 0;
            break;
        end
    end

end