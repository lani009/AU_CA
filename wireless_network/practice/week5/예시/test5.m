% 문제 1은 보고서에 그림 첨부
% 문제 2 
stg=3; % stage 수
taps=[1 3]; % register feedback 위치
inidata=[1 0 1]; % 초기 sequence 
ans2= mseq(stg, taps, inidata)  

% 문제 3
ans2=ans2*2-1;
ans3=autocorr(ans2,2);
figure(1);
hold on; 
stem(ans3);
hold off;

% 문제 4,5 
ans4=mseq(3,[2 3],[1 0 1])
ans4=ans4*2-1;
tn=1+2^((stg+1)/2)
ans5=crosscorr(ans2,ans4,2) 
figure(2);
hold on;
stem(ans5);
hold off;
