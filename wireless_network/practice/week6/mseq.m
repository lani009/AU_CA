function [mout] = mseq(stg, taps, inidata)
mout = zeros(1, 2^stg-1); 
fpos = zeros(stg, 1);
fpos(taps) = 1;

for ii = 1 : 2^stg-1
    mout(1, ii) = inidata(stg);
    num = mod(inidata*fpos, 2);
    inidata = shift(inidata, 1, 0);
    inidata(1) = num;
end