clc
clear all
close all

t=[0:2*pi/99:2*pi]; %�ð���

BER_avg=[];
AWGN_sum=[];
AWGN_map=[];
  
%SNR ������ -10���� 10���� ����
SNR_start=-10;
SNR_end=10;
SNR=SNR_start:1:SNR_end;
    
sim_loop=1; % BER ���� ��, ������ �����Ͽ� ��� ���� 
bit_len=100; % ������ 10��
bit_rang = [0.01:0.01:bit_len]; % plot���� bit range 

for ii=1:length(SNR)
    BER=[]; 
    for sim=1:sim_loop
        % ���� ������ ����
        bits=round(rand(1,bit_len));
        % ������ �Է� �޾� bpsk ����
        [bpsk]=Mod_bpsk(bits);
        % ���� ä�� ���۽� fading ����
        [fade_bits fade_sig]=Rayleigh(bpsk,bits);
        % ���Ŵ��� AWGN noise ����
        [AWGN_bits AWGN_sig]=AWGN(fade_sig, SNR(ii),bits);
        AWGN_sum=[AWGN_sum; AWGN_bits];
        % BPSK ���� ����Ͽ� ���� ������ ����
        [dbpsk]=dMod_bpsk(AWGN_sig,bits);
        % ���� �����Ϳ� ���Ͽ� BER ����
        BER(sim)=sum(xor(bits,dbpsk))/length(bits);

    end
    
    BER_avg=[BER_avg mean(BER)];
    AWGN_map=[AWGN_map; mean(AWGN_sum,1)];
end

show_bits=[];   
dshow_bits=[]; 

% �޽�ó�� ���̰� bpsk ������ �ϳ��� ������ 100���� ����
for i=1:length(bits)
    if bits(i)==0
        show_bits=[show_bits 0*ones(1,100)];
    else
        show_bits=[show_bits 1*ones(1,100)];
    end
end

% �޽�ó�� ���̰� dmod_bpsk ������ �ϳ��� 100���� ����
for i=1:length(dbpsk)
    if dbpsk(i)==0
        dshow_bits=[dshow_bits 0*ones(1,100)];
    else
        dshow_bits=[dshow_bits 1*ones(1,100)];
    end
end

% �׷��� ���� 
f1 = figure('Name', '5-10��° ��Ʈ�� ���� �� ���� ���� �ܰ�');

subplot(2, 2, 1)
plot(bit_rang, show_bits, 'b') 
axis([5 10 -0.5 1.5])
title('<1> 5-10��° ��Ʈ�� ���� ��ȣ')


subplot(2, 2, 2)
plot(bit_rang, bpsk, 'b')
axis([5 10 -2 2])
title('<2> BPSK ����')

subplot(2, 2, 3)
plot(bit_rang, AWGN_sig, 'b')
axis([5 10 -2 2])
title('<3> ���̵��� AWGN ����')


subplot(2, 2, 4)
plot(bit_rang, dshow_bits, 'r')
axis([5 10 -0.5 1.5])
title('<4> BPSK ������ ��Ʈ��')

f2 = figure('Name', 'BER ����(Log Scale)');
plot(SNR, log(BER_avg), '-.rp')
title('BER performance (Log Scale)')
xlabel('AWGN Noise SNR(dB)')
ylabel('BER (Log)')
grid on

f3 = figure('Name', 'BER Performance');
plot(SNR, BER_avg, '-.rp')
title('BER ����')
xlabel('AWGN Noise SNR(dB)')
ylabel('BER')
grid on

