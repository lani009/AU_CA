clc
clear all
close all

t=[0:2*pi/99:2*pi]; %시간축

BER_avg=[];
AWGN_sum=[];
AWGN_map=[];
  
%SNR 범위를 -10에서 10으로 설정
SNR_start=-10;
SNR_end=10;
SNR=SNR_start:1:SNR_end;
    
sim_loop=1; % BER 구할 때, 여러번 측정하여 평균 내기 
bit_len=100; % 데이터 10만
bit_rang = [0.01:0.01:bit_len]; % plot에서 bit range 

for ii=1:length(SNR)
    BER=[]; 
    for sim=1:sim_loop
        % 랜덤 데이터 생성
        bits=round(rand(1,bit_len));
        % 데이터 입력 받아 bpsk 변조
        [bpsk]=Mod_bpsk(bits);
        % 무선 채널 전송시 fading 적용
        [fade_bits fade_sig]=Rayleigh(bpsk,bits);
        % 수신단의 AWGN noise 적용
        [AWGN_bits AWGN_sig]=AWGN(fade_sig, SNR(ii),bits);
        AWGN_sum=[AWGN_sum; AWGN_bits];
        % BPSK 복조 사용하여 수신 데이터 복구
        [dbpsk]=dMod_bpsk(AWGN_sig,bits);
        % 원래 데이터와 비교하여 BER 구함
        BER(sim)=sum(xor(bits,dbpsk))/length(bits);

    end
    
    BER_avg=[BER_avg mean(BER)];
    AWGN_map=[AWGN_map; mean(AWGN_sum,1)];
end

show_bits=[];   
dshow_bits=[]; 

% 펄스처럼 보이게 bpsk 데이터 하나당 동일한 100개로 설정
for i=1:length(bits)
    if bits(i)==0
        show_bits=[show_bits 0*ones(1,100)];
    else
        show_bits=[show_bits 1*ones(1,100)];
    end
end

% 펄스처럼 보이게 dmod_bpsk 데이터 하나당 100개로 설정
for i=1:length(dbpsk)
    if dbpsk(i)==0
        dshow_bits=[dshow_bits 0*ones(1,100)];
    else
        dshow_bits=[dshow_bits 1*ones(1,100)];
    end
end

% 그래프 설정 
f1 = figure('Name', '5-10번째 비트열 변조 및 복조 진행 단계');

subplot(2, 2, 1)
plot(bit_rang, show_bits, 'b') 
axis([5 10 -0.5 1.5])
title('<1> 5-10번째 비트열 원래 신호')


subplot(2, 2, 2)
plot(bit_rang, bpsk, 'b')
axis([5 10 -2 2])
title('<2> BPSK 적용')

subplot(2, 2, 3)
plot(bit_rang, AWGN_sig, 'b')
axis([5 10 -2 2])
title('<3> 페이딩과 AWGN 적용')


subplot(2, 2, 4)
plot(bit_rang, dshow_bits, 'r')
axis([5 10 -0.5 1.5])
title('<4> BPSK 복조된 비트열')

f2 = figure('Name', 'BER 성능(Log Scale)');
plot(SNR, log(BER_avg), '-.rp')
title('BER performance (Log Scale)')
xlabel('AWGN Noise SNR(dB)')
ylabel('BER (Log)')
grid on

f3 = figure('Name', 'BER Performance');
plot(SNR, BER_avg, '-.rp')
title('BER 성능')
xlabel('AWGN Noise SNR(dB)')
ylabel('BER')
grid on

