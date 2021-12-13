function[dout]=despread(sdata, codes)
[hn, vn] = size(sdata); % sdata array size
[hc, vc] = size(codes); % codes array size

row=hn;
column=vn/vc;
dout = zeros(row, column); % size ���� 

for i=1:row 
    for j =1:column
        value=sdata(i, (j-1)*vc+1 : j*vc); % vc ������ �����б� 
        dout(i, j) = codes(i, :) * rot90(value, -1);
   end
end
