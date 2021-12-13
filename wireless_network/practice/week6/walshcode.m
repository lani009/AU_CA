function [wout] = walshcode(n)
% n: degree of Hadamard matrix
% wout: Output Walsh Codes

wout = [-1 -1
        -1 1];

if n ~= 1
    for i = 1 : n-1
    wout = [wout wout; wout -wout];
    end
end