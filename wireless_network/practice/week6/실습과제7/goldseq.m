function [gout] = goldseq(seq1,seq2,n)

if nargin <3
    n=1;
end

v=length(seq1);
gout = zeros(n,v);

for i=1:n
    gout(i, :) = xor(seq1, seq2);
    seq2 = shift(seq2, 1);
end