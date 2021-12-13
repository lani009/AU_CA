function [sout] = spread(udata, codes)
% updata: data seq of users
% sout: out data seq
% codes: spread code seq

sout = [];
[iMax, jMax] = size(udata);

for i = 1 : iMax
    spreadCode = [];
    for j = 1 : jMax
        spreadCode = [spreadCode udata(i, j) * codes(i, :)];
    end
    sout(i, :) = spreadCode;
end