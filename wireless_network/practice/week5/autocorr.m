function [out] = autocorr(indata, tn)
if nargin < 2
    tn = 1;
end

ln = length(indata);
out = zeros(1, ln*tn);

for ii = 0 : ln*tn-1
    out(ii+1) = sum(indata.*shift(indata, ii, 0));
end