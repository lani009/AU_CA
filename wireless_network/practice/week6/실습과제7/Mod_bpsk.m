function [bpsk]=Mod_bpsk(bits)
t=[0:2*pi/99:2*pi]; %시간축
bpsk=[]; % BPSK 이후 변조된 신호 
freq=2*pi; % 주파수 2pi 
carrier=sin(freq*t);

for n=1:length(bits)
    if bits(n)<0
        as=-1;
    else
        as=1;
    end
    bpsk=[bpsk as*carrier];
end

