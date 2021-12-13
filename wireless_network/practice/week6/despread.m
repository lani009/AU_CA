function [dout] = despread(sdata, codes)
% sdata: spread data seq of users
% dout: output data seq
% codes: spread code seq

dout = [];
[iMax, jMax] = size(sdata);
[~, codeLength] = size(codes);

for i = 1 : iMax
    despreadCode = [];
    for j = 1 : jMax/codeLength
        despreadCode = [despreadCode sum(sdata(i, (j-1)*codeLength+1 : j*codeLength) .* codes(i, :))];
    end
    dout(i, :) = despreadCode;
end