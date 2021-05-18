function [x]=unirv(a, b, N)
    x = a + (b - a) * rand(N, 1);
end