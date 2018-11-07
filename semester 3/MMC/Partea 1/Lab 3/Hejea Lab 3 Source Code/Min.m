function [ index ] = Min (array)

    n = size(array, 2);
    M = sym('m', [1 n]);
       
    array = double(subs(array, M(1), 100000));
    
    min = array(1);
    index = 1;
    for i = 2:(n-1)
        if min >= array(i)
            min = array(i);
            index = i;
        end
    end
end