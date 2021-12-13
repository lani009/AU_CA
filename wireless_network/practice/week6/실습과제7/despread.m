function[dout]=despread(sdata, codes)
[hn, vn] = size(sdata); % sdata array size
[hc, vc] = size(codes); % codes array size

row=hn;
column=vn/vc;
dout = zeros(row, column); % size 정리 

for i=1:row 
    for j =1:column
        value=sdata(i, (j-1)*vc+1 : j*vc); % vc 단위로 끊어읽기 
        dout(i, j) = codes(i, :) * rot90(value, -1);
   end
end
