function [crc, frame]=crc_gen(msg,poly)
newzero=zeros(1,length(poly)-1);
realmsg=[msg newzero];
[q,r]=deconv(realmsg,poly);
modr=mod(r,2)
crc=modr(length(msg)+1);

for ii=2:1:length(poly)-1
    crc=[crc modr(length(msg)+ii)];
end

crc=crc
frame=[msg crc]