function [sout] = spread(udata, codes)
[hn, vn]=size(udata);
[hc, vc]=size(codes);

if hn>hc
    error('lack of spread code sequences');
end
sout=zeros(hn, vn*vc);
for ii=1: hn
    sout(ii,:)=reshape(rot90(codes(ii,:), 3)*udata(ii,:), 1, vn*vc);
end
    