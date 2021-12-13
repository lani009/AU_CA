clear; clc;

msg=[1 1 1 0 0 0 1 1];
poly=[1 1 0 0 1 1];
BER=0.1;

[crc, frame]=crc_gen(msg,poly); % crc=[1 1 0 1 0]

r_frame=zeros(1,length(frame));
for ii=1:1:length(frame)
    if rand<BER
        r_frame(ii)=~frame(ii);
    else 
        r_frame(ii)=frame(ii);
    end
end

check=crc_check(r_frame, poly)

if check==0
    fprintf('No Error\n');
else
    fprintf('Error Detected\n');
end