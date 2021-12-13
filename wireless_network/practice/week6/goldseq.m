function [gout] = goldseq(m1, m2, n)
if nargin < 3
    n = 1;
end

gout = zeros(n, length(m1));

for ii = 1 : n
    gout(ii, :) = xor(m1, m2);
    m2 = shift(m2, 1, 0);
end