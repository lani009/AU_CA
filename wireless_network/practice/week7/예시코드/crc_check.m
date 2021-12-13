function check=crc_check(r_frame,poly)
[q,r]=deconv(r_frame,poly);
modr=mod(r,2);
sum=0;
for ii=1:1:length(modr)
if modr(ii)==0
  
else
    sum=sum+1;
end

end

if sum==0
    check=0;
else
    check=1;
end   