function [dbpsk]=dMod_bpsk(AWGN_sig,bits)
t=[0:2*pi/99:2*pi];% 시간축
dbpsk=[];
freq=2*pi;% 주파수 2pi 
carrier=sin(freq*t);

for n=1:length(bits)
    demod=AWGN_sig((n-1)*100+1 : n*100) .*carrier;
    tt=sum(demod/(99/2));
    if round(tt)==1
        dbpsk=[dbpsk 1];
    else
        dbpsk=[dbpsk 0];
    end
end
