function [bpsk]=Mod_bpsk(bits)
t=[0:2*pi/99:2*pi]; %�ð���
bpsk=[]; % BPSK ���� ������ ��ȣ 
freq=2*pi; % ���ļ� 2pi 
carrier=sin(freq*t);

for n=1:length(bits)
    if bits(n)<0
        as=-1;
    else
        as=1;
    end
    bpsk=[bpsk as*carrier];
end

